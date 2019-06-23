package com.example.githubsearchapp.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SubscribersResponse {

    @SerializedName("subscribers_count")
    @Expose
    private String subscribersCount;

    public String getSubscribersCount() {
        return subscribersCount;
    }

    public void setSubscribersCount(String subscribersCount) {
        this.subscribersCount = subscribersCount;
    }
}
