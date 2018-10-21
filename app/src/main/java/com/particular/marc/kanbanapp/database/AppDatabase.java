package com.particular.marc.kanbanapp.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.particular.marc.kanbanapp.model.Repo;

/**
 * Implementation of a RoomDatabase
 */
@Database(entities = Repo.class, version = 1)
public abstract class AppDatabase extends RoomDatabase {
}
