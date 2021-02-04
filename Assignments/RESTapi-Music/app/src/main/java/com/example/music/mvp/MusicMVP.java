package com.example.music.mvp;

import com.example.music.model.Music;

import java.util.ArrayList;
import java.util.List;

public interface MusicMVP {

    interface View {



        void showProgress();

        void hideProgress();

        void setDataToRecyclerView(ArrayList<Music> musicArrayList);

        void onResponseFailure(Throwable throwable);
    }

    interface Presenter {



        void onDestroy();

        void onRefreshButtonClick();

        void requestDataFromServer();

    }

    interface Model {

        Music getMusic();

        List<Music> getAllMusics();

        void reset();

    }

    /**
     * Intractors are classes built for fetching data from your database, web services, or any other data source.
     **/
    interface GetNoticeIntractor {

        interface OnFinishedListener {
            void onFinished(ArrayList<Music> musicArrayList);

            void onFailure(Throwable t);
        }

        void getNoticeArrayList(OnFinishedListener onFinishedListener);
    }
}
