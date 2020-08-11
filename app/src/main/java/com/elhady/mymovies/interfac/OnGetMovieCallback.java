package com.elhady.mymovies.interfac;


import com.elhady.mymovies.model.Movie;

public interface OnGetMovieCallback {
    void onSuccess(Movie movie);

    void onError();
}
