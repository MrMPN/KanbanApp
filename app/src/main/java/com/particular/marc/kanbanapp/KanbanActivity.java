package com.particular.marc.kanbanapp;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.particular.marc.kanbanapp.adapter.KanbanPagerAdapter;
import com.particular.marc.kanbanapp.fragments.LocalFragment;
import com.particular.marc.kanbanapp.viewmodel.KanbanViewModel;

public class KanbanActivity extends AppCompatActivity {
    private static final String TAG = "KanbanActivity";
    private ViewModel viewModel;
    private int repoId;
    private String repoName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kanban);
        getRepoFromIntent();
        setTitle(repoName);
        viewModel = ViewModelProviders.of(this).get(KanbanViewModel.class);
        ((KanbanViewModel) viewModel).getIssuesByRepo(repoId);
        setViewPager();
    }

    private void getRepoFromIntent(){
        Intent intent = getIntent();
        if (intent != null && intent.hasExtra("repoId")){
            repoId = intent.getIntExtra(LocalFragment.REPO_ID, 0);
            repoName = intent.getStringExtra(LocalFragment.REPO_NAME);
        }
    }

    private void setViewPager(){
        // Find the view pager that will allow the user to swipe between fragments
        ViewPager viewPager = findViewById(R.id.kanban_viewpager);
        // Create an adapter that knows which fragment should be shown on each page
        KanbanPagerAdapter adapter = new KanbanPagerAdapter(getSupportFragmentManager(),
                this);
        // Set the adapter onto the view pager
        viewPager.setAdapter(adapter);
        // Give the TabLayout the ViewPager
        TabLayout tabLayout = findViewById(R.id.kanban_sliding_tabs);
        tabLayout.setupWithViewPager(viewPager);
    }
}
