package com.particular.marc.kanbanapp.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.support.annotation.NonNull;

import com.particular.marc.kanbanapp.repository.RepoRepository;

/**
 * ViewModel that will hold all the issues data (list of Issue)
 * It gets its LiveData from the RepoRepository class
 * (Backlog,Next,Doing,Done) fragments will observe methods from this
 */
public class KanbanViewModel extends AndroidViewModel {
    private RepoRepository repository;

    public KanbanViewModel(@NonNull Application application) {
        super(application);
    }
}
