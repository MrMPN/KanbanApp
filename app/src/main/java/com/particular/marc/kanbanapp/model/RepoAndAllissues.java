package com.particular.marc.kanbanapp.model;

import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Relation;

import java.util.List;

/**
 * This class serves as a way for Room to know that Repo and Issue and related by id/repo_id field
 * This will enable us to get a list of issues by repo_id from our RepoDao
 */
public class RepoAndAllissues {

    @Embedded
    public Repo repo;
    @Relation (parentColumn = "id", entityColumn = "repo_id", entity = Issue.class)
    public List<Issue> issues;
}
