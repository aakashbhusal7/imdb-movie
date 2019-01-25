package com.example.movieapi.ui.search;

import com.example.movieapi.model.MovieResponse;


/***/

public interface SearchViewInterface {

    void showToast(String str);
    void displayResult(MovieResponse movieResponse);
    void displayError(String s);
    void showProgressBar();
    void hideProgressBar();
}
