package com.example.tc.movie_db.adapters;

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

import com.example.tc.movie_db.MovieViewHolder;
import com.example.tc.movie_db.R;
import com.example.tc.movie_db.movies.InitialiseMovieGenres;
import com.example.tc.movie_db.tvshows.InitialiseTVGenres;
import com.example.tc.movie_db.tvshows.Result;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class TvAdapter extends RecyclerView.Adapter<MovieViewHolder> {

    Context context;
    ArrayList<Result> tvs;
    public TvAdapter(Context context,ArrayList<Result> tvs) {
        this.context=context;
        this.tvs=tvs;
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View imagelayout=inflater.inflate(R.layout.imagelayout,parent,false);
        return new MovieViewHolder(imagelayout);
    }

    @Override
    public void onBindViewHolder(@NonNull final MovieViewHolder holder, final int position) {
        com.example.tc.movie_db.tvshows.Result tv=tvs.get(position);

        //1)
        holder.title.setText(tv.getName());

        //2)
        String backdrop_path=tv.getBackdropPath();
        Picasso.get().load("https://image.tmdb.org/t/p/w780"+backdrop_path).resize(973,600).into(holder.image);

        //3)
        if (tv.getVoteAverage()>0) {
            if (tv.getVoteAverage()<10.0D) {
                Spannable spannable = new SpannableString(tv.getVoteAverage() + "★");
                spannable.setSpan(new ForegroundColorSpan(Color.parseColor("#fff2e41f")), 3, 4, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                holder.rating.setText(spannable);
            }
            else{
                Spannable spannable = new SpannableString(tv.getVoteAverage() + "★");
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
        setGenres(holder,tvs.get(position));

        //4)
        if(tvs.get(position).getIsFav())
            holder.imageButton.setImageResource(R.drawable.ic_favorite_black_24dp);
        else
            holder.imageButton.setImageResource(R.drawable.ic_favorite_border_black_24dp);

        //5)
        holder.imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(tvs.get(position).getIsFav()){
                    holder.imageButton.setImageResource(R.drawable.ic_favorite_border_black_24dp);
                    tvs.get(position).setIsFav(false);
                }else{
                    holder.imageButton.setImageResource(R.drawable.ic_favorite_black_24dp);
                    tvs.get(position).setIsFav(true);
                }
            }
        });
    }

    @Override
    public int getItemCount() {return tvs.size();}

    public void setGenres(MovieViewHolder holder, com.example.tc.movie_db.tvshows.Result movie){
        String genrestring=" ";
        String temp;
        if (movie.getGenreIds().size()==0)
            genrestring="(Not Available)";
        else {
            for (int i = 0; i < movie.getGenreIds().size(); i++) {
                temp = InitialiseTVGenres.getGenreName(movie.getGenreIds().get(i).intValue());
                if (temp != null)
                    genrestring += temp+", ";
            }
        }
        if (!genrestring.equals("(Not Available)"))
            holder.genre.setText(genrestring.substring(0,genrestring.length()-2));//to not display ", "
        else
            holder.genre.setText(genrestring);
    }

}
