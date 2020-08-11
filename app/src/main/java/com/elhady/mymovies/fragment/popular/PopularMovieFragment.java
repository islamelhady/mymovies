package com.elhady.mymovies.fragment.popular;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.elhady.mymovies.R;
import com.elhady.mymovies.adapter.MovieAdapter;
import com.elhady.mymovies.interfac.OnGetGenresCallback;
import com.elhady.mymovies.interfac.OnGetMoviesCallback;
import com.elhady.mymovies.interfac.OnMoviesClickCallback;
import com.elhady.mymovies.model.Genre;
import com.elhady.mymovies.model.Movie;
import com.elhady.mymovies.network.MovieClient;
import com.elhady.mymovies.ui.activity.MovieActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class PopularMovieFragment extends Fragment {

    private MovieClient movieClient;
    private MovieAdapter adapter;
    private RecyclerView moviesList;
    private List<Genre> movieGenres;
    private String popular = MovieClient.POPULAR;

    /**
     * isFetchingMovies:
     * flag that we will use to determine if we are currently fetching the next page.
     * Without this flag, if the we scrolled 50% above it will fetch the same page multiple times and causes duplication.
     * Try commenting out this flag and you will notice that when you scroll,
     * you will see the same movies of next page again and again.
     */
    private boolean isFetchingMovies;
    /*** currentPage:
     * initialized to page 1.
     * Every time we scrolled half of the list we increment it by one which is the next page.
     */
    private int currentPage = 1;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.popular_movie_fragment, container, false);
        movieClient = MovieClient.getInstance();

        moviesList = root.findViewById(R.id.popular_movie_rv);
        moviesList.setLayoutManager(new LinearLayoutManager(getContext()));

//        Toolbar toolbar = findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);

        setupOnScrollListener();

        getGenres();
        return root;
    }

    private void setupOnScrollListener() {
        final LinearLayoutManager manager = new LinearLayoutManager(getContext());
        moviesList.setLayoutManager(manager);
        moviesList.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                int totalItemCount = manager.getItemCount();
                int visibleItemCount = manager.getChildCount();
                int firstVisibleItem = manager.findFirstVisibleItemPosition();

                if (firstVisibleItem + visibleItemCount >= totalItemCount / 2) {
                    if (!isFetchingMovies) {
                        getMovies(currentPage + 1);
                    }
                }
            }
        });
    }

    private void getGenres() {
        movieClient.getGenres(new OnGetGenresCallback() {
            @Override
            public void onSuccess(List<Genre> genres) {
                movieGenres = genres;
                getMovies(currentPage);
            }

            @Override
            public void onError() {
                showError();
            }
        });
    }

    private void getMovies(int page) {
        isFetchingMovies = true;
        movieClient.getMovies(page, popular, new OnGetMoviesCallback() {
            @Override
            public void onSuccess(int page, List<Movie> movies) {
                if (adapter == null) {
                    adapter = new MovieAdapter(movies, movieGenres, callback);
                    moviesList.setAdapter(adapter);
                } else {
                    if (page == 1) {
                        adapter.clearMovies();
                    }
                    adapter.appendMovies(movies);
                }
                currentPage = page;
                isFetchingMovies = false;

            }

            @Override
            public void onError() {
                showError();
            }
        });
    }

    OnMoviesClickCallback callback = new OnMoviesClickCallback() {
        @Override
        public void onClick(Movie movie) {
            Intent intent = new Intent(getContext(), MovieActivity.class);
            intent.putExtra(MovieActivity.MOVIE_ID, movie.getId());
            startActivity(intent);
        }
    };

    private void showError() {
        Toast.makeText(getContext(), "Please check your internet connection.", Toast.LENGTH_SHORT).show();
    }

}