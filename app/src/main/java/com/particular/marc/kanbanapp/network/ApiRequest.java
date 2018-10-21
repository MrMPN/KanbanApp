package com.particular.marc.kanbanapp.network;

import com.particular.marc.kanbanapp.model.Issue;
import com.particular.marc.kanbanapp.model.Repo;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Interface that defines the methods to query the GitHub API.
 * Retrofit will implement it
 */
public interface ApiRequest {
    @GET("users/JakeWharton/repos")
    Call<List<Repo>> getAllRepos(
            @Query("page") int page,
            @Query("per_page") int perPage
            );

    @GET("/repos/JakeWharton/{repoName}/issues")
    Call<List<Issue>> getAllIssuesByRepo(
            @Path("repoName") String name,
            @Query("page") int page,
            @Query("per_page") int perPage
    );
}
