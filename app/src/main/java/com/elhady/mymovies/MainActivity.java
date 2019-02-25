package com.elhady.mymovies;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.elhady.mymovies.adapters.MovieAdapter;
import com.elhady.mymovies.models.Movies;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    Toolbar toolbar;
    RecyclerView recyclerView;
    MovieAdapter movieAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = findViewById(R.id.toolbar);

        List<Movies> moviesList = new ArrayList<>();
        int movPic [] = {R.drawable.a_quiet_place, R.drawable.avengers_infinity_war,
                         R.drawable.badsamaritan,R.drawable.blockers,R.drawable.ifeel_pretty,
                         R.drawable.isleof_dogs,R.drawable.love_simon,R.drawable.rampage};

        String movName[] = {"A Quiet Place","Avengers: Infinity War","Bad Samaritan","Blockers","I Feel Pretty","Isle of Dogs","Love, Simon","Rampage"};

        String movRate[] = {"6.1","8.2","7.9","8.1","6.7","4.5","5.6","8.6"};

        String movStory[] = {getResources().getString(R.string.a_quiet_place),
                             getResources().getString(R.string.avengers),
                             getResources().getString(R.string.badsamaritan),
                             getResources().getString(R.string.blockers),
                             getResources().getString(R.string.ifeel_pretty),
                             getResources().getString(R.string.isleof_dogs),
                             getResources().getString(R.string.love_simon),
                             getResources().getString(R.string.rampage)};

        String movTime[] = {getResources().getString(R.string.min_a_quiet_place),
                            getResources().getString(R.string.min_avengers),
                            getResources().getString(R.string.min_badsamaritan),
                            getResources().getString(R.string.min_blockers),
                            getResources().getString(R.string.min_ifeel_pretty),
                            getResources().getString(R.string.min_isleof_dogs),
                            getResources().getString(R.string.min_love_simon),
                            getResources().getString(R.string.min_rampage)};

        for (int i = 0; i < movPic.length; i++){
            Movies movies = new Movies(movName[i],movRate[i],movStory[i],movTime[i],movPic[i]);
            moviesList.add(movies);
        }
        recyclerView = (RecyclerView) findViewById(R.id.recyler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        movieAdapter = new MovieAdapter(moviesList);
        recyclerView.setAdapter(movieAdapter);
    }
}
