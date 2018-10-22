package com.particular.marc.kanbanapp.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Transformations;
import android.support.annotation.NonNull;

import com.particular.marc.kanbanapp.model.Issue;
import com.particular.marc.kanbanapp.repository.RepoRepository;

import java.util.ArrayList;
import java.util.List;

/**
 * ViewModel that will hold all the issues data (list of Issue)
 * It gets its LiveData from the RepoRepository class
 * KanbanBoardfragment will observe methods from this
 */
public class KanbanViewModel extends AndroidViewModel {
    private static final String TAG = "KanbanViewModel";
    public static final int BACKLOG = 0;
    public static final int NEXT = 1;
    public static final int DOING = 2;
    public static final int DONE = 3;
    private RepoRepository repository;
    private LiveData<List<Issue>> issues;

    public KanbanViewModel(@NonNull Application application) {
        super(application);
        repository = RepoRepository.getInstance(application);
    }

    public void getIssuesByRepo(int repoId){
        issues =repository.getIssuesByRepo(repoId);
    }

    public LiveData<List<Issue>> getIssuesByRepoFilteredByBoard(int board){
        return Transformations.map(issues, list -> {
            List<Issue> filteredList = new ArrayList<>();
            for (Issue issue: list){
                if (issue.getBoard() == board){
                    filteredList.add(issue);
                }
            }
            return filteredList;
        });
    }

    public void updateIssue(Issue issue){
        repository.updateIssue(issue);
    }
}
