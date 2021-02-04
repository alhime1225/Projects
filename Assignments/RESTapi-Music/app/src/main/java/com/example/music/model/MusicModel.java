package com.example.music.model;

import android.util.Log;

import com.example.music.mvp.MusicMVP;
import com.example.music.rest.ApiClient;
import com.example.music.rest.ApiInterface;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MusicModel implements MusicMVP.Model {
    private List<Music> allMusics;

   public  MusicModel(){

       fetchAllMusics();
   }

    @Override
    public Music getMusic() {
        return null;
    }

    @Override
    public List<Music> getAllMusics() {
        if(allMusics == null){
            fetchAllMusics();
        }

        return allMusics;
    }

    @Override
    public void reset() {
        allMusics=null;
    }


    private List<Music> fetchAllMusics() {

        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);


        apiService.getMusicDetails()
                //Subscriber to the Observable
                .enqueue( new Callback<MusicResponse>() {
                              @Override
                              public void onResponse(Call<MusicResponse> call, Response<MusicResponse> response) {
                                        allMusics=response.body().getResults();
                              }

                              @Override
                              public void onFailure(Call<MusicResponse> call, Throwable t) {
                                         allMusics= new ArrayList<>();
                              }
                          }
                    );

        return allMusics;
    }
}
