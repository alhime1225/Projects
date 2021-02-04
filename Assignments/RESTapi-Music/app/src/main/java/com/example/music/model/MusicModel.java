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
                //Moves the work onto the background thread (Schedules.io is one of the background thread)
                .subscribeOn(Schedulers.io())
                //Display in the main thread after the background work is done
                .observeOn(AndroidSchedulers.mainThread())
                //Subscriber to the Observable
                .subscribe(new Observer<MusicResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {
//                        System.out.println(d);
                    }

                    @Override
                    public void onNext(MusicResponse movieResponse) {
                          allMusics = movieResponse.getResults();
                    }

                    @Override
                    public void onError(Throwable e) {
                        allMusics = new ArrayList<>();
                        Log.d("DEBUG_TEST ", e.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        System.out.println("Done");
                    }
                });

        return allMusics;
    }
}
