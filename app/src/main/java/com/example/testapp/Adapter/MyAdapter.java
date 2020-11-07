package com.example.testapp.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.testapp.Network.Post;
import com.example.testapp.R;
import com.example.testapp.SecondActivity;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    private Context context;
    private List<Post> posts;

    public MyAdapter(Context context, List<Post> posts) {
        this.context = context;
        this.posts = posts;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_posts, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.id.setText("ID: " + posts.get(position).getId());
        holder.title.setText(posts.get(position).getTitle());
        holder.body.setText(posts.get(position).getText());

        holder.cardView.setOnClickListener(v -> {
            Intent intent = new Intent(context, SecondActivity.class);
            intent.putExtra("id", Integer.toString(posts.get(position).getId()));
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return posts.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView id;
        TextView title;
        TextView body;
        CardView cardView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            cardView = itemView.findViewById(R.id.card_view_posts);
            id = itemView.findViewById(R.id.txt_id);
            title = itemView.findViewById(R.id.txt_title);
            body = itemView.findViewById(R.id.txt_body);
        }
    }
}
