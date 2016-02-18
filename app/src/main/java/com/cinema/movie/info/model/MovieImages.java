package com.cinema.movie.info.model;

import java.util.HashMap;

/**
 * Created by Apurva on 2/12/2016.
 */
public class MovieImages {
    String movieID, movieTitle, posterUrl, backdropUrl;

    public MovieImages(String movieID, String movieTitle, String posterUrl, String backdropUrl) {
        this.movieID = movieID;
        this.movieTitle = movieTitle;
        this.posterUrl = posterUrl;
        this.backdropUrl = backdropUrl;
    }

    public String getPosterUrl() {
        return posterUrl;
    }

    public String getBackdropUrl() {
        return backdropUrl;
    }

    public String getMovieID() {
        return movieID;
    }
}
