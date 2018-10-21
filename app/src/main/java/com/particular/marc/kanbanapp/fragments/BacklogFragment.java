package com.particular.marc.kanbanapp.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.particular.marc.kanbanapp.R;

/**
 * A simple {@link Fragment} subclass.
 * This will be the initial fragment the user will see when clicking on a repository.
 * Displays a recyclerView with the list of backlog issues (default state when newly created)
 */
public class BacklogFragment extends Fragment {


    public BacklogFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_base, container, false);
    }

}