package com.particular.marc.kanbanapp.repository;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.arch.paging.LivePagedListBuilder;
import android.arch.paging.PagedList;
import android.util.Log;

import com.particular.marc.kanbanapp.database.AppDatabase;
import com.particular.marc.kanbanapp.database.IssueDao;
import com.particular.marc.kanbanapp.database.RepoDao;
import com.particular.marc.kanbanapp.helper.AppExecutors;
import com.particular.marc.kanbanapp.model.Issue;
import com.particular.marc.kanbanapp.model.Repo;
import com.particular.marc.kanbanapp.network.ApiRequest;
import com.particular.marc.kanbanapp.network.RetrofitClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Repository that will handle all the data-fetching related tasks, be it from the server or local.
 * It uses the Room Database as DataSource to pass into the viewmodel, but it'll use the server
 * to fetch more data when running out
 */
public class RepoRepository {
    private static final String TAG = "RepoRepository";
    private ApiRequest service;
    private RepoDao repoDao;
    private IssueDao issueDao;
    private static RepoRepository instance;
    private AppExecutors mExecutors = AppExecutors.getInstance();
    private LiveData<PagedList<Repo>> reposList;
    private LiveData<PagedList<Repo>> localReposList;

    public static synchronized RepoRepository getInstance(Application application){
        if (instance == null){
            instance = new RepoRepository(application);
        }
        return instance;
    }

    private RepoRepository(Application application){
        service = RetrofitClient.getRetrofitInstance().create(ApiRequest.class);
        AppDatabase db = AppDatabase.getInstance(application.getApplicationContext());
        repoDao = db.repoDao();
        issueDao = db.issueDao();
        localReposList = new LivePagedListBuilder<>(
                repoDao.getAllLocalRepos(), 30)
                .build();
        reposList = new LivePagedListBuilder<>(
                repoDao.getAllRepos(), 30)
                .setBoundaryCallback(new CustomBoundaryCallback(application))
                .build();
    }

    public LiveData<PagedList<Repo>> getReposList() {
        return reposList;
    }
    public LiveData<PagedList<Repo>> getLocalRepos() {
        return localReposList;
    }
    public LiveData<List<Issue>> getIssuesByRepo(int repoId){
        return issueDao.getIssuesByRepo(repoId);
    }

    private void fetchIssuesList(final Repo repo){
        String[] processed_name = repo.getFullName().split("/");
        Call<List<Issue>> call = service.getAllIssuesByRepo(processed_name[1], 1, 100);
        call.enqueue(new Callback<List<Issue>>() {
            @Override
            public void onResponse(Call<List<Issue>> call, Response<List<Issue>> response) {
                if (response.isSuccessful()){
                    Log.d(TAG, "onResponse:");
                    insertIssues(response.body(), repo.getId());
                }
            }
            @Override
            public void onFailure(Call<List<Issue>> call, Throwable t) {
                Log.i(TAG, "onFailure: " + t.getMessage());
            }
        });
    }

    private void insertIssues(final List<Issue> issues, final int repoId){
        mExecutors.diskIO().execute(new Runnable() {
            @Override
            public void run() {
                for (Issue i : issues){
                    i.setRepoId(repoId);
                }
                issueDao.insert(issues);
            }
        });
    }

    public void makeRepoLocal(final Repo repo){
        Log.d(TAG, "makeRepoLocal: ");
        mExecutors.diskIO().execute(new Runnable() {
            @Override
            public void run() {
                repoDao.update(repo);
                fetchIssuesList(repo);
            }
        });

    }

    public void updateIssue(final Issue issue){
        Log.d(TAG, "updateIssue: ");
        mExecutors.diskIO().execute(new Runnable() {
            @Override
            public void run() {
                issueDao.update(issue);
            }
        });    }
}
