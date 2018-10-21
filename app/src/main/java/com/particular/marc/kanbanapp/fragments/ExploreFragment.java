package com.particular.marc.kanbanapp.fragments;


import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.arch.paging.PagedList;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.particular.marc.kanbanapp.R;
import com.particular.marc.kanbanapp.RepoListAdapter;
import com.particular.marc.kanbanapp.model.Repo;
import com.particular.marc.kanbanapp.viewmodel.MainViewModel;


/**
 * A simple {@link Fragment} subclass.
 * This will be the initial fragment the user will see. It displays a recyclerView with the list of
 * repositories of a given user
 */
public class ExploreFragment extends Fragment {
    private static final String TAG = "ExploreFragment";
    private MainViewModel viewModel;
    private RepoListAdapter adapter;

    public ExploreFragment() {
    }
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_base, container, false);
        setRecyclerView(v);
        initViewModel();
        return v;
    }

    private void setRecyclerView(View v){
        RecyclerView recyclerView = v.findViewById(R.id.list);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new RepoListAdapter(getContext());
        recyclerView.setAdapter(adapter);
    }

    private void initViewModel(){
        viewModel = ViewModelProviders.of(getActivity()).get(MainViewModel.class);
        viewModel.getRepos().observe(getViewLifecycleOwner(), new Observer<PagedList<Repo>>() {
            @Override
            public void onChanged(@Nullable PagedList<Repo> repos) {
                adapter.submitList(repos);
            }
        });
    }

}
