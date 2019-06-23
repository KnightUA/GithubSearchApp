package com.example.githubsearchapp.api;

import com.example.githubsearchapp.model.RepositoryResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface JSONPlaceHolderApi {

    @GET("/search/repositories")
    Call<RepositoryResponse> getRepositories(@Query("q") String query);
}
