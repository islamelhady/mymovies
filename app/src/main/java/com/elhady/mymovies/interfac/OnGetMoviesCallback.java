package com.elhady.mymovies.interfac;


import com.elhady.mymovies.model.Movie;

import java.util.List;

public interface OnGetMoviesCallback {
    //Refactor MoviesRepository.getMovies(…) to accept a page number (Pagination)
    void onSuccess(int page, List<Movie> movies);

    void onError();
}

