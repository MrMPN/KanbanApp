package com.particular.marc.kanbanapp.repository;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.arch.paging.LivePagedListBuilder;
import android.arch.paging.PagedList;

import com.particular.marc.kanbanapp.database.AppDatabase;
import com.particular.marc.kanbanapp.database.RepoDao;
import com.particular.marc.kanbanapp.model.Repo;
import com.particular.marc.kanbanapp.network.ApiRequest;
import com.particular.marc.kanbanapp.network.RetrofitClient;

/**
 * Repository that will handle all the data-fetching related tasks, be it from the server or local.
 * It uses the Room Database as DataSource to pass into the viewmodel, but it'll use the server
 * to fetch more data when running out
 */
public class RepoRepository {
    private ApiRequest service;
    private RepoDao repoDao;
    private static RepoRepository instance;
    private LiveData<PagedList<Repo>> reposList;

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
        reposList = new LivePagedListBuilder<>(
                repoDao.getAllRepos(), 30).build();
    }

    public LiveData<PagedList<Repo>> getReposList() {
        return reposList;
    }
}
