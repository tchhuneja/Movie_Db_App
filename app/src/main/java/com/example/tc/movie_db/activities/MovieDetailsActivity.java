package com.example.tc.movie_db.activities;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SnapHelper;
import android.support.v7.widget.Toolbar;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.view.Gravity;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tc.movie_db.R;
import com.example.tc.movie_db.adapters.TrailersAdapter;
import com.example.tc.movie_db.movies.InitialiseMovieGenres;
import com.example.tc.movie_db.movies.Movie;
import com.example.tc.movie_db.network.ApiClient;
import com.example.tc.movie_db.network.MovidbService;
import com.example.tc.movie_db.trailers.Trailers;
import com.github.rubensousa.gravitysnaphelper.GravitySnapHelper;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MovieDetailsActivity extends AppCompatActivity {

    ImageView backdropImageView,posterImageView;
    TextView genreTextView,overviewTextView,ratingTextView,releaseDateTextView,durationTextView;
    Toolbar toolbar1;
    String[] date;
    ArrayList<com.example.tc.movie_db.trailers.Result> trailerz=new ArrayList<>();
    RecyclerView trailer_recycler,castRecycler;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);
        toolbar1 = findViewById(R.id.toolbar1);
        setSupportActionBar(toolbar1);

        backdropImageView=findViewById(R.id.BackdropShower);
        genreTextView=findViewById(R.id.textView6);
        overviewTextView=findViewById(R.id.textView7);
        posterImageView=findViewById(R.id.imageView4);
        ratingTextView=findViewById(R.id.textView9);
        releaseDateTextView=findViewById(R.id.textView8);
        durationTextView=findViewById(R.id.textView10);

        Intent intent=getIntent();
        Long id=intent.getLongExtra("movie_id",-1);
        String title=intent.getStringExtra("movie_title");
        toolbar1.setTitle(title);

        final Retrofit retrofit=ApiClient.getRetrofit();

        MovidbService service=retrofit.create(MovidbService.class);
        Call<Movie> call=service.getMovieDetails(id);
        call.enqueue(new Callback<Movie>() {
            @Override
            public void onResponse(Call<Movie> call, Response<Movie> response) {
                Movie movie=response.body();

                String backdrop_path=movie.getBackdropPath();
                Picasso.get().load("https://image.tmdb.org/t/p/w1280"+backdrop_path).resize(425,295).into(backdropImageView);

                setGenres(movie);

                overviewTextView.setText(movie.getOverview());

                String poster_path=movie.getPosterPath();
                Picasso.get().load("https://image.tmdb.org/t/p/w342"+poster_path).into(posterImageView);

                Spannable spannable = new SpannableString(movie.getVoteAverage() + "/10.0");
                spannable.setSpan(new RelativeSizeSpan(1.5f), 0, 4, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                ratingTextView.setText(spannable);

                date=movie.getReleaseDate().split("-");
                Spannable spannable1=new SpannableString("Release Date: " + date[2]+"/" + date[1]+"/" + date[0]);
                spannable1.setSpan(new ForegroundColorSpan(Color.parseColor("#FF4081")),0,13,Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                releaseDateTextView.setText(spannable1);

                int hrs=movie.getRuntime().intValue()/60;
                int min=movie.getRuntime().intValue()%60;
                Spannable spannable2=new SpannableString("Run Time :" + hrs +" hours "+min+" minutes");
                spannable2.setSpan(new ForegroundColorSpan(Color.parseColor("#FF4081")),0,9,Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                durationTextView.setText(spannable2);
            }

            @Override
            public void onFailure(Call<Movie> call, Throwable t) {
            }
        });

        trailer_recycler=findViewById(R.id.trailer_recycler);

        final TrailersAdapter adapter=new TrailersAdapter(this,trailerz);

        trailer_recycler.setAdapter(adapter);
        trailer_recycler.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));

        SnapHelper snapHelper=new GravitySnapHelper(Gravity.START);
        snapHelper.attachToRecyclerView(trailer_recycler);

        MovidbService service1=retrofit.create(MovidbService.class);
        Call<Trailers> call1=service1.getMovieTrailers(id);
        call1.enqueue(new Callback<Trailers>() {
            @Override
            public void onResponse(Call<Trailers> call, Response<Trailers> response) {
                Trailers trailers=response.body();
                List<com.example.tc.movie_db.trailers.Result> movieTrailers=trailers.getResults();
                trailerz.clear();
                trailerz.addAll(movieTrailers);
                adapter.notifyDataSetChanged();
            }
            @Override
            public void onFailure(Call<Trailers> call, Throwable t) {

            }
        });

//        castRecycler=findViewById(R.id.cast_recycler);
//
//        final TrailersAdapter adapter=new TrailersAdapter(this,trailerz);
//
//        trailer_recycler.setAdapter(adapter);
//        trailer_recycler.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
//
//        SnapHelper snapHelper=new GravitySnapHelper(Gravity.START);
//        snapHelper.attachToRecyclerView(trailer_recycler);
//
//        MovidbService service1=retrofit.create(MovidbService.class);
//        Call<Trailers> call1=service1.getMovieTrailers(id);
//        call1.enqueue(new Callback<Trailers>() {
//            @Override
//            public void onResponse(Call<Trailers> call, Response<Trailers> response) {
//                Trailers trailers=response.body();
//                List<com.example.tc.movie_db.trailers.Result> movieTrailers=trailers.getResults();
//                trailerz.clear();
//                trailerz.addAll(movieTrailers);
//                adapter.notifyDataSetChanged();
//            }
//            @Override
//            public void onFailure(Call<Trailers> call, Throwable t) {
//
//            }
//        });

    }

    public void setGenres(Movie movie){
        String genrestring=" ";
        if (movie.getGenres().size()==0)
            genrestring="(Not Available)";
        else {
            for (int i = 0; i < movie.getGenres().size(); i++)
                genrestring += InitialiseMovieGenres.getGenreName(movie.getGenres().get(i).getId().intValue()) + ", ";
        }
        if (!genrestring.equals("(Not Available)"))
            genreTextView.setText(genrestring.substring(0,genrestring.length()-2));//to not display ", "
        else
            genreTextView.setText(genrestring);
    }

}
