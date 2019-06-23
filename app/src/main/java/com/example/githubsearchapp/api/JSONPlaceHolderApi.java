package com.example.githubsearchapp.api;

import com.example.githubsearchapp.model.RepositoryResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface JSONPlaceHolderApi {

    /*TODO add search in repositories by user request*/

    @GET("/search/repositories?q=language:java")
    Call<RepositoryResponse> getRepositories();
}
