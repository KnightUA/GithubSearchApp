package com.example.githubsearchapp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.githubsearchapp.controller.RepositoryActivity;
import com.example.githubsearchapp.model.Repository;
import com.squareup.picasso.Picasso;

import java.util.List;

public class RepositoryAdapter extends RecyclerView.Adapter<RepositoryAdapter.ViewHolder> {

    private List<Repository> repositories;
    private Context context;

    public RepositoryAdapter(Context context, List<Repository> repositories) {

        this.context = context;
        this.repositories = repositories;
    }

    @NonNull
    @Override
    public RepositoryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.repository_item_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RepositoryAdapter.ViewHolder holder, int position) {

        holder.repositoryName.setText(repositories.get(position).getName());
        holder.description.setText(repositories.get(position).getDescription());
        holder.forkCount.setText(repositories.get(position).getForkCount());

        Picasso.get()
                .load(repositories.get(position).getOwner().getAvatarUrl())
                .placeholder(R.drawable.ic_default_image)
                .into(holder.ownerAvatar);
    }

    @Override
    public int getItemCount() {

        return repositories.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView repositoryName;
        private TextView description;
        private TextView forkCount;
        private ImageView ownerAvatar;

        private static final String FULL_NAME_TAG = "full_name";
        private static final String AVATAR_TAG = "avatar_url";
        private static final String REPOSITORY_NAME_TAG = "repository_name_url";
        private static final String WATCHER_TAG = "watchers";
        private static final String DESCIPTION_TAG = "description";

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            repositoryName = (TextView) itemView.findViewById(R.id.name);
            description = (TextView) itemView.findViewById(R.id.description);
            forkCount = (TextView) itemView.findViewById(R.id.numberOfForks);
            ownerAvatar = (ImageView) itemView.findViewById(R.id.avatarImg);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int pos = getAdapterPosition();
                    if (pos != RecyclerView.NO_POSITION) {
                        Repository clickedRepository = repositories.get(pos);

                        Intent intent = new Intent(context, RepositoryActivity.class);

                        intent.putExtra(FULL_NAME_TAG, repositories.get(pos).getFullName());
                        intent.putExtra(AVATAR_TAG, repositories.get(pos).getOwner().getAvatarUrl());
                        intent.putExtra(REPOSITORY_NAME_TAG, repositories.get(pos).getName());
                        intent.putExtra(WATCHER_TAG, repositories.get(pos).getWatchersCount());
                        intent.putExtra(DESCIPTION_TAG, repositories.get(pos).getDescription());
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                        context.startActivity(intent);

                        Toast.makeText(view.getContext(), "You clicked " + clickedRepository.getName(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }
}
