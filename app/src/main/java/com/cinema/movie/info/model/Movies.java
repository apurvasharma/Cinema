package com.cinema.movie.info.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Apurva on 12/27/2015.
 */
public class Movies {
        private String id;
        private String title;
        private Integer year;
        private String mpaaRating;
        private Integer runtime;
        private String criticsConsensus;
        private NewMovies.ReleaseDates releaseDates;
        private NewMovies.Ratings ratings;
        private String synopsis;
        private NewMovies.Posters posters;
        private List<NewMovies.AbridgedCast> abridgedCast = new ArrayList<NewMovies.AbridgedCast>();
        private NewMovies.AlternateIds alternateIds;
        private NewMovies.Links links;

}
