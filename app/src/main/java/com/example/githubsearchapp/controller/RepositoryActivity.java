package com.example.githubsearchapp.controller;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.githubsearchapp.R;
import com.example.githubsearchapp.SubscriberAdapter;
import com.example.githubsearchapp.api.JSONPlaceHolderApi;
import com.example.githubsearchapp.api.NetworkService;
import com.example.githubsearchapp.model.Subscriber;
import com.example.githubsearchapp.model.SubscribersResponse;
import com.squareup.picasso.Picasso;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RepositoryActivity extends AppCompatActivity {

    private static final String FULL_NAME_TAG = "full_name";
    private static final String AVATAR_TAG = "avatar_url";
    private static final String REPOSITORY_NAME_TAG = "repository_name_url";
    private static final String WATCHER_TAG = "watchers";
    private static final String DESCIPTION_TAG = "description";

    private RecyclerView mRecyclerView;
    private ImageView mAvatarImg;
    private TextView mRepositoryName;
    private TextView mDescription;
    private TextView mWatchersCount;
    private TextView mSubscribersCount;
    androidx.appcompat.widget.Toolbar mToolbar;
    private String fullName = "";
    private String[] parts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repository);

        initToolbar();
        initViews();
        setExtrasToViews();

        loadJSON();
    }

    private void initToolbar() {
        mToolbar = (androidx.appcompat.widget.Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Repository");
    }

    private void initViews() {
        mAvatarImg = (ImageView) findViewById(R.id.avatarImg);

        mRepositoryName = (TextView) findViewById(R.id.name);
        mWatchersCount = (TextView) findViewById(R.id.watchersCount);
        mDescription = (TextView) findViewById(R.id.description);
        mSubscribersCount = (TextView) findViewById(R.id.subscribersCount);

        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        mRecyclerView.smoothScrollToPosition(0);
    }

    private void setExtrasToViews() {
        fullName = getIntent().getExtras().getString(FULL_NAME_TAG);
        parts = fullName.split("/");

        String avatarUrl = getIntent().getExtras().getString(AVATAR_TAG);
        String repositoryName = getIntent().getExtras().getString(REPOSITORY_NAME_TAG);
        String countWatchers = getIntent().getExtras().getString(WATCHER_TAG);
        String description = getIntent().getExtras().getString(DESCIPTION_TAG);

        Picasso.get()
                .load(avatarUrl)
                .placeholder(R.drawable.ic_default_image)
                .into(mAvatarImg);

        mRepositoryName.setText(repositoryName);
        mWatchersCount.setText(countWatchers);
        mDescription.setText(description);
    }

    private void loadJSON() {
        try {
            JSONPlaceHolderApi apiService = NetworkService.getInstance().getJSONApi();
            Call<List<Subscriber>> call = apiService.getSubscribers(parts[0], parts[1]);

            call.enqueue(new Callback<List<Subscriber>>() {
                @Override
                public void onResponse(Call<List<Subscriber>> call, Response<List<Subscriber>> response) {
                    List<Subscriber> subscribers = response.body();
                    mRecyclerView.setAdapter(new SubscriberAdapter(getApplicationContext(), subscribers));
                    mRecyclerView.smoothScrollToPosition(0);
                }

                @Override
                public void onFailure(Call<List<Subscriber>> call, Throwable t) {
                    Log.d("Error", t.getMessage());
                    Toast.makeText(RepositoryActivity.this, "Error: Fetching Data!", Toast.LENGTH_SHORT).show();
                }
            });
        } catch (Exception e) {
            Log.d("Error", e.getMessage());
            Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            JSONPlaceHolderApi apiService = NetworkService.getInstance().getJSONApi();
            Call<SubscribersResponse> call = apiService.getSubscribersCount(parts[0], parts[1]);
            Log.d("URL", call.request().url().toString());
            call.enqueue(new Callback<SubscribersResponse>() {
                @Override
                public void onResponse(Call<SubscribersResponse> call, Response<SubscribersResponse> response) {
                    mSubscribersCount.setText(response.body().getSubscribersCount());
                }

                @Override
                public void onFailure(Call<SubscribersResponse> call, Throwable t) {
                    Log.d("Error", t.getMessage());
                    Toast.makeText(RepositoryActivity.this, "Error: Fetching Data!", Toast.LENGTH_SHORT).show();
                }
            });
        } catch (Exception e) {
            Log.d("Error", e.getMessage());
            Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return super.onSupportNavigateUp();
    }
}
