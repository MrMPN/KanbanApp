package com.particular.marc.kanbanapp;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.particular.marc.kanbanapp.adapter.MainPagerAdapter;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setViewPager();
    }

    private void setViewPager(){
        // Find the view pager that will allow the user to swipe between fragments
        ViewPager viewPager = findViewById(R.id.main_viewpager);
        // Create an adapter that knows which fragment should be shown on each page
        MainPagerAdapter adapter = new MainPagerAdapter(getSupportFragmentManager(),
                this);
        // Set the adapter onto the view pager
        viewPager.setAdapter(adapter);
        // Give the TabLayout the ViewPager
        TabLayout tabLayout = findViewById(R.id.main_sliding_tabs);
        tabLayout.setupWithViewPager(viewPager);
    }
}
