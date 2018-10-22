package com.particular.marc.kanbanapp.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

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
    private int number;
    private int comments;

    @ColumnInfo(name = "created_at")
    @SerializedName("created_at")
    private String createdAt;

    @ColumnInfo(name = "repo_id")
    @Expose(serialize = false, deserialize = false)
    private int repoId;

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

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getComments() {
        return comments;
    }

    public void setComments(int comments) {
        this.comments = comments;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public int getRepoId() {
        return repoId;
    }

    public void setRepoId(int repoId) {
        this.repoId = repoId;
    }

    public int getBoard() {
        return board;
    }

    public void setBoard(int board) throws IllegalArgumentException {
        if (board < 0 || board > 3){
            throw new IllegalArgumentException("Nonexistent board");
        }
        this.board = board;
    }

    @Override
    public String toString() {
        return "Issue{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", body='" + body + '\'' +
                ", number=" + number +
                ", comments=" + comments +
                ", createdAt='" + createdAt + '\'' +
                ", repoId=" + repoId +
                ", board=" + board +
                '}';
    }
}
