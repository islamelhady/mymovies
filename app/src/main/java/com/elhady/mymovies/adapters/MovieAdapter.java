package com.elhady.mymovies.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.elhady.mymovies.R;
import com.elhady.mymovies.models.Movies;

import java.util.ArrayList;
import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter <MovieAdapter.MovieHolder> {
    private List<Movies> moviesList = new ArrayList<>();;

    public MovieAdapter(List<Movies> moviesList) {
        this.moviesList = moviesList;
    }

    @NonNull
    @Override
    public MovieHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View row = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.movie_row,viewGroup, false);
        MovieHolder holder = new MovieHolder(row);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MovieHolder movieHolder, int position) {
        Movies movie = moviesList.get(position);
        movieHolder.nameMov.setText(movie.getMovieName());
        movieHolder.rateMov.setText(movie.getMovieRate());
        movieHolder.timeMov.setText(movie.getMovieTime());
        movieHolder.storyMov.setText(movie.getMovieStory());
        movieHolder.picMov.setImageResource(movie.getMoviePic());
    }

    @Override
    public int getItemCount() {
        return moviesList.size();
    }

    public class MovieHolder extends RecyclerView.ViewHolder{
        public TextView nameMov,rateMov,timeMov,storyMov;
        public ImageView picMov;

        public MovieHolder(@NonNull View itemView) {
            super(itemView);
            nameMov  = itemView.findViewById(R.id.movie_name);
            rateMov  = itemView.findViewById(R.id.movie_rate);
            timeMov  = itemView.findViewById(R.id.movie_time);
            storyMov = itemView.findViewById(R.id.movie_story);
            picMov   = itemView.findViewById(R.id.movie_pic);
        }
    }

}
