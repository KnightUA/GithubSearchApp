package com.example.githubsearchapp.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Subscriber {

    @SerializedName("avatar_url")
    @Expose
    private String avatarUrl;

    @SerializedName("login")
    @Expose
    private String login;

    public Subscriber(String avatarUrl, String login) {
        this.avatarUrl = avatarUrl;
        this.login = login;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }
}
