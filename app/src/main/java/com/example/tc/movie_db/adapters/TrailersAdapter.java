package com.example.tc.movie_db.adapters;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tc.movie_db.R;
import com.example.tc.movie_db.TrailersViewHolder;
import com.example.tc.movie_db.trailers.Result;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import static android.support.v4.content.ContextCompat.startActivity;

public class TrailersAdapter extends RecyclerView.Adapter<TrailersViewHolder> {

    private ArrayList<Result> trailers;
    private Context context;

    public TrailersAdapter(Context context, ArrayList<Result> list) {
        this.context=context;
        this.trailers=list;
    }

    @NonNull
    @Override
    public TrailersViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View imageLayout=inflater.inflate(R.layout.trailer_image,parent,false);
        return new TrailersViewHolder(imageLayout);
    }

    @Override
    public void onBindViewHolder(@NonNull TrailersViewHolder holder, int position) {
        final Result trailer=trailers.get(position);

        String key=trailer.getKey();
        Picasso.get().load("https://img.youtube.com/vi/"+key+"/maxresdefault.jpg").resize(700,500).into(holder.trailerImage);

        holder.trailerImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Intent.ACTION_VIEW,Uri.parse("http://www.youtube.com/watch?v="+trailer.getKey()));
                startActivity(context,intent,null);
            }
        });
    }

    @Override
    public int getItemCount() { return trailers.size(); }
}
