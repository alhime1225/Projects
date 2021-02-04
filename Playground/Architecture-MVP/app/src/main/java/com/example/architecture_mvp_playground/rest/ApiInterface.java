package com.firstcode.cakedemo.rest;


import com.firstcode.cakedemo.model.Cake_model;
import com.firstcode.cakedemo.model.MoviesResponse;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

//Can be named as the Service (This is where you'll be making the calls/requests and response)
public interface ApiInterface {
//    @GET("movie/top_rated?sort_by=popularity?&{api_key}")
//    Observable<MoviesResponse> getTopRatedMovies(@Query("api_key") String apiKey);
//
//    @GET("movie/{id}")
//    Observable<MoviesResponse> getMovieDetails(@Path("id") int id, @Query("api_key") String apiKey);

    //TODO 5. Annotate the it with what you want @GET or @PUT or @DELETE
    @GET("198f29ec5114a3ec3460/raw/8dd19a88f9b8d24c23d9960f3300d0c917a4f07c/cake.json")
    //Observable = Comes from rxJava library. Allows us to object an object. Its an object that will have subscribers to it
    //Any changes to it, will be observed by whatever watchers are there.
    public Observable<List<Cake_model>> getCakeslist();
}
