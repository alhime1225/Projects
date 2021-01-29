package com.firstcode.moviesdemo.rest;


import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


public class ApiClient {
    //TODO 2. Create a variable that will hold the URL that you will interact with.
    public static final String API_KEY = "6b04dec03bbe622c55407ff079258fd8";
    public static final String BASE_URL = "http://api.themoviedb.org/3/";
    //TODO 3. Create a retrofit instance
    private static Retrofit retrofit = null;


    //TODO 4. Create a method that is public static Retrofit <name of method>()
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
