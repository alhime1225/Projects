package com.firstcode.moviesdemo.model;


import com.google.gson.annotations.Expose;

public class MoviesModel {

    @Expose
    private int id;
    @Expose
    private String original_title;
    @Expose
    private String overview;

    @Expose
    private String image;


    public String getOriginal_title() {
        return original_title;
    }

    public void setOriginal_title(String original_title) {
        this.original_title = original_title;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
