package com.example.music.rest;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {
   // public static final String API_KEY = "6b04dec03bbe622c55407ff079258fd8"; //6b04dec03bbe622c55407ff079258fd8
    public static final String BASE_URL = "https://itunes.apple.com"; //http://api.themoviedb.org/3/movie/top_rated
//    public static final String IMG_URL = "https://image.tmdb.org/t/p/w500";
    private static Retrofit retrofit = null;

    public static Retrofit getClient() {
        Retrofit myRetrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .build();

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

        if(retrofit==null){
            retrofit= new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .client(client)
                    .build();
        }
        return retrofit;
    }

}
