package com.example.tc.movie_db;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class NowShowingAdapter extends RecyclerView.Adapter<MovieViewHolder> {

    Context context;
    ArrayList<Result> movies;

    public NowShowingAdapter(Context context, ArrayList<Result> movies) {
        this.context = context;
        this.movies = movies;
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View imagelayout=inflater.inflate(R.layout.imagelayout,parent,false);
        return new MovieViewHolder(imagelayout);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
        Result movie=movies.get(position);

        //1)
        holder.title.setText(movie.getTitle());

        //2)
        String backdrop_path=movie.getBackdropPath();
        Picasso.get().load("https://image.tmdb.org/t/p/w780"+backdrop_path).resize(950,600).into(holder.image);

        //3)
        Spannable spannable=new SpannableString(movie.getVoteAverage()+"★");
        spannable.setSpan(new ForegroundColorSpan(Color.parseColor("#fff2e41f")),3,4,Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        holder.rating.setText(spannable);

        //4)
        setGenres(holder,movies.get(position));

    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    public void setGenres(MovieViewHolder holder,Result movie){
        String genrestring=" ";
        for (int i=0;i<movie.getGenreIds().size();i++){
            genrestring+=InitialiseMovieGenres.getGenreName(movie.getGenreIds().get(i).intValue()) + ", ";
        }
        holder.genre.setText(genrestring.substring(0,genrestring.length()-2));//to not display ", "
    }
}
