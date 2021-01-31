package com.example.music.rest;

import com.example.music.model.MusicResponse;


import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiInterface {
//        @GET("movie/top_rated")
//        Observable<List<MovieResponse>> getTopRatedMovies(@Query("api_key") String apiKey);

    @GET("/search?term=classick&amp;media=music&amp;entity=song&amp;limit=50")
    Observable<MusicResponse> getMusicDetails();
}
