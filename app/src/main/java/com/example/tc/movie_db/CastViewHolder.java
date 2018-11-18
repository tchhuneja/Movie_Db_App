package com.example.tc.movie_db;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


public class CastViewHolder extends RecyclerView.ViewHolder {

    public ImageView castImageView;
    public TextView castName,castAs;

    public CastViewHolder(View itemView) {
        super(itemView);
        this.castImageView=itemView.findViewById(R.id.imageview_cast);
        this.castName=itemView.findViewById(R.id.cast_name);
        this.castAs=itemView.findViewById(R.id.cast_as);
    }
}
