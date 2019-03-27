package com.elhady.mymovies.models;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = "movie_table")
public class Movies {

    @PrimaryKey(autoGenerate = true)
    private int id;
    @NonNull
    private String movieName;
    private String movieRate;
    private String movieStory;
    private String movieTime;
    private int moviePic;


    public Movies() {
    }

    public Movies(@NonNull String movieName, @NonNull String movieRate, @NonNull String movieStory, @NonNull String movieTime, int moviePic) {
        this.movieName = movieName;
        this.movieRate = movieRate;
        this.movieStory = movieStory;
        this.movieTime = movieTime;
        this.moviePic = moviePic;
    }

    @NonNull
    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(@NonNull String movieName) {
        this.movieName = movieName;
    }

    @NonNull
    public String getMovieRate() {
        return movieRate;
    }

    public void setMovieRate(@NonNull String movieRate) {
        this.movieRate = movieRate;
    }

    @NonNull
    public String getMovieStory() {
        return movieStory;
    }

    public void setMovieStory(@NonNull String movieStory) {
        this.movieStory = movieStory;
    }

    @NonNull
    public String getMovieTime() {
        return movieTime;
    }

    public void setMovieTime(@NonNull String movieTime) {
        this.movieTime = movieTime;
    }

    public int getMoviePic() {
        return moviePic;
    }

    public void setMoviePic(int moviePic) {
        this.moviePic = moviePic;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
