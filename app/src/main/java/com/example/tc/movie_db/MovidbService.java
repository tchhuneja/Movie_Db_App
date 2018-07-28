package com.example.tc.movie_db;

import retrofit2.Call;
import retrofit2.http.GET;

public interface MovidbService {

    final String api_key="22e390ea1991d3c47cbcfe5341787e99";

    //@GET("movie/popular?api_key="+api_key+"&language=en-US&page=1")
    //Call<PopularMovieResponse> getPopular();

    @GET("genre/movie/list?api_key="+api_key+"&language=en-US")
    Call<MovieGenres> getMovieGenresList();


    @GET("movie/now_playing?api_key="+api_key+"&language=en-US&page=1")
    Call<NowPlaying> getNowPlaying();

    //@GET("movie/upcoming?api_key="+api_key+"&language=en-US&page=1")
    //Call<TopRatedResponse> getUpcoming();

    //@GET("movie/top_rated?api_key="+api_key+"&language=en-US&page=1")
    //Call<PopularMovieResponse> getTopRated();
}
