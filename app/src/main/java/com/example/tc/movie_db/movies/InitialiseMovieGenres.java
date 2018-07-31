package com.example.tc.movie_db.movies;

import java.util.HashMap;
import java.util.List;

public class InitialiseMovieGenres {

    private static HashMap<Integer,String> genresMap=new HashMap<>();


    public static void setGenresMap(List<Genre> genres){
        for (Genre genre: genres) {
            int id=genre.getId().intValue();
            genresMap.put(id,genre.getName());
        }
    }

    public static String getGenreName(int id){
        return genresMap.get(id);
    }
}
