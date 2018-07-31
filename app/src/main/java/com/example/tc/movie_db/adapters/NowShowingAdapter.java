package com.example.tc.movie_db.adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tc.movie_db.activities.MovieDetailsActivity;
import com.example.tc.movie_db.MovieViewHolder;
import com.example.tc.movie_db.R;
import com.example.tc.movie_db.movies.InitialiseMovieGenres;
import com.example.tc.movie_db.movies.Result;
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
        //imageButton=imagelayout.findViewById(R.id.favbutton);
        return new MovieViewHolder(imagelayout);
    }

    @Override
    public void onBindViewHolder(@NonNull final MovieViewHolder holder, final int position) {
        final Result movie=movies.get(position);

        //1)
        holder.title.setText(movie.getTitle());

        //2)
        String backdrop_path=movie.getBackdropPath();
        Picasso.get().load("https://image.tmdb.org/t/p/w780"+backdrop_path).resize(950,600).into(holder.image);

        //3)
        if (movie.getVoteAverage()>0) {
            if (movie.getVoteAverage()<10.0D) {
                Spannable spannable = new SpannableString(movie.getVoteAverage() + "★");
                spannable.setSpan(new ForegroundColorSpan(Color.parseColor("#fff2e41f")), 3, 4, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                holder.rating.setText(spannable);
            }
            else{
                Spannable spannable = new SpannableString(movie.getVoteAverage() + "★");
                spannable.setSpan(new ForegroundColorSpan(Color.parseColor("#fff2e41f")), 4, 5, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                holder.rating.setText(spannable);
            }
        }
        else {
            Spannable spannable = new SpannableString("N/A"+ "★");
            spannable.setSpan(new ForegroundColorSpan(Color.parseColor("#fff2e41f")), 3, 4, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            holder.rating.setText(spannable);
        }

        //4)
        if(movies.get(position).getIsfav())
            holder.imageButton.setImageResource(R.drawable.ic_favorite_black_24dp);
        else
            holder.imageButton.setImageResource(R.drawable.ic_favorite_border_black_24dp);

        //5)
        setGenres(holder,movies.get(position));

        //6)
        holder.imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(movies.get(position).getIsfav()){
                    holder.imageButton.setImageResource(R.drawable.ic_favorite_border_black_24dp);
                    movies.get(position).setIsfav(false);
                }else{
                    holder.imageButton.setImageResource(R.drawable.ic_favorite_black_24dp);
                    movies.get(position).setIsfav(true);
                }
            }
        });

        //7)
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, MovieDetailsActivity.class);
                intent.putExtra("movie_id",movies.get(position).getId());
                intent.putExtra("movie_title",movies.get(position).getTitle());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    public void setGenres(MovieViewHolder holder,Result movie){
        String genrestring=" ";
        if (movie.getGenreIds().size()==0)
            genrestring="(Not Available)";
        else {
            for (int i = 0; i < movie.getGenreIds().size(); i++)
                genrestring += InitialiseMovieGenres.getGenreName(movie.getGenreIds().get(i).intValue()) + ", ";
        }
        if (!genrestring.equals("(Not Available)"))
            holder.genre.setText(genrestring.substring(0,genrestring.length()-2));//to not display ", "
        else
            holder.genre.setText(genrestring);
    }
}
