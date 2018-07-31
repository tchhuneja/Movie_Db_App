package com.example.tc.movie_db;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

public class TrailersViewHolder extends RecyclerView.ViewHolder {

    public ImageView trailerImage;
    public CardView trailer_card;

    public TrailersViewHolder(View itemView) {

        super(itemView);
        trailer_card=itemView.findViewById(R.id.trailer_card);
        trailerImage=itemView.findViewById(R.id.trailer_image);
    }
}
