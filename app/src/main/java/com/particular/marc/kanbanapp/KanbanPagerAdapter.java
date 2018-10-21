package com.particular.marc.kanbanapp;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.particular.marc.kanbanapp.fragments.BacklogFragment;
import com.particular.marc.kanbanapp.fragments.DoingFragment;
import com.particular.marc.kanbanapp.fragments.DoneFragment;
import com.particular.marc.kanbanapp.fragments.NextFragment;

/**
 * FragmentPagerAdapter subclass used to populate the KanbanActivity ViewPager, so the user can
 * swipe between the Backlog, Next, Doing and Done fragments
 */
public class KanbanPagerAdapter extends FragmentPagerAdapter {
    private final int PAGE_COUNT = 4;
    private Context context;

    KanbanPagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.context = context;
    }

    @Override
    public Fragment getItem(int i) {
        switch (i){
            case 0:
                return new BacklogFragment();
            case 1:
                return new NextFragment();
            case 2:
                return new DoingFragment();
            case 3:
                return new DoneFragment();
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
                return context.getString(R.string.backlog);
            case 1:
                return context.getString(R.string.next);
            case 2:
                return context.getString(R.string.doing);
            case 3:
                return context.getString(R.string.done);
            default:
                return null;
        }
    }
}

