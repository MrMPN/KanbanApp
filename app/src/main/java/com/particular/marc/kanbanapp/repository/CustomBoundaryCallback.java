package com.particular.marc.kanbanapp.repository;

import android.arch.paging.PagedList.BoundaryCallback;
import android.support.annotation.NonNull;
import android.util.Log;

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
    private AppExecutors mExecutors = AppExecutors.getInstance();
    private ApiRequest service;
    private RepoDao repoDao;



    CustomBoundaryCallback(RepoDao repoDao) {
        super();
        this.repoDao = repoDao;
        service = RetrofitClient.getRetrofitInstance().create(ApiRequest.class);
    }

    private int lastRequestedPage = 0;
    private boolean isRequestInProgress = false;
    /**
     * Database returned 0 items, we should query the server, get results
     * and insert them into the db
     */
    @Override
    public void onZeroItemsLoaded() {
        Log.d(TAG, "onZeroItemsLoaded: ");
        super.onZeroItemsLoaded();
        requestAndSaveData();
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
        requestAndSaveData();
    }

    private void requestAndSaveData(){
        if (isRequestInProgress) return;
        isRequestInProgress = true;
        Log.d(TAG, "requestAndSaveData: Page" + (lastRequestedPage+1));
        Call<List<Repo>> call = service.getAllRepos(lastRequestedPage+1, 30);
        call.enqueue(new Callback<List<Repo>>() {
            @Override
            public void onResponse(Call<List<Repo>> call, final Response<List<Repo>> response) {
                if (response.isSuccessful()){
                    mExecutors.diskIO().execute(new Runnable() {
                        @Override
                        public void run() {
                            //Log.d(TAG, "run: " + response.headers().get("Link"));
                            if (response.body().size() > 0){
                                response.body().get(response.body().size()-1).setLastPage(lastRequestedPage+1);
                                repoDao.insert(response.body());
                            }
                            isRequestInProgress = false;
                        }
                    });

                }
            }
            @Override
            public void onFailure(Call<List<Repo>> call, Throwable t) {
                Log.d(TAG, "onFailure: " +t.getLocalizedMessage());
                isRequestInProgress = false;
            }
        });
    }
}
