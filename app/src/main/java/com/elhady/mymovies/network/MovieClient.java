package com.elhady.mymovies.network;

import com.elhady.mymovies.interfac.OnGetGenresCallback;
import com.elhady.mymovies.interfac.OnGetMovieCallback;
import com.elhady.mymovies.interfac.OnGetMoviesCallback;
import com.elhady.mymovies.interfac.OnGetReviewsCallback;
import com.elhady.mymovies.interfac.OnGetTrailersCallback;
import com.elhady.mymovies.model.GenresResponse;
import com.elhady.mymovies.model.Movie;
import com.elhady.mymovies.model.MoviesResponse;
import com.elhady.mymovies.model.ReviewResponse;
import com.elhady.mymovies.model.TrailerResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MovieClient {
    private static final String BASE_URL = "https://api.themoviedb.org/3/";
    private static final String LANGUAGE = "en-US";
    public static final String POPULAR = "popular";
    public static final String TOP_RATED = "top_rated";
    public static final String UPCOMING = "upcoming";
    public static final String API_KEY = "282157b63b2a2ef81abaca304a648cba";

    private static MovieClient repository;

    private MovieApiService api;

    private MovieClient(MovieApiService api) {
        this.api = api;
    }

    public static MovieClient getInstance() {
        if (repository == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            repository = new MovieClient(retrofit.create(MovieApiService.class));
        }

        return repository;
    }

    //refactor getMovies(…) method to accept a page and pass the current page through the callback
    public void getMovies(int page, String sortBy, final OnGetMoviesCallback callback) {
        Callback<MoviesResponse> call = new Callback<MoviesResponse>() {
            @Override
            public void onResponse(Call<MoviesResponse> call, Response<MoviesResponse> response) {
                if (response.isSuccessful()) {
                    MoviesResponse moviesResponse = response.body();
                    if (moviesResponse != null && moviesResponse.getMovies() != null) {
                        callback.onSuccess(moviesResponse.getPage(), moviesResponse.getMovies());
                    } else {
                        callback.onError();
                    }
                } else {
                    callback.onError();
                }
            }

            @Override
            public void onFailure(Call<MoviesResponse> call, Throwable t) {
                callback.onError();
            }
        };

        switch (sortBy) {
            case TOP_RATED:
                api.getTopRatedMovies(API_KEY, LANGUAGE, page)
                        .enqueue(call);
                break;
            case UPCOMING:
                api.getUpcomingMovies(API_KEY, LANGUAGE, page)
                        .enqueue(call);
                break;
            case POPULAR:
            default:
                api.getPopularMovies(API_KEY, LANGUAGE, page)
                        .enqueue(call);
                break;
        }
    }


    public void getGenres(final OnGetGenresCallback callback) {
        api.getGenres(API_KEY, LANGUAGE)
                .enqueue(new Callback<GenresResponse>() {
                    @Override
                    public void onResponse(Call<GenresResponse> call, Response<GenresResponse> response) {
                        if (response.isSuccessful()) {
                            GenresResponse genresResponse = response.body();
                            if (genresResponse != null && genresResponse.getGenres() != null) {
                                callback.onSuccess(genresResponse.getGenres());
                            } else {
                                callback.onError();
                            }
                        } else {
                            callback.onError();
                        }
                    }

                    @Override
                    public void onFailure(Call<GenresResponse> call, Throwable t) {
                        callback.onError();
                    }
                });
    }

    public void getMovie(int movieId, final OnGetMovieCallback callback) {
        api.getMovie(movieId, API_KEY, LANGUAGE)
                .enqueue(new Callback<Movie>() {
                    @Override
                    public void onResponse(Call<Movie> call, Response<Movie> response) {
                        if (response.isSuccessful()) {
                            Movie movie = response.body();
                            if (movie != null) {
                                callback.onSuccess(movie);
                            } else {
                                callback.onError();
                            }
                        } else {
                            callback.onError();
                        }
                    }

                    @Override
                    public void onFailure(Call<Movie> call, Throwable t) {
                        callback.onError();
                    }
                });
    }

        public void getTrailers(int movieId, final OnGetTrailersCallback callback) {
        api.getTrailers(movieId, API_KEY, LANGUAGE)
                .enqueue(new Callback<TrailerResponse>() {
                    @Override
                    public void onResponse(Call<TrailerResponse> call, Response<TrailerResponse> response) {
                        if (response.isSuccessful()) {
                            TrailerResponse trailerResponse = response.body();
                            if (trailerResponse != null && trailerResponse.getTrailers() != null) {
                                callback.onSuccess(trailerResponse.getTrailers());
                            } else {
                                callback.onError();
                            }
                        } else {
                            callback.onError();
                        }
                    }

                    @Override
                    public void onFailure(Call<TrailerResponse> call, Throwable t) {
                        callback.onError();
                    }
                });
    }

    public void getReviews(int movieId, final OnGetReviewsCallback callback) {
        api.getReviews(movieId, API_KEY, LANGUAGE)
                .enqueue(new Callback<ReviewResponse>() {
                    @Override
                    public void onResponse(Call<ReviewResponse> call, Response<ReviewResponse> response) {
                        if (response.isSuccessful()) {
                            ReviewResponse reviewResponse = response.body();
                            if (reviewResponse != null && reviewResponse.getReviews() != null) {
                                callback.onSuccess(reviewResponse.getReviews());
                            } else {
                                callback.onError();
                            }
                        } else {
                            callback.onError();
                        }
                    }

                    @Override
                    public void onFailure(Call<ReviewResponse> call, Throwable t) {
                        callback.onError();
                    }
                });
    }
}
