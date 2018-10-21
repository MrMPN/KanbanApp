package com.particular.marc.kanbanapp.viewmodel;

import android.arch.lifecycle.AndroidViewModel;

/**
 * ViewModel that will hold all the issues data (list of Issue)
 * It gets its LiveData from the RepoRepository class
 * (Backlog,Next,Doing,Done) fragments will observe methods from this
 */
public class KanbanViewModel extends AndroidViewModel {
}
