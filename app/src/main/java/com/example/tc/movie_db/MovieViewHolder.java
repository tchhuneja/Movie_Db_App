package com.example.tc.movie_db;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MovieViewHolder extends RecyclerView.ViewHolder {
    TextView title,rating,genre;
    ImageView image;


    public MovieViewHolder(View itemView) {
        super(itemView);
        this.image=itemView.findViewById(R.id.image);
        this.title = itemView.findViewById(R.id.title);
        this.genre=itemView.findViewById(R.id.genre);
        this.rating=itemView.findViewById(R.id.rating);
    }
}
