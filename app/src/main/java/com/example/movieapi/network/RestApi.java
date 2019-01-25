package com.example.movieapi.network;

import com.example.movieapi.model.MovieResponse;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

public interface RestApi {

    @GET("discover/movie")
    Observable<MovieResponse> getMovies(@Query("api_key")String api_key);
//    ,@Query("with_cast")String cast

    @GET("search/movie")
    Observable<MovieResponse> getMoviesOnSearch(@Query("api_key")String api_key, @Query("query")String query);



}
