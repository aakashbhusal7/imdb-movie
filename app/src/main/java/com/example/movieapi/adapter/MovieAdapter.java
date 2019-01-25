package com.example.movieapi.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.movieapi.R;
import com.example.movieapi.model.Result;

import org.w3c.dom.Text;

import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MoviesHolder> {

    List<Result> movieList;
    Context context;
    private TextView emptyView;

    public MovieAdapter(List<Result> movieList, Context context){
        this.movieList=movieList;
        this.context=context;
    }
    @NonNull
    @Override
    public MoviesHolder onCreateViewHolder(ViewGroup parent, int i) {
        LayoutInflater layoutInflater=LayoutInflater.from(context);
        View view=layoutInflater.inflate(R.layout.row_movies_list,parent,false);
        MoviesHolder moviesHolder= new MoviesHolder(view);
        return moviesHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MoviesHolder moviesHolder, int position) {
        moviesHolder.textViewTitle.setText(movieList.get(position).getTitle());
        moviesHolder.textViewReleaseDate.setText(movieList.get(position).getReleaseDate());
        moviesHolder.textViewDescription.setText(movieList.get(position).getOverview());
        Glide.with(context).load("https://image.tmdb.org/t/p/w500/"+movieList.get(position).getPosterPath()).into(moviesHolder.imageView);
    }

    @Override
    public int getItemCount() {
        int test;
        if (movieList != null && !movieList.isEmpty()) {
            test= movieList.size();
        }
        else{
            test=0;
        }
        return test;
    }



    public class MoviesHolder extends RecyclerView.ViewHolder{
        TextView textViewTitle, textViewReleaseDate, textViewDescription;
        ImageView imageView;

        public MoviesHolder(View view){
            super(view);
            textViewTitle=view.findViewById(R.id.title_movie);
            textViewReleaseDate=view.findViewById(R.id.release_date_movie);
            textViewDescription=view.findViewById(R.id.description_movie);
            imageView=view.findViewById(R.id.image_movie);
        }
    }
}
