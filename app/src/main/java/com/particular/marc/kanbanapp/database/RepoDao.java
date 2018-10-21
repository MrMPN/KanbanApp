package com.particular.marc.kanbanapp.database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.particular.marc.kanbanapp.model.Repo;
import com.particular.marc.kanbanapp.model.RepoAndAllissues;

import java.util.List;

/**
 * Dao interface for Room to implement the sql methods we define.
 * Will query from AppDatabase
 */
@Dao
public interface RepoDao {

    @Insert
    void insert(Repo repo);

    @Update
    void update(Repo repo);

    @Query("SELECT * FROM repo")
    List<Repo> getAllRepos();

    @Query("SELECT * FROM repo WHERE id = :repoId")
    List<RepoAndAllissues> getIssuesByRepo (int repoId);
}
