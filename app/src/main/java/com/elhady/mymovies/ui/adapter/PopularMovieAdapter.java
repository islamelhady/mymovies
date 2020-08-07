package com.elhady.mymovies.ui.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.bumptech.glide.Glide;
import com.elhady.mymovies.R;
import com.elhady.mymovies.model.Movie;

import java.util.ArrayList;


public class PopularMovieAdapter extends RecyclerView.Adapter<PopularMovieAdapter.PopularMovieViewHolder> {
    private ArrayList<Movie> moviesList;
    private Context context;

    public PopularMovieAdapter(ArrayList<Movie> moviesList, Context context) {
        this.moviesList = moviesList;
        this.context = context;
    }

    @NonNull
    @Override
    public PopularMovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_popular_movie, parent, false);
        return new PopularMovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PopularMovieViewHolder holder, int position) {
        Movie movie = moviesList.get(position);

        Glide.with(context).load("https://image.tmdb.org/t/p/w500" + movie.getPosterPath()).
                into(holder.coverPosterImageView);
        holder.votesTextView.setText(movie.getVotesCount() + " votes");
        holder.voteAverageTextView.setText(movie.getVoteAverage() + "/10");
        holder.titleTextView.setText(movie.getTitle());

    }

    @Override
    public int getItemCount() {
        return moviesList.size();
    }

    class PopularMovieViewHolder extends RecyclerView.ViewHolder {
        public ImageView coverPosterImageView;
        public TextView titleTextView;
        public TextView votesTextView;
        public TextView voteAverageTextView;

        public PopularMovieViewHolder(@NonNull View itemView) {
            super(itemView);
            coverPosterImageView = itemView.findViewById(R.id.movie_cover_iv);
            titleTextView = itemView.findViewById(R.id.title_tv);
            votesTextView = itemView.findViewById(R.id.votes_count_tv);
            voteAverageTextView =  itemView.findViewById(R.id.votes_average_tv);
        }
    }
}
