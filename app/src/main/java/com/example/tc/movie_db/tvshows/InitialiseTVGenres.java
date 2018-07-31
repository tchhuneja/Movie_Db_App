package com.example.tc.movie_db.tvshows;

import java.util.HashMap;
import java.util.List;

public class InitialiseTVGenres {

    private static HashMap<Integer,String> genresMap=new HashMap<>();


    public static void setGenresMap(List<com.example.tc.movie_db.tvshows.Genre> genres){
        for (com.example.tc.movie_db.tvshows.Genre genre: genres) {
            int id=genre.getId().intValue();
            genresMap.put(id,genre.getName());
        }
    }

    public static String getGenreName(int id){
        return genresMap.get(id);
    }
}
