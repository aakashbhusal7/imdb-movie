package com.example.movieapi.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.Toast;


import com.example.movieapi.R;
import com.example.movieapi.adapter.MovieAdapter;
import com.example.movieapi.model.MovieResponse;

public class MainActivity extends AppCompatActivity implements  MainViewInterface{

    public static String TAG=MainActivity.class.getSimpleName();
    RecyclerView.Adapter adapter;
    MainPresenter presenter;
    RecyclerView recyclerView;
     Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView=findViewById(R.id.recycler_view_movies);
        toolbar=findViewById(R.id.toolbar);

        setUpMvp();
        setUpViews();
        getMoviesList();
    }

    private void setUpMvp(){
        presenter=new MainPresenter(this);
    }
    private void setUpViews(){
        setSupportActionBar(toolbar);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
    private void getMoviesList(){
        presenter.getMovies();
    }

    @Override
    public void showToast(String message) {
        Toast.makeText(MainActivity.this,message,Toast.LENGTH_LONG).show();
    }

    @Override
    public void displayMovies(MovieResponse movieResponse) {
        if(movieResponse!=null){
            adapter=new MovieAdapter(movieResponse.getResults(),this);
            recyclerView.setAdapter(adapter);
        }
        else{
            Log.d(TAG,"movie response is null");
        }
    }

    @Override
    public void displayError(String mesage) {
        showToast(mesage);
    }
}
