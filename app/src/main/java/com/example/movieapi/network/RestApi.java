package com.example.movieapi.network;

import com.example.movieapi.model.MovieResponse;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RestApi {

    @GET("discover/movie")
    Observable<MovieResponse> getMovies(@Query("api_key")String api_key);
}
