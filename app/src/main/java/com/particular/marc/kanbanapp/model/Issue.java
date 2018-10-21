package com.particular.marc.kanbanapp.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import com.google.gson.annotations.Expose;

/**
 * A class that represents the issues of a given repository.
 * Both Room and Retrofit will use it
 */
@Entity(tableName = "issue")
public class Issue {
    @PrimaryKey
    private int id;

    private String title;
    private String body;

    @Expose(serialize = false, deserialize = false)
    private int repo_id;

    @Expose(serialize = false, deserialize = false)
    private int board = 0;

    public Issue() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public int getRepo_id() {
        return repo_id;
    }

    public void setRepo_id(int repo_id) {
        this.repo_id = repo_id;
    }

    public int getBoard() {
        return board;
    }

    public void setBoard(int board) {
        this.board = board;
    }
}
