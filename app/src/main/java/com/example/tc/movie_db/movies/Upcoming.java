
package com.example.tc.movie_db.movies;

import java.util.List;

import com.example.tc.movie_db.movies.Dates;
import com.example.tc.movie_db.movies.Result;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Upcoming {

    @Expose
    private Dates dates;
    @Expose
    private Long page;
    @Expose
    private List<Result> results;
    @SerializedName("total_pages")
    private Long totalPages;
    @SerializedName("total_results")
    private Long totalResults;

    public Dates getDates() {
        return dates;
    }

    public void setDates(Dates dates) {
        this.dates = dates;
    }

    public Long getPage() {
        return page;
    }

    public void setPage(Long page) {
        this.page = page;
    }

    public List<Result> getResults() {
        return results;
    }

    public void setResults(List<Result> results) {
        this.results = results;
    }

    public Long getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(Long totalPages) {
        this.totalPages = totalPages;
    }

    public Long getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(Long totalResults) {
        this.totalResults = totalResults;
    }

}
