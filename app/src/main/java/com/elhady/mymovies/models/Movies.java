package com.elhady.mymovies.models;

public class Movies {
    private String movieName, movieRate, movieStory, movieTime;
    private int moviePic;

    public Movies(String movieName, String movieRate, String movieStory, String movieTime, int moviePic) {
        this.movieName = movieName;
        this.movieRate = movieRate;
        this.movieStory = movieStory;
        this.movieTime = movieTime;
        this.moviePic = moviePic;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public String getMovieRate() {
        return movieRate;
    }

    public void setMovieRate(String movieRate) {
        this.movieRate = movieRate;
    }

    public String getMovieStory() {
        return movieStory;
    }

    public void setMovieStory(String movieStory) {
        this.movieStory = movieStory;
    }

    public String getMovieTime() {
        return movieTime;
    }

    public void setMovieTime(String movieTime) {
        this.movieTime = movieTime;
    }

    public int getMoviePic() {
        return moviePic;
    }

    public void setMoviePic(int moviePic) {
        this.moviePic = moviePic;
    }
}
