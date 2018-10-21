package com.particular.marc.kanbanapp;

import android.arch.paging.PagedListAdapter;

import com.particular.marc.kanbanapp.model.Issue;

/**
 * PagedListAdapter that will take a PagedList and display it into the RecyclerView
 */
public class KanbanListAdapter extends PagedListAdapter<Issue, KanbanListAdapter.ViewHolder> {
}
