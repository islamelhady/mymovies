package com.elhady.mymovies.interfac;

import com.elhady.mymovies.model.Genre;

import java.util.List;

public interface OnGetGenresCallback {
    void onSuccess(List<Genre> genres);

    void onError();
}
