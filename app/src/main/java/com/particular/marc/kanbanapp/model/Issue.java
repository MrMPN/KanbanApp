package com.particular.marc.kanbanapp.model;

import android.arch.persistence.room.Entity;

/**
 * A class that represents the issues of a given repository.
 * Both Room and Retrofit will use it
 */
@Entity(tableName = "issue")
public class Issue {
}
