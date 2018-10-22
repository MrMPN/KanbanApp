package com.particular.marc.kanbanapp;

import android.arch.paging.PagedListAdapter;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.util.DiffUtil;
import android.support.v7.util.DiffUtil.ItemCallback;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.particular.marc.kanbanapp.model.Repo;

/**
 * PagedListAdapter that will take a PagedList and display it into the RecyclerView
 */
public class RepoListAdapter extends PagedListAdapter<Repo, RepoListAdapter.ViewHolder> {
    private static final String TAG = "RepoListAdapter";
    final public static int EXPLORE = 0;
    final public static int LOCAL = 1;
    private LayoutInflater inflater;
    private int page;

    public RepoListAdapter(Context context, int page) {
        super(DIFF_CALLBACK);
        this.page = page;
        inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = inflater.inflate(R.layout.recycler_view_item, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Repo repo = getItem(i);
        if (repo != null){
            String[] processed = repo.getFullName().split("/");
            viewHolder.titleTextView.setText(processed[1]);
            viewHolder.authorTextView.setText(processed[0]);
        }
    }

    private static DiffUtil.ItemCallback<Repo> DIFF_CALLBACK =
            new ItemCallback<Repo>() {
                @Override
                public boolean areItemsTheSame(@NonNull Repo repo, @NonNull Repo t1) {
                    return repo.getId() == t1.getId();
                }

                @Override
                public boolean areContentsTheSame(@NonNull Repo repo, @NonNull Repo t1) {
                    return repo.equals(t1);
                }
            };

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView titleTextView;
        TextView authorTextView;
        ImageView addImage;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            titleTextView = itemView.findViewById(R.id.issue_title);
            authorTextView = itemView.findViewById(R.id.issue_date);
            addImage = itemView.findViewById(R.id.add_button);
        }
    }
}
