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

import com.example.githubsearchapp.model.Subscriber;
import com.squareup.picasso.Picasso;

import java.util.List;

public class SubscriberAdapter extends RecyclerView.Adapter<SubscriberAdapter.ViewHolder> {

    private List<Subscriber> subscribers;
    private Context context;

    public SubscriberAdapter(Context context, List<Subscriber> subscribers) {
        this.subscribers = subscribers;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.subscriber_item_list, parent, false);
        return new SubscriberAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.nameSubscriber.setText(subscribers.get(position).getLogin());

        Picasso.get()
                .load(subscribers.get(position).getAvatarUrl())
                .placeholder(R.drawable.ic_default_image)
                .into(holder.avatarSubscriber);
    }

    @Override
    public int getItemCount() {
        if (subscribers != null)
            return subscribers.size();
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView nameSubscriber;
        private ImageView avatarSubscriber;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            nameSubscriber = (TextView) itemView.findViewById(R.id.name);
            avatarSubscriber = (ImageView) itemView.findViewById(R.id.avatarImg);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int pos = getAdapterPosition();
                    if (pos != RecyclerView.NO_POSITION) {
                        Subscriber clickedSubscriber = subscribers.get(pos);

                        Toast.makeText(view.getContext(), "You clicked " + clickedSubscriber.getLogin(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }
}
