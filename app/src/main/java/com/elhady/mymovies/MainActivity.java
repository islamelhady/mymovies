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

        //int movPic[] = getResources().getIntArray(R.array.movie_pics);
        String movName[] = getResources().getStringArray(R.array.movie_name);

        String movRate[] = getResources().getStringArray(R.array.movie_rate);

        String movStory[] = getResources().getStringArray(R.array.movie_story);

        String movTime[] = getResources().getStringArray(R.array.movie_time);

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
