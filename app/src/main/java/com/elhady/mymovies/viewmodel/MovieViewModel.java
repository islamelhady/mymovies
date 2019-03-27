package com.elhady.mymovies.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.elhady.mymovies.models.Movies;
import com.elhady.mymovies.repository.MovieRepository;

import java.util.List;

public class MovieViewModel extends AndroidViewModel {

    private MovieRepository repository;
    private LiveData<List<Movies>> allMovies;

    public MovieViewModel(@NonNull Application application) {
        super(application);
        repository = new MovieRepository(application);
        allMovies = repository.getAllMovies();
    }

    public void insert (Movies movies){
        repository.insert(movies);
    }

    public LiveData<List<Movies>> getAllMovies() {
        return allMovies;
    }
}
