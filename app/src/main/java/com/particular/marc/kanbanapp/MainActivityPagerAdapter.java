package com.particular.marc.kanbanapp;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.particular.marc.kanbanapp.fragments.ExploreFragment;
import com.particular.marc.kanbanapp.fragments.LocalFragment;

/**
 * FragmentPagerAdapter subclass used to populate the MainActivity ViewPager, so the user can
 * swipe between the Local and Explore fragments
 */
public class MainActivityPagerAdapter extends FragmentPagerAdapter {
    private final int PAGE_COUNT = 2;
    private Context context;

    MainActivityPagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.context = context;
    }

    @Override
    public Fragment getItem(int i) {
        switch (i){
            case 0:
                return new ExploreFragment();
            case 1:
                return new LocalFragment();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return PAGE_COUNT;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {

            case 0:
                return context.getString(R.string.explore);

            case 1:
                return context.getString(R.string.local);

            default:
                return null;
        }
    }
}
