package com.firstcode.cakedemo.rest;


import com.firstcode.cakedemo.model.Cake_model;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;


public interface ApiInterface {
//    @GET("movie/top_rated")
//    Observable<MoviesResponse> getTopRatedMovies(@Query("api_key") String apiKey);
//
//    @GET("movie/{id}")
//    Observable<MoviesResponse> getMovieDetails(@Path("id") int id, @Query("api_key") String apiKey);
    @GET("198f29ec5114a3ec3460/raw/8dd19a88f9b8d24c23d9960f3300d0c917a4f07c/cake.json")
    public Observable<List<Cake_model>> getCakeslist();
}
