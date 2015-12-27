package com.cinema.movie.info.model;

import java.util.ArrayList;
import java.util.List;

public class NewMovies {
    private Integer total;
    private List<Movies> movies = new ArrayList<Movies>();
    private Links_ links;
    private String linkTemplate;

    public List<Movies> getMovies() {
        return movies;
    }

    public class AbridgedCast {
        private String name;
        private String id;
        private List<String> characters = new ArrayList<String>();
    }

    public class AlternateIds {
        private String imdb;
    }

    public class Links {
        private String self;
        private String alternate;
        private String cast;
        private String reviews;
        private String similar;
    }

    public class Links_ {
        private String self;
        private String next;
        private String alternate;
    }



    public class Posters {
        private String thumbnail;
        private String profile;
        private String detailed;
        private String original;
    }

    public class Ratings {
        private String criticsRating;
        private Integer criticsScore;
        private String audienceRating;
        private Integer audienceScore;
    }

    public class ReleaseDates {
        private String theater;
    }
}