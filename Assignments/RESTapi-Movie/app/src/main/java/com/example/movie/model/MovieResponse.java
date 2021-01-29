package com.example.movie.model;

import com.google.gson.annotations.Expose;

import java.util.List;

public class MovieResponse {
    @Expose
    private int page;
    @Expose
    private int total_pages;
    @Expose
    private int total_results;
    @Expose
    private List<MoviesModel> results;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getTotal_pages() {
        return total_pages;
    }

    public void setTotal_pages(int total_pages) {
        this.total_pages = total_pages;
    }

    public int getTotal_results() {
        return total_results;
    }

    public void setTotal_results(int total_results) {
        this.total_results = total_results;
    }

    public List<MoviesModel> getResults() {
        return results;
    }

    public void setResults(List<MoviesModel> results) {
        this.results = results;
    }
}
