package com.example.tc.movie_db;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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
        Picasso.get().load("https://image.tmdb.org/t/p/w780"+backdrop_path).resize(1000,600).into(holder.image);
        //Picasso.get().load()

        //3)
        holder.rating.setText(movie.getVoteAverage()+ "★");

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
