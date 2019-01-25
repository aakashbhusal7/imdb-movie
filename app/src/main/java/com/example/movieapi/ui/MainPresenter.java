package com.example.movieapi.ui;

import android.support.annotation.NonNull;
import android.util.Log;

import com.example.movieapi.BuildConfig;
import com.example.movieapi.Constants;
import com.example.movieapi.model.MovieResponse;
import com.example.movieapi.network.NetworkClient;
import com.example.movieapi.network.RestApi;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import retrofit2.http.QueryMap;

public class MainPresenter implements MainPresenterInterface {

    public static String TAG=MainPresenter.class.getSimpleName();
    private MainViewInterface mainViewInterface;

     MainPresenter(MainViewInterface mainViewInterface){
        this.mainViewInterface=mainViewInterface;
    }

    @Override
    public void getMovies() {
        getObservable().subscribeWith(getObserver());
    }


    public Observable<MovieResponse>getObservable(){
        return NetworkClient.getRetrofit()
                .create(RestApi.class)
                .getMovies(BuildConfig.apikey)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public DisposableObserver<MovieResponse>getObserver(){
        return new DisposableObserver<MovieResponse>() {
            @Override
            public void onNext(@NonNull MovieResponse movieResponse) {
                Log.d(TAG,"OnNext"+movieResponse.getTotalResults());
                mainViewInterface.displayMovies(movieResponse);
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG,"Error"+e);
                e.printStackTrace();
                mainViewInterface.displayError("Error displaying movies");

            }

            @Override
            public void onComplete() {
                Log.d(TAG,"Completed");

            }
        };
    }

}
