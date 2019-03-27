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

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieHolder> {
    private List<Movies> moviesList = new ArrayList<>();


    @NonNull
    @Override
    public MovieHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View row = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.movie_row, viewGroup, false);
        return new MovieHolder(row);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieHolder movieHolder, int position) {
        Movies movie = moviesList.get(position);
        movieHolder.textViewName.setText(movie.getMovieName());
        movieHolder.textViewRate.setText(movie.getMovieRate());
        movieHolder.textViewTime.setText(movie.getMovieTime());
        movieHolder.textViewStory.setText(movie.getMovieStory());
        movieHolder.imageViewPic.setImageResource(movie.getMoviePic());
    }

    @Override
    public int getItemCount() {
        return moviesList.size();
    }

    public void setMoviesList(List<Movies> moviesList) {
        this.moviesList = moviesList;
        notifyDataSetChanged();
    }

    public class MovieHolder extends RecyclerView.ViewHolder {
        private TextView textViewName;
        private TextView textViewRate;
        private TextView textViewTime;
        private TextView textViewStory;
        private ImageView imageViewPic;

        public MovieHolder(@NonNull View itemView) {
            super(itemView);
            textViewName = itemView.findViewById(R.id.text_view_name);
            textViewRate = itemView.findViewById(R.id.text_view_rate);
            textViewTime = itemView.findViewById(R.id.text_view_time);
            textViewStory = itemView.findViewById(R.id.text_view_story);
            imageViewPic = itemView.findViewById(R.id.image_view_pic);
        }
    }

}
