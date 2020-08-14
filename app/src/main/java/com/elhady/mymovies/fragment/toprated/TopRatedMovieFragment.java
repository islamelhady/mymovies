package com.elhady.mymovies.fragment.toprated;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
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

import java.util.List;

public class TopRatedMovieFragment extends Fragment {

    private MovieClient movieClient;
    private MovieAdapter adapter;
    private RecyclerView recyclerView;
    private List<Genre> movieGenres;
    private String topRated = MovieClient.TOP_RATED;

    private boolean isFetchingMovies;

    private int currentPage = 1;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.top_rated_movie_fragment, container, false);
        movieClient = MovieClient.getInstance();

        recyclerView = root.findViewById(R.id.top_rated_movie_rv);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

//        Toolbar toolbar = findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);

        setupOnScrollListener();

        getGenres();
        return root;
    }

    private void setupOnScrollListener() {
        final LinearLayoutManager manager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(manager);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
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
        movieClient.getMovies(page, topRated, new OnGetMoviesCallback() {
            @Override
            public void onSuccess(int page, List<Movie> movies) {
                if (adapter == null) {
                    adapter = new MovieAdapter(movies, movieGenres, callback);
                    recyclerView.setAdapter(adapter);
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