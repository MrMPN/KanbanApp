package com.particular.marc.kanbanapp.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.particular.marc.kanbanapp.R;


/**
 * A simple {@link Fragment} subclass.
 * This fragment displays a recyclerView with the list of repositories the user has opened a Kanban board for.
 * The user can click the "Local" tab or slide to get into this fragment
 */
public class LocalFragment extends Fragment {


    public LocalFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_base, container, false);
    }

}
