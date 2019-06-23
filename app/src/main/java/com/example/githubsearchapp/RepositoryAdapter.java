package com.example.githubsearchapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

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
                        Repository clickedData = repositories.get(pos);
                        Toast.makeText(view.getContext(), "You clicked " + clickedData.getName(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }
}
