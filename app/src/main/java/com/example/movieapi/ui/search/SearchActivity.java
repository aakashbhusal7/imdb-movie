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

import com.example.movieapi.R;
import com.example.movieapi.adapter.MovieAdapter;
import com.example.movieapi.model.MovieResponse;

public class SearchActivity extends AppCompatActivity implements SearchContract.View {
    private SearchContract.Presenter presenter;
    private RecyclerView.Adapter adapter;
    private RecyclerView queryRecyclerView;
     Toolbar toolbar;
    private SearchView searchView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        toolbar=findViewById(R.id.toolbar);
        queryRecyclerView=findViewById(R.id.recycler_query_result);
        //queryRecyclerView.setAdapter(null);
        setUpViews();
        presenter= new SearchPresenter(this);
    }
    private void setUpViews(){
        setSupportActionBar(toolbar);
        queryRecyclerView.setLayoutManager(new LinearLayoutManager(this));

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
    public void showToast(String string) {

    }

    @Override
    public void displayResult(MovieResponse movieResponse) {
        adapter= new MovieAdapter(movieResponse.getResults(),SearchActivity.this);
        queryRecyclerView.setAdapter(adapter);
    }

    @Override
    public void displayError(String error) {
        showToast(error);
    }

    @Override
    public void setPresenter(SearchContract.Presenter presenter) {
      //  this.presenter=presenter;
    }
}
