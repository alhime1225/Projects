package com.example.music.mvp;

import com.example.music.model.Music;

import java.util.ArrayList;
import java.util.List;

public class MainPresenterImpl implements MusicMVP.Presenter, MusicMVP.GetNoticeIntractor.OnFinishedListener {

    private MusicMVP.View mainView;
    private MusicMVP.GetNoticeIntractor getNoticeIntractor;

    public MainPresenterImpl(MusicMVP.View mainView, MusicMVP.GetNoticeIntractor getNoticeIntractor) {
        this.mainView = mainView;
        this.getNoticeIntractor = getNoticeIntractor;
    }

    @Override
    public void onDestroy() {

        mainView = null;

    }

    @Override
    public void onRefreshButtonClick() {

        if(mainView != null){
            mainView.showProgress();
        }
        getNoticeIntractor.getNoticeArrayList(this);

    }

    @Override
    public void requestDataFromServer() {
        getNoticeIntractor.getNoticeArrayList(this);
    }


    @Override
    public void onFinished(ArrayList<Music> noticeArrayList) {
        if(mainView != null){
            mainView.setDataToRecyclerView(noticeArrayList);
            mainView.hideProgress();
        }
    }

    @Override
    public void onFailure(Throwable t) {
        if(mainView != null){
            mainView.onResponseFailure(t);
            mainView.hideProgress();
        }
    }
}
