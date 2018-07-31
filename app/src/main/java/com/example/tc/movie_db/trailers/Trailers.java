
package com.example.tc.movie_db.trailers;

import java.util.List;
import com.google.gson.annotations.Expose;


public class Trailers {

    @Expose
    private Long id;
    @Expose
    private List<Result> results;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Result> getResults() {
        return results;
    }

    public void setResults(List<Result> results) {
        this.results = results;
    }

}
