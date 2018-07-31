package com.example.tc.movie_db;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MovieViewHolder extends RecyclerView.ViewHolder {
    public TextView title,rating,genre;
    public ImageView image;
    public ImageButton imageButton;
    public CardView cardView;

    public MovieViewHolder(View itemView) {
        super(itemView);
        this.cardView=itemView.findViewById(R.id.initialcardview);
        this.image=itemView.findViewById(R.id.image);
        this.title = itemView.findViewById(R.id.title);
        this.genre=itemView.findViewById(R.id.genre);
        this.rating=itemView.findViewById(R.id.rating);
        this.imageButton=itemView.findViewById(R.id.favbutton);
    }

}
