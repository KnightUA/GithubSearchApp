package com.example.githubsearchapp.api;

import com.example.githubsearchapp.model.RepositoryResponse;
import com.example.githubsearchapp.model.Subscriber;
import com.example.githubsearchapp.model.SubscribersResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface JSONPlaceHolderApi {

    @GET("search/repositories")
    Call<RepositoryResponse> getRepositories(@Query("q") String query);

    @GET("repos/{first}/{second}/subscribers")
    Call<List<Subscriber>> getSubscribers(@Path("first") String first, @Path("second") String second);

    @GET("https://api.github.com/repos/{first}/{second}")
    Call<SubscribersResponse> getSubscribersCount(@Path("first") String first, @Path("second") String second);

}
