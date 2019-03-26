package com.elhady.mymovies.interfaces;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.elhady.mymovies.models.Movies;

import java.util.List;


@Dao
public interface MovieDao {

    @Insert
    void insert (Movies movies);

    @Query("SELECT * from movie_table ORDER BY movieRate ASC")
    LiveData<List<Movies>> getAllMovies();
}
