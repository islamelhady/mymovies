package com.elhady.mymovies.fragment.toprated;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.elhady.mymovies.R;

public class TopRatedMovieFragment extends Fragment {

    private TopRatedMovieViewModel mViewModel;

    public static TopRatedMovieFragment newInstance() {
        return new TopRatedMovieFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.top_rated_movie_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(TopRatedMovieViewModel.class);
        // TODO: Use the ViewModel
    }

}