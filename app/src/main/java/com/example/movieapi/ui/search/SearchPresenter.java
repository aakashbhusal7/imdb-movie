package com.example.movieapi.ui.search;

import android.support.annotation.NonNull;
import android.support.v7.widget.SearchView;
import android.util.Log;

import com.example.movieapi.BuildConfig;
import com.example.movieapi.model.MovieResponse;
import com.example.movieapi.network.NetworkClient;
import com.example.movieapi.network.RestApi;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.PublishSubject;

public class SearchPresenter implements SearchContract.Presenter {

    private static String TAG= SearchPresenter.class.getSimpleName();
    private SearchContract.View view;

    public SearchPresenter(SearchContract.View view){
        this.view=view;
        this.view.setPresenter(this);
    }
    @Override
    public void getSearchResults(SearchView searchView) {
        getObservableQuery(searchView).
                debounce(TIME_IN_TEXT, TimeUnit.SECONDS)
                .filter(new Predicate<String>() {
                    @Override
                    public boolean test(String text) throws Exception {
                        if(text.isEmpty()){
                            return false;
                        }
                        else{
                            return true;
                        }
                    }
                })
                .distinctUntilChanged()
                .switchMap(new Function<String, ObservableSource<MovieResponse>>() {
                    @Override
                    public ObservableSource<MovieResponse> apply(@NonNull String s) throws Exception {
                        return NetworkClient.getRetrofit()
                                .create(RestApi.class)
                                .getMoviesOnSearch(BuildConfig.apikey,s);
                    }
                }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(getObserver());
    }

    private Observable<String>getObservableQuery(SearchView searchView){
        final PublishSubject<String>publishSubject= PublishSubject.create();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                publishSubject.onNext(query);
                Log.d(TAG, "typing  "+query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                publishSubject.onNext(newText);
                return true;
            }
        });
        Log.d(TAG,"query"+publishSubject);
        return publishSubject;
    }
    public DisposableObserver<MovieResponse>getObserver(){
        return new DisposableObserver<MovieResponse>() {
            @Override
            public void onNext(@NonNull MovieResponse movieResponse) {
                Log.d(TAG,"OnNext"+movieResponse.getTotalResults());
                view.displayResult(movieResponse);
                Log.d(TAG,"query"+movieResponse);
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG,"Error"+e);
                e.printStackTrace();
            }

            @Override
            public void onComplete() {
                Log.d(TAG,"On COmpleted");
            }
        };
    }

    @Override
    public void start() {

    }

    @Override
    public void stop() {

    }

    private static long TIME_IN_TEXT=3;
}
