package com.example.movieapi.ui.search;

import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.movieapi.R;
import com.example.movieapi.adapter.MovieAdapter;
import com.example.movieapi.model.MovieResponse;

public class SearchActivity extends AppCompatActivity implements SearchContract.View{

     private  Toolbar toolbar;
     RecyclerView queryRecyclerView;
     SearchContract.Presenter presenter;
     RecyclerView.Adapter adapter;
     private SearchView searchView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        toolbar=findViewById(R.id.toolbar);
        queryRecyclerView=findViewById(R.id.recycler_query_result);
        setUpViews();
        setUpMvp();
    }

    private void setUpViews(){
        setSupportActionBar(toolbar);
        final LinearLayoutManager linearLayoutManager= new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        queryRecyclerView.setLayoutManager(linearLayoutManager);
    }

    private void setUpMvp(){
     new SearchPresenter(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_search,menu);
        SearchManager searchManager= (SearchManager)getSystemService(Context.SEARCH_SERVICE);
        searchView=(SearchView)menu.findItem(R.id.action_search)
                .getActionView();
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setMaxWidth(Integer.MAX_VALUE);
        searchView.setQueryHint(getString(R.string.query_movie_name));
        presenter.getSearchResults(searchView);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id=item.getItemId();
        if(id==R.id.action_search){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void showToast(String message) {
        Toast.makeText(SearchActivity.this,message,Toast.LENGTH_LONG).show();
    }

    @Override
    public void displayResult(MovieResponse movieResponse) {
        if(movieResponse!=null) {
            adapter = new MovieAdapter(movieResponse.getResults(), this);
            queryRecyclerView.setAdapter(adapter);
        }
        else{
            showToast("nothing queried");
        }
    }

    @Override
    public void displayError(String error) {
        showToast(error);
    }

    @Override
    public void setPresenter(SearchContract.Presenter presenter) {
        this.presenter=presenter;
    }

}
