package com.example.music.mvp;

import android.util.Log;

import com.example.music.model.MusicResponse;
import com.example.music.rest.ApiClient;
import com.example.music.rest.ApiInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GetNoticeIntractorImpl implements MusicMVP.GetNoticeIntractor {

    @Override
    public void getNoticeArrayList(final OnFinishedListener onFinishedListener) {


        /** Create handle for the RetrofitInstance interface*/
        ApiInterface service = ApiClient.getClient().create(ApiInterface.class);

        /** Call the method with parameter in the interface to get the notice data*/
        Call<MusicResponse> call = service.getMusicDetails();

        /**Log the URL called*/
        Log.wtf("URL Called", call.request().url() + "");

        call.enqueue(new Callback<MusicResponse>() {
            @Override
            public void onResponse(Call<MusicResponse> call, Response<MusicResponse> response) {
                onFinishedListener.onFinished(response.body().getResults());

            }

            @Override
            public void onFailure(Call<MusicResponse> call, Throwable t) {
                onFinishedListener.onFailure(t);
            }
        });

    }

}
