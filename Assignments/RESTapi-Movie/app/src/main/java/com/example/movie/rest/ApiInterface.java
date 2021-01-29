package com.example.movie.rest;

import com.example.movie.model.MovieResponse;


import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiInterface {
//        @GET("movie/top_rated")
//        Observable<List<MovieResponse>> getTopRatedMovies(@Query("api_key") String apiKey);

    @GET("movie/top_rated")
    Observable<MovieResponse> getMovieDetails(@Query("sort_by") String sortBy, @Query("api_key") String apiKey);
}
