package com.firstcode.moviesdemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;

import com.firstcode.cakedemo.R;
import com.firstcode.moviesdemo.adapter.MoviesAdapter;
import com.firstcode.moviesdemo.model.MoviesModel;
import com.firstcode.moviesdemo.rest.ApiClient;
import com.firstcode.moviesdemo.rest.ApiInterface;
import com.firstcode.moviesdemo.utilities.RxUtils;

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
    // https://gist.githubusercontent.com/hart88/198f29ec5114a3ec3460/raw/8dd19a88f9b8d24c23d9960f3300d0c917a4f07c/cake.json
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recycler_main);
        initialRecyclerView();
        intialaizeProgress();
        displayCakesList();
    }
    public void displayCakesList() {
        //TODO 7. Create an API interface
         ApiInterface apiService =
                 //TODO 8. It creates an instance of the class and returns an object of the interface class
                ApiClient.getClient().create(ApiInterface.class);

        //TODO 9. Making the calls/requests
        apiService.getTopRatedMovies(ApiClient.API_KEY)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<MoviesModel>>() {
                    @Override
                    public void onSubscribe( Disposable d) {

                    }

                    @Override
                    public void onNext(List<MoviesModel> moviesModel) {
                        if(moviesModel.size() > 0 && mRecyclerView != null){
                            mRecyclerView.setAdapter(mAdapter);
                            hidePDialog();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        hidePDialog();
                    }

                    @Override
                    public void onComplete() {

                    }
                });

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

    @Override
    public void onDestroy() {
        super.onDestroy();
        hidePDialog();
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