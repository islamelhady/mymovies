package com.elhady.mymovies.interfac;



import com.elhady.mymovies.model.Review;

import java.util.List;

public interface OnGetReviewsCallback {
    void onSuccess(List<Review> reviews);

    void onError();
}
