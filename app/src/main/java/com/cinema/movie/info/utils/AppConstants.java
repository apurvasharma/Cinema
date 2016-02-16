package com.cinema.movie.info.utils;

/**
 * Created by Apurva on 11/29/2015.
 */
public class AppConstants {
    public static final String NEW_MOVIES_URL = "http://api.rottentomatoes.com/api/public/v1.0/lists/movies/in_theaters.json?apikey=";
    public static final String UPCOMING_MOVIES_URL = "http://api.rottentomatoes.com/api/public/v1.0/lists/movies/upcoming.json?apikey=";
    public static final String NEW_MOVIES_IMAGES_URL = "https://api.themoviedb.org/3/movie/now_playing?api_key=";
    public static final String UPCOMING_MOVIES_IMAGES_URL = "https://api.themoviedb.org/3/movie/upcoming?api_key=";
    public static final String SEARCH_MOVIE_POSTER_URL = "https://api.themoviedb.org/3/search/movie?api_key=";
    public static final String MOVIE_ID = "movie id";
    public static final String MOVIE_TITLE = "movie title";
    public static final String IMAGE_URL = "https://image.tmdb.org/t/p/original";
    public static final String MOVIE_BACKDROP = "movie backdrop";

    public static String getSearchMoviePosterUrl(String apiKey, String movieName) {
        return SEARCH_MOVIE_POSTER_URL + apiKey + "&query=" + movieName;
    }


    public static String getMovieDetailsUrl(String movieId) {
        return "http://api.rottentomatoes.com/api/public/v1.0/movies/" + movieId + ".json?apikey=";
    }

    public static String getThumbnailUrl(String posterPath) {
        return IMAGE_URL + posterPath;
    }


}
