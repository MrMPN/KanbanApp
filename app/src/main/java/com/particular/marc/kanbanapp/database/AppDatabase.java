package com.particular.marc.kanbanapp.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.particular.marc.kanbanapp.model.Issue;
import com.particular.marc.kanbanapp.model.Repo;

/**
 * Implementation of a RoomDatabase
 */
@Database(entities = { Repo.class, Issue.class }, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    private static AppDatabase instance;

    public static synchronized AppDatabase getInstance(final Context context){
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    AppDatabase.class, "app-database")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }
}
