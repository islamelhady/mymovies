package com.elhady.mymovies;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.elhady.mymovies.interfaces.MovieDao;
import com.elhady.mymovies.models.Movies;

@Database(entities = {Movies.class}, version = 1)
public abstract class MovieDatabase extends RoomDatabase {

    public abstract MovieDao movieDao();

    private static MovieDatabase instance;

    public static synchronized MovieDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    MovieDatabase.class, "movie_database")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }
}
