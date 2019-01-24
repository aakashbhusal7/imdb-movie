package com.example.movieapi.ui.search;

import android.support.v7.widget.SearchView;

import com.example.movieapi.BasePresenter;
import com.example.movieapi.BaseView;
import com.example.movieapi.model.MovieResponse;

public interface SearchContract {
    interface Presenter extends BasePresenter{
        void getSearchResults(SearchView searchView);
    }
    interface View extends BaseView<SearchContract.Presenter>{
        void showToast(String string);
        void displayResult(MovieResponse movieResponse);
        void displayError(String error);
    }
}
