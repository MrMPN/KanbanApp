package com.particular.marc.kanbanapp.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Class that represents a repository.
 * Both Room and Retrofit will use it
 */
@Entity(tableName = "repo")
public class Repo {
    @PrimaryKey
    private int id;

    @ColumnInfo(name = "full_name")
    @SerializedName("full_name")
    private String fullName;

    @ColumnInfo(name = "issues_count")
    @SerializedName("open_issues_count")
    private int issuesCount;

    @ColumnInfo(name = "has_kanban")
    @Expose(serialize = false, deserialize = false)
    private boolean hasKanban = false;

    @Ignore
    public Repo(int id, String fullName, int issuesCount) {
        this.id = id;
        this.fullName = fullName;
        this.issuesCount = issuesCount;
    }

    public Repo() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public int getIssuesCount() {
        return issuesCount;
    }

    public void setIssuesCount(int issuesCount) {
        this.issuesCount = issuesCount;
    }

    public boolean isHasKanban() {
        return hasKanban;
    }

    public void setHasKanban(boolean hasKanban) {
        this.hasKanban = hasKanban;
    }
}
