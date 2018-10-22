package com.particular.marc.kanbanapp.fragments;


import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.arch.paging.PagedList;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.particular.marc.kanbanapp.KanbanActivity;
import com.particular.marc.kanbanapp.R;
import com.particular.marc.kanbanapp.adapter.MainListAdapter;
import com.particular.marc.kanbanapp.adapter.MainListAdapter.ListItemClickListener;
import com.particular.marc.kanbanapp.model.Repo;
import com.particular.marc.kanbanapp.viewmodel.MainViewModel;


/**
 * A simple {@link Fragment} subclass.
 * This fragment displays a recyclerView with the list of repositories the user has opened a Kanban board for.
 * The user can click the "Local" tab or slide to get into this fragment
 */
public class LocalFragment extends Fragment implements ListItemClickListener {
    private static final String TAG = "LocalFragment";
    private MainViewModel viewModel;
    private MainListAdapter adapter;
    final public static String REPO_ID = "repoId";
    final public static String REPO_NAME = "repoName";

    public LocalFragment() {
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
        adapter = new MainListAdapter(getContext(), this, MainListAdapter.LOCAL);
        recyclerView.setAdapter(adapter);
    }

    private void initViewModel(){
        viewModel = ViewModelProviders.of(getActivity()).get(MainViewModel.class);
        viewModel.getLocalRepos().observe(getViewLifecycleOwner(), new Observer<PagedList<Repo>>() {
            @Override
            public void onChanged(@Nullable PagedList<Repo> repos) {
                adapter.submitList(repos);
            }
        });
    }

    @Override
    public void onAddListItemClick(Repo clickedItem) {
        //We do nothing
    }

    @Override
    public void onListItemClick(Repo clickedItem) {
        Intent intent = new Intent(getContext(), KanbanActivity.class);
        String [] processedName = clickedItem.getFullName().split("/");
        intent.putExtra(REPO_ID, clickedItem.getId());
        intent.putExtra(REPO_NAME, processedName[1]);
        getContext().startActivity(intent);
    }
}
