package com.particular.marc.kanbanapp.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.paging.PagedList;
import android.support.annotation.NonNull;

import com.particular.marc.kanbanapp.model.Repo;
import com.particular.marc.kanbanapp.repository.RepoRepository;

/**
 * ViewModel that will hold all the repos data (list of Repo)
 * It gets its LiveData from the RepoRepository class
 * Both Explore and Local fragments will observe methods from this
 */
public class MainViewModel extends AndroidViewModel {
    private static final String TAG = "MainViewModel";
    private RepoRepository repository;
    private LiveData<PagedList<Repo>> repos;
    private LiveData<PagedList<Repo>> localRepos;

    public MainViewModel(@NonNull Application application) {
        super(application);
        repository = RepoRepository.getInstance(application);
        repos = repository.getReposList();
        localRepos = repository.getLocalRepos();
    }

    public LiveData<PagedList<Repo>> getRepos() {
        return repos;
    }

    public LiveData<PagedList<Repo>> getLocalRepos(){
        return localRepos;
    }

    public void makeRepoLocal(Repo repo){
        repository.makeRepoLocal(repo);
    }
}
