package com.example.tc.movie_db;

import retrofit2.Call;
import retrofit2.http.GET;

public interface MovidbService {

    String api_key="22e390ea1991d3c47cbcfe5341787e99";

    //Genres
    @GET("genre/movie/list?api_key="+api_key+"&language=en-US")
    Call<MovieGenres> getMovieGenresList();


    //Movies
    @GET("movie/popular?api_key="+api_key+"&language=en-US&page=1&region=IN")
    Call<Popular> getPopular();

    @GET("movie/now_playing?api_key="+api_key+"&language=en-US&page=1&region=IN")
    Call<NowPlaying> getNowPlaying();

    @GET("movie/upcoming?api_key="+api_key+"&language=en-US&page=1&region=IN")
    Call<Upcoming> getUpcoming();

    @GET("movie/top_rated?api_key="+api_key+"&language=en-US&page=1&region=IN")
    Call<TopRated> getTopRated();

    //TV Shows

}
