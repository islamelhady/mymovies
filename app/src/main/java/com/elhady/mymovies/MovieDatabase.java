package com.elhady.mymovies;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

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
                    .addCallback(roomCallback)
                    .build();
        }
        return instance;
    }

    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback() {
        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db) {
            super.onOpen(db);
            new PopulateDbAsyncTask(instance).execute();
        }
    };

    private static class PopulateDbAsyncTask extends AsyncTask<Void, Void, Void> {
        private MovieDao movieDao;
        //private String nameMovie[],rateMovie[],timeMovie[],storyMovie[];
        //private int picMovie[];


        public PopulateDbAsyncTask(MovieDatabase database) {
            movieDao = database.movieDao();
        }

        /*public PopulateDbAsyncTask(Context context){
            nameMovie = context.getResources().getStringArray(R.array.movie_name);
            rateMovie = context.getResources().getStringArray(R.array.movie_rate);
            timeMovie = context.getResources().getStringArray(R.array.movie_time);
            storyMovie = context.getResources().getStringArray(R.array.movie_story);
            picMovie = context.getResources().getIntArray(R.array.movie_pics);
        }*/

        @Override
        protected Void doInBackground(Void... voids) {
            /**
             * Start the app with a clean database every time.
             */
            movieDao.deleteAll();

            /*for (int i =0; i<picMovie.length;i++){
                movieDao.insert(new Movies(nameMovie[i],rateMovie[i],storyMovie[i],timeMovie[i],picMovie[i]));
            }*/

            movieDao.insert(new Movies("A Auiet Place","6.1","In a post-apocalyptic world, a family is forced to live in silence while hiding from monsters with ultra-sensitive hearing",
                    "120 min",R.drawable.a_quiet_place));
            movieDao.insert(new Movies("Avengers","8.2","A pair of burglars stumble upon a woman being held captive in a home they intended to rob.",
                    "90 min",R.drawable.avengers_infinity_war));
            movieDao.insert(new Movies("Bad Samaritan","4.7","Three parents try to stop their daughters from losing their virginity on prom night.",
                    "111 min",R.drawable.badsamaritan));
            movieDao.insert(new Movies("Blockers","7.5","Set in Japan, Isle of Dogs follows a boys odyssey in search of his lost dog.",
                    "109 min",R.drawable.blockers));
            movieDao.insert(new Movies("I Feel Pretty","6.7","A woman struggling with insecurity wakes from a fall believing she is the most beautiful and capable woman on the planet. Her new confidence empowers her to live fearlessly, but what happens when she realizes her appearance never changed?",
                    "120 min",R.drawable.ifeel_pretty));
            movieDao.insert(new Movies("Is Leof Dogs","5.6","The Avengers and their allies must be willing to sacrifice all in an attempt to defeat the powerful Thanos before his blitz of devastation and ruin puts an end to the universe.",
                    "190 min",R.drawable.isleof_dogs));
            movieDao.insert(new Movies("Love Simon","8.6","Simon Spier keeps a huge secret from his family, his friends and all of his classmates: he is gay. When that secret is threatened, Simon must face everyone and come to terms with his identity.",
                    "122 min",R.drawable.love_simon));
            movieDao.insert(new Movies("Rampage","7.9","When three different animals become infected with a dangerous pathogen, a primatologist and a geneticist team up to stop them from destroying Chicago.",
                    "140 min",R.drawable.rampage));
            return null;
        }
    }
}
