package com.elhady.mymovies.repository;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import com.elhady.mymovies.MovieDatabase;
import com.elhady.mymovies.interfaces.MovieDao;
import com.elhady.mymovies.models.Movies;

import java.util.List;

public class MovieRepository {

    private MovieDao movieDao;
    private LiveData<List<Movies>> allMovies;

    public MovieRepository(Application application) {
        MovieDatabase database = MovieDatabase.getInstance(application);
        movieDao = database.movieDao();
        allMovies = movieDao.getAllMovies();
    }

    public void insert(Movies movies) {
        new insertMovieAsycTask(movieDao).execute(movies);
    }

    public LiveData<List<Movies>> getAllMovies() {
        return allMovies;
    }

    private static class insertMovieAsycTask extends AsyncTask<Movies, Void, Void> {
        private MovieDao movieDao;

        insertMovieAsycTask(MovieDao movieDao) {
            this.movieDao = movieDao;
        }

        @Override
        protected Void doInBackground(Movies... movies) {
            movieDao.insert(movies[0]);
            return null;
        }
    }
}
