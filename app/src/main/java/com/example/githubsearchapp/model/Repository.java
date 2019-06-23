package com.example.githubsearchapp.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Repository {

    @SerializedName("owner")
    @Expose
    private Owner owner;

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("description")
    @Expose
    private String description;

    @SerializedName("forks_count")
    @Expose
    private String forkCount;

    @SerializedName("full_name")
    @Expose
    private String fullName;

    @SerializedName("watchers")
    @Expose
    private String watchersCount;

    public Repository(Owner owner, String name, String description, String forkCount, String fullName, String watchersCount) {
        this.owner = owner;
        this.name = name;
        this.description = description;
        this.forkCount = forkCount;
        this.fullName = fullName;
        this.watchersCount = watchersCount;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getForkCount() {
        return forkCount;
    }

    public void setForkCount(String forkCount) {
        this.forkCount = forkCount;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getWatchersCount() {
        return watchersCount;
    }

    public void setWatchersCount(String watchersCount) {
        this.watchersCount = watchersCount;
    }
}
