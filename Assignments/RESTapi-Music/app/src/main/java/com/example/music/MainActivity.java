package com.example.music;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.music.adapter.MusicAdapter;
import com.example.music.model.MusicModel;
import com.example.music.model.MusicResponse;
import com.example.music.rest.ApiClient;
import com.example.music.rest.ApiInterface;
import com.example.music.utilities.RxUtils;
import com.google.android.material.textfield.TextInputEditText;


import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;
import io.reactivex.Observer;

public class MainActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private MusicAdapter mAdapter;
    private ProgressDialog pDialog;
    private TextInputEditText searchView;

    /**
     * Subscription that represents a group of Subscriptions that are unsubscribed together.
     */
    private CompositeSubscription _subscriptions = new CompositeSubscription();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recycler_main);

        initialRecyclerView();
        intialaizeProgress();
        displayMovieList();

        searchView  =findViewById(R.id.txtSearch);
        searchView.setOnKeyListener((v, keyCode, event) -> {
String searchKey=searchView.getText().toString();
            mAdapter.getFilter().filter(searchKey);
            Toast.makeText(MainActivity.this,"Hello Bro", Toast.LENGTH_LONG).show();
            return false;
        });

    }

    public void displayMovieList() {
        //TODO 7. Create an API interface
        ApiInterface apiService =
                //TODO 8. It creates an instance of the class and returns an object of the interface class
                ApiClient.getClient().create(ApiInterface.class);
       // apiService.getTopRatedMovies(ApiClient.API_KEY);
        //TODO 9. Making the calls/requests

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
                        if ( mRecyclerView != null) {
                            List<MusicModel> allMusic = movieResponse.getResults();

                            mAdapter = new MusicAdapter(allMusic, R.layout.music_row_circular, MainActivity.this);
                            mRecyclerView.setAdapter(mAdapter);
                            hidePDialog();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        hidePDialog();
                        Log.d("DEBUG_TEST ", e.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        System.out.println("Done");
                    }
                });

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        hidePDialog();
    }

    @Override
    public void onResume() {
        super.onResume();
        _subscriptions = RxUtils.getNewCompositeSubIfUnsubscribed(_subscriptions);
    }

    @Override
    public void onPause() {
        super.onPause();
        RxUtils.unsubscribeIfNotNull(_subscriptions);
    }

    public void initialRecyclerView(){
        mRecyclerView = (RecyclerView)findViewById(R.id.list);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
    }
    public void intialaizeProgress(){
        pDialog = new ProgressDialog(this);
        // Showing progress dialog before making http request
        pDialog.setMessage("Loading...");
        pDialog.show();
    }

    private void hidePDialog() {
        if (pDialog != null) {
            pDialog.dismiss();
            pDialog = null;
        }
    }
}