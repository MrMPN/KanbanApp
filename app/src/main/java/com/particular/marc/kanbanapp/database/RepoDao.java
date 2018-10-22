package com.particular.marc.kanbanapp.database;

import android.arch.paging.DataSource;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.particular.marc.kanbanapp.model.Repo;

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

    @Query("SELECT * FROM repo ORDER BY full_name COLLATE NOCASE")
    DataSource.Factory<Integer, Repo> getAllRepos();

}
