package com.example.movieapi.ui;

import com.example.movieapi.model.MovieResponse;

public interface MainViewInterface {
    void showToast(String message);
    void displayMovies(MovieResponse movieResponse);
    void displayError(String mesage);
}
