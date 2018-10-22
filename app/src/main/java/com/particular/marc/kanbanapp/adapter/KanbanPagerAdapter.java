package com.particular.marc.kanbanapp.adapter;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.particular.marc.kanbanapp.R;
import com.particular.marc.kanbanapp.fragments.KanbanBoardFragment;
import com.particular.marc.kanbanapp.viewmodel.KanbanViewModel;

/**
 * FragmentPagerAdapter subclass used to populate the KanbanActivity ViewPager, so the user can
 * swipe between the Backlog, Next, Doing and Done fragments
 */
public class KanbanPagerAdapter extends FragmentPagerAdapter {
    private static final String TAG = "KanbanPagerAdapter";
    private final int PAGE_COUNT = 4;
    private Context context;

    public KanbanPagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.context = context;
    }

    @Override
    public Fragment getItem(int i) {
        switch (i){
            case 0:
                return fragmentBuilder(KanbanViewModel.BACKLOG);
            case 1:
                return fragmentBuilder(KanbanViewModel.NEXT);
            case 2:
                return fragmentBuilder(KanbanViewModel.DOING);
            case 3:
                return fragmentBuilder(KanbanViewModel.DONE);
            default:
                return null;
        }
    }

    private KanbanBoardFragment fragmentBuilder(int i){
        Bundle bundle = new Bundle();
        KanbanBoardFragment fragment = new KanbanBoardFragment();
        bundle.putInt("board", i);
        fragment.setArguments(bundle);
        return fragment;
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
