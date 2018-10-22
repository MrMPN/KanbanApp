package com.particular.marc.kanbanapp.database;

import android.arch.paging.DataSource;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.particular.marc.kanbanapp.model.Repo;

import java.util.List;

/**
 * Dao interface for Room to implement the sql methods we define.
 * Will query from AppDatabase
 */
@Dao
public interface RepoDao {

    @Insert(onConflict=OnConflictStrategy.IGNORE)
    void insert(List<Repo> repos);

    @Update
    void update(Repo... repo);

    @Query("SELECT * FROM repo ORDER BY full_name COLLATE NOCASE")
    DataSource.Factory<Integer, Repo> getAllRepos();

}
