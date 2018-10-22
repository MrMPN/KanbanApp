package com.particular.marc.kanbanapp.database;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.particular.marc.kanbanapp.model.Issue;

import java.util.List;

/**
 * Dao interface for Room to implement the sql methods relating to Issue
 * Will query from AppDatabase
 */
@Dao
public interface IssueDao {

    @Insert(onConflict=OnConflictStrategy.IGNORE)
    void insert(List<Issue> issues);

    @Update
    void update(Issue... issues);

    @Query("DELETE FROM issue WHERE repo_id = :repoId")
    void deleteIssuesByRepo (int repoId);

    @Query("SELECT * FROM issue WHERE repo_id = :repoId ORDER BY created_at COLLATE NOCASE DESC")
    LiveData<List<Issue>> getIssuesByRepo (int repoId);
}