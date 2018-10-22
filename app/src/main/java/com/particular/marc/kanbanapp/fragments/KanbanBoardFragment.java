package com.particular.marc.kanbanapp.fragments;


import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.particular.marc.kanbanapp.R;
import com.particular.marc.kanbanapp.adapter.KanbanListAdapter;
import com.particular.marc.kanbanapp.model.Issue;
import com.particular.marc.kanbanapp.viewmodel.KanbanViewModel;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * This will be the initial fragment the user will see when clicking on a repository.
 * Displays a recyclerView with the list of backlog issues (default state when newly created)
 */
public class KanbanBoardFragment extends Fragment {
    private static final String TAG = "KanbanBoardFragment";
    private KanbanViewModel viewModel;
    private KanbanListAdapter adapter;
    private int board;

    public KanbanBoardFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_base, container, false);
        getBundleData();
        setRecyclerView(v);
        initViewModel();
        return v;
    }

    /**
     * This method will provide us with the int that tells us the board we are
     */
    private void getBundleData(){
        Bundle bundle = this.getArguments();
        if (bundle != null) {
            board = bundle.getInt("board", 0);
        }
    }

    private void setRecyclerView(View v){
        RecyclerView recyclerView = v.findViewById(R.id.list);
        adapter = new KanbanListAdapter(getContext(), board);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    private void initViewModel(){
        viewModel = ViewModelProviders.of(getActivity()).get(KanbanViewModel.class);
        viewModel.getIssuesByRepoFilteredByBoard(board).observe(getViewLifecycleOwner(), new Observer<List<Issue>>() {
            @Override
            public void onChanged(@Nullable List<Issue> issues) {
                adapter.submitList(issues);
            }
        });
    }
}
