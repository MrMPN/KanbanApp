package com.particular.marc.kanbanapp.fragments;


import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.arch.paging.PagedList;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.particular.marc.kanbanapp.R;
import com.particular.marc.kanbanapp.adapter.MainListAdapter;
import com.particular.marc.kanbanapp.adapter.MainListAdapter.ListItemClickListener;
import com.particular.marc.kanbanapp.model.Repo;
import com.particular.marc.kanbanapp.viewmodel.MainViewModel;


/**
 * A simple {@link Fragment} subclass.
 * This will be the initial fragment the user will see. It displays a recyclerView with the list of
 * repositories of a given user
 */
public class ExploreFragment extends Fragment implements ListItemClickListener {
    private static final String TAG = "ExploreFragment";
    private MainViewModel viewModel;
    private MainListAdapter adapter;

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
        adapter = new MainListAdapter(getContext(), this, MainListAdapter.EXPLORE);
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

    @Override
    public void onAddListItemClick(Repo clickedItem) {
        if (!clickedItem.isHasKanban()) {
            String nameRepo = clickedItem.getFullName().split("/")[1];
            Log.d(TAG, "makeRepoLocal: " + nameRepo);
            Toast.makeText(getContext(), "Adding " + nameRepo + " to Local", Toast.LENGTH_SHORT).show();
            clickedItem.setHasKanban(true);
            viewModel.makeRepoLocal(clickedItem);
        }
        else{
            Toast.makeText(getContext(), "This repository is already in Local tab", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onListItemClick(Repo clickedItem) {
        //We do nothing
    }
}
