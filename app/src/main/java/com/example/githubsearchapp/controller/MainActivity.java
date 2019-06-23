package com.example.githubsearchapp.controller;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.githubsearchapp.R;
import com.example.githubsearchapp.RepositoryAdapter;
import com.example.githubsearchapp.api.JSONPlaceHolderApi;
import com.example.githubsearchapp.api.NetworkService;
import com.example.githubsearchapp.model.Repository;
import com.example.githubsearchapp.model.RepositoryResponse;
import com.google.android.material.snackbar.Snackbar;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ProgressDialog progressDialog;
    private SwipeRefreshLayout swipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipeRefresh);

        swipeRefreshLayout.setColorSchemeResources(android.R.color.holo_blue_light);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                loadJSON();
                Toast.makeText(MainActivity.this, "Github Repositories Refreshed", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initViews() {
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Fetching Github Repositories ...");
        progressDialog.setCancelable(false);
        progressDialog.show();

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerView.smoothScrollToPosition(0);
        loadJSON();
    }

    private void loadJSON() {
        try {
            JSONPlaceHolderApi apiService = NetworkService.getInstance().getJSONApi();
            Call<RepositoryResponse> call = apiService.getRepositories();
            call.enqueue(new Callback<RepositoryResponse>() {
                @Override
                public void onResponse(Call<RepositoryResponse> call, Response<RepositoryResponse> response) {
                    List<Repository> repositories = response.body().getRepositories();
                    recyclerView.setAdapter(new RepositoryAdapter(getApplicationContext(), repositories));
                    recyclerView.smoothScrollToPosition(0);
                    swipeRefreshLayout.setRefreshing(false);
                    progressDialog.hide();
                }

                @Override
                public void onFailure(Call<RepositoryResponse> call, Throwable t) {
                    Log.d("Error", t.getMessage());
                    Snackbar.make(swipeRefreshLayout, "Error: Fetching Data!", Snackbar.LENGTH_SHORT).show();
                    swipeRefreshLayout.setRefreshing(false);
                    progressDialog.hide();
                }
            });
        } catch (Exception e) {
            Log.d("Error", e.getMessage());
            Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT).show();
        }

    }
}
