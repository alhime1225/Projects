package com.example.movie;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;

import com.example.movie.adapter.MoviesAdapter;
import com.example.movie.model.MovieResponse;
import com.example.movie.model.MoviesModel;
import com.example.movie.rest.ApiClient;
import com.example.movie.rest.ApiInterface;
import com.example.movie.utilities.RxUtils;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;
public class MainActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private MoviesAdapter mAdapter;
    private ProgressDialog pDialog;

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
    }

    public void displayMovieList() {
        //TODO 7. Create an API interface
        ApiInterface apiService =
                //TODO 8. It creates an instance of the class and returns an object of the interface class
                ApiClient.getClient().create(ApiInterface.class);
       // apiService.getTopRatedMovies(ApiClient.API_KEY);
        //TODO 9. Making the calls/requests

        apiService.getMovieDetails("popularity", ApiClient.API_KEY)
                //Moves the work onto the background thread (Schedules.io is one of the background thread)
                .subscribeOn(Schedulers.io())
                //Display in the main thread after the background work is done
                .observeOn(AndroidSchedulers.mainThread())
                //Subscriber to the Observable
                .subscribe(new Observer<MovieResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {
//                        System.out.println(d);
                    }

                    @Override
                    public void onNext(MovieResponse movieResponse) {
                        if ( mRecyclerView != null) {
                            List<MoviesModel> allMovies = movieResponse.getResults();
                            mAdapter = new MoviesAdapter(allMovies, R.layout.movie_row, getApplicationContext());
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