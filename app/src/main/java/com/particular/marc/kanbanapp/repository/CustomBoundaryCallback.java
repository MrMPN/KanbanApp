package com.particular.marc.kanbanapp.repository;

import android.arch.paging.PagedList.BoundaryCallback;
import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;

import com.particular.marc.kanbanapp.database.AppDatabase;
import com.particular.marc.kanbanapp.database.RepoDao;
import com.particular.marc.kanbanapp.helper.AppExecutors;
import com.particular.marc.kanbanapp.model.Repo;
import com.particular.marc.kanbanapp.network.ApiRequest;
import com.particular.marc.kanbanapp.network.RetrofitClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CustomBoundaryCallback extends BoundaryCallback<Repo> {
    private static final String TAG = "CustomBoundaryCallback";
    private AppExecutors mExecutors;
    private ApiRequest service;
    private RepoDao repoDao;
    private int lastRequestedPage = 0;
    private boolean isRequestInProgress = false;


    CustomBoundaryCallback(Context context) {
        super();
        AppDatabase db = AppDatabase.getInstance(context.getApplicationContext());
        repoDao = db.repoDao();
        mExecutors = AppExecutors.getInstance();
        service = RetrofitClient.getRetrofitInstance().create(ApiRequest.class);
    }

    /**
     * Database returned 0 items, we should query the server, get results
     * and insert them into the db
     */
    @Override
    public void onZeroItemsLoaded() {
        Log.d(TAG, "onZeroItemsLoaded: ");
        super.onZeroItemsLoaded();
        requestData();
    }

    /**
     * We're running out of items, we should query the server, get results and
     * insert them
     * Problem: itemAtEnd is no useful with github API, we need pages
     * @param itemAtEnd
     */
    @Override
    public void onItemAtEndLoaded(@NonNull Repo itemAtEnd) {
        Log.d(TAG, "onItemAtEndLoaded: ");
        super.onItemAtEndLoaded(itemAtEnd);
        lastRequestedPage = itemAtEnd.getLastPage();
        requestData();
    }

    private void requestData(){
        if (isRequestInProgress) return;
        isRequestInProgress = true;
        Call<List<Repo>> call = service.getAllRepos(lastRequestedPage+1, 30);
        call.enqueue(new Callback<List<Repo>>() {
            @Override
            public void onResponse(Call<List<Repo>> call, final Response<List<Repo>> response) {
                if (response.isSuccessful()){
                   saveData(response.body());
                }
            }
            @Override
            public void onFailure(Call<List<Repo>> call, Throwable t) {
                Log.d(TAG, "onFailure: " +t.getLocalizedMessage());
                isRequestInProgress = false;
            }
        });
    }

    private void saveData(final List<Repo> repos){
        mExecutors.diskIO().execute(new Runnable() {
            @Override
            public void run() {
                if (repos.size() > 0){
                    repos.get(repos.size()-1).setLastPage(lastRequestedPage+1);
                    repoDao.insert(repos);
                }
                isRequestInProgress = false;
            }
        });
    }
}
