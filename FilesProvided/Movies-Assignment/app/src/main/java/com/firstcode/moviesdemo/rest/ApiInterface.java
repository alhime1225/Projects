package com.firstcode.moviesdemo.rest;


import com.firstcode.moviesdemo.model.MoviesModel;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

//Can be named as the Service (This is where you'll be making the calls/requests and response)
public interface ApiInterface {
    @GET("movie/top_rated?sort_by=popularity?")
    Observable<List<MoviesModel>> getTopRatedMovies(@Query("api_key") String apiKey);

    @GET("movie/{id}")
    Observable<MoviesModel> getMovieDetails(@Path("id") int id, @Query("api_key") String apiKey);

}
