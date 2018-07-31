package com.example.tc.movie_db.network;


import com.example.tc.movie_db.movies.Movie;
import com.example.tc.movie_db.movies.MovieGenres;
import com.example.tc.movie_db.movies.NowPlaying;
import com.example.tc.movie_db.movies.Popular;
import com.example.tc.movie_db.movies.TopRated;
import com.example.tc.movie_db.movies.Upcoming;
import com.example.tc.movie_db.tvshows.AiringToday;
import com.example.tc.movie_db.tvshows.Latest;
import com.example.tc.movie_db.tvshows.OnTheAir;
import com.example.tc.movie_db.tvshows.TvGenres;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface MovidbService {

    String api_key="22e390ea1991d3c47cbcfe5341787e99";

    //Genres-movies
    @GET("genre/movie/list?api_key="+api_key+"&language=en-US")
    Call<MovieGenres> getMovieGenresList();
    //Genres-TV
    @GET("genre/tv/list?api_key="+api_key+"&language=en-US")
    Call<TvGenres> getTVGenresList();



    //Movies
    @GET("movie/popular?api_key="+api_key+"&language=en-US&page=1&region=IN")
    Call<Popular> getPopular();

    @GET("movie/now_playing?api_key="+api_key+"&language=en-US&page=1&region=IN")
    Call<NowPlaying> getNowPlaying();

    @GET("movie/upcoming?api_key="+api_key+"&language=en-US&page=1&region=IN")
    Call<Upcoming> getUpcoming();

    @GET("movie/top_rated?api_key="+api_key+"&language=en-US&page=1&region=IN")
    Call<TopRated> getTopRated();

    @GET("movie/{id}?api_key="+api_key+"&language=en-US")
    Call<Movie> getMovieDetails(@Path("id") Long movieId);




    //TV Shows
    @GET("tv/popular?api_key="+api_key+"&language=en-US&page=1")
    Call<com.example.tc.movie_db.tvshows.Popular> getPopulartvs();

    @GET("tv/airing_today?api_key="+api_key+"&language=en-US&page=1")
    Call<AiringToday> getAiringTodaytvs();

    @GET("tv/on_the_air?api_key="+api_key+"&language=en-US&page=1")
    Call<OnTheAir> getOnTheAirtvs();

    @GET("tv/top_rated?api_key="+api_key+"&language=en-US&page=1")
    Call<com.example.tc.movie_db.tvshows.TopRated> getTopRatedtvs();

    @GET("tv/latest?api_key="+api_key+"&language=en-US")
    Call<Latest> getLatesttvs();
}
