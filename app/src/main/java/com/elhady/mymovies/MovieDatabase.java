package com.elhady.mymovies;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

import com.elhady.mymovies.interfaces.MovieDao;
import com.elhady.mymovies.models.Movies;

import java.util.ArrayList;
import java.util.List;

@Database(entities = {Movies.class}, version = 1)
public abstract class MovieDatabase extends RoomDatabase {

    public abstract MovieDao movieDao();

    private static MovieDatabase instance;


    public static synchronized MovieDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    MovieDatabase.class, "movie_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallback)
                    .build();
        }
        return instance;
    }

    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDbAsyncTask(instance).execute();
        }
    };

    private static class PopulateDbAsyncTask extends AsyncTask<Void, Void, Void> {
        private MovieDao movieDao;


        public PopulateDbAsyncTask(MovieDatabase database) {
            movieDao = database.movieDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            movieDao.insert(new Movies("sdf","asdf","asdf","asdf",R.drawable.badsamaritan));
            return null;
        }
    }
}
