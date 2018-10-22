package com.particular.marc.kanbanapp.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.recyclerview.extensions.ListAdapter;
import android.support.v7.util.DiffUtil;
import android.support.v7.util.DiffUtil.ItemCallback;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.particular.marc.kanbanapp.R;
import com.particular.marc.kanbanapp.adapter.KanbanListAdapter.ViewHolder;
import com.particular.marc.kanbanapp.model.Issue;

/**
 * PagedListAdapter that will take a PagedList and display it into the RecyclerView
 */
public class KanbanListAdapter extends ListAdapter<Issue, ViewHolder> {
    private static final String TAG = "KanbanListAdapter";
    private LayoutInflater inflater;
    private int board;

    public KanbanListAdapter(Context context, int board) {
        super(DIFF_CALLBACK);
        this.board = board;
        inflater = LayoutInflater.from(context);
    }


    @NonNull
    @Override
    public KanbanListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = inflater.inflate(R.layout.recycler_view_item, viewGroup, false);
        return new KanbanListAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull KanbanListAdapter.ViewHolder viewHolder, int i) {
        Issue issue = getItem(i);
        if (issue != null){
            viewHolder.titleTextView.setText(issue.getTitle());
            viewHolder.dateTextView.setText(issue.getCreatedAt());
            viewHolder.numberTextView.setText(String.valueOf(issue.getNumber()));
            viewHolder.commentsTextView.setText(String.valueOf(issue.getComments()));
        }
    }

    private static DiffUtil.ItemCallback<Issue> DIFF_CALLBACK =
            new ItemCallback<Issue>() {
                @Override
                public boolean areItemsTheSame(@NonNull Issue issue, @NonNull Issue t1) {
                    return issue.getId() == t1.getId();
                }

                @Override
                public boolean areContentsTheSame(@NonNull Issue issue, @NonNull Issue t1) {
                    return issue.equals(t1);
                }
            };

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView titleTextView;
        TextView dateTextView;
        TextView numberTextView;
        TextView commentsTextView;
        ImageView previous;
        ImageView next;
        ViewHolder(@NonNull View itemView) {
            super(itemView);
            initViews();
        }

        private void initViews(){
            titleTextView = itemView.findViewById(R.id.issue_title);
            dateTextView = itemView.findViewById(R.id.issue_date);
            numberTextView = itemView.findViewById(R.id.issue_number);
            commentsTextView = itemView.findViewById(R.id.issue_comments);
            previous = itemView.findViewById(R.id.previous_board);
            next = itemView.findViewById(R.id.next_board);
        }
    }
}
