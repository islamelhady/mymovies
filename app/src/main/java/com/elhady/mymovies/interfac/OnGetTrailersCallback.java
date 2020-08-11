package com.elhady.mymovies.interfac;


import com.elhady.mymovies.model.Trailer;

import java.util.List;

public interface OnGetTrailersCallback {
    void onSuccess(List<Trailer> trailers);

    void onError();
}
