package com.elhady.mymovies.fragment.popular;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.elhady.mymovies.R;

public class PopularMovieFragment extends Fragment {

    private PopularMovieViewModel mViewModel;

    public static PopularMovieFragment newInstance() {
        return new PopularMovieFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.popular_movie_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(PopularMovieViewModel.class);
        // TODO: Use the ViewModel
    }

}