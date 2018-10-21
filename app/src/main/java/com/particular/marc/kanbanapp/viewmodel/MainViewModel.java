package com.particular.marc.kanbanapp.viewmodel;

import android.arch.lifecycle.AndroidViewModel;

/**
 * ViewModel that will hold all the repos data (list of Repo)
 * It gets its LiveData from the RepoRepository class
 * Both Explore and Local fragments will observe methods from this
 */
public class MainViewModel extends AndroidViewModel {
}
