
package com.example.tc.movie_db.tvshows;

import java.util.List;
import com.google.gson.annotations.Expose;


public class TvGenres {

    @Expose
    private List<Genre> genres;

    public List<Genre> getGenres() {
        return genres;
    }

    public void setGenres(List<Genre> genres) {
        this.genres = genres;
    }

}
