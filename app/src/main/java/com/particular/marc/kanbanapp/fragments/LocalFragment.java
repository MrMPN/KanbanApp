package com.particular.marc.kanbanapp.fragments;


import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.particular.marc.kanbanapp.R;
import com.particular.marc.kanbanapp.RepoListAdapter;
import com.particular.marc.kanbanapp.viewmodel.MainViewModel;


/**
 * A simple {@link Fragment} subclass.
 * This fragment displays a recyclerView with the list of repositories the user has opened a Kanban board for.
 * The user can click the "Local" tab or slide to get into this fragment
 */
public class LocalFragment extends Fragment {
    private static final String TAG = "LocalFragment";
    private MainViewModel viewModel;
    private RepoListAdapter adapter;

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
        adapter = new RepoListAdapter(getContext(), RepoListAdapter.LOCAL);
        recyclerView.setAdapter(adapter);
    }

    private void initViewModel(){
        viewModel = ViewModelProviders.of(getActivity()).get(MainViewModel.class);
    }

}
