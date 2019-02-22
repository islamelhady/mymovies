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

        String movStory[] = {"In a post-apocalyptic world, a family is forced to live in silence while hiding from monsters with ultra-sensitive hearing.",
                             "A pair of burglars stumble upon a woman being held captive in a home they intended to rob.",
                             "Three parents try to stop their daughters from losing their virginity on prom night.",
                             "Set in Japan, Isle of Dogs follows a boy's odyssey in search of his lost dog.",
                             "A woman struggling with insecurity wakes from a fall believing she is the most beautiful and capable woman on the planet. Her new confidence empowers her to live fearlessly, but what happens when she realizes her appearance never changed?",
                             "The Avengers and their allies must be willing to sacrifice all in an attempt to defeat the powerful Thanos before his blitz of devastation and ruin puts an end to the universe.",
                             "Simon Spier keeps a huge secret from his family, his friends and all of his classmates: he's gay. When that secret is threatened, Simon must face everyone and come to terms with his identity.",
                             "When three different animals become infected with a dangerous pathogen, a primatologist and a geneticist team up to stop them from destroying Chicago."};

        String movTime[] = {"149 min","90 min","120 min","149 min","104 min","111 min","109 min","95 min"};

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
