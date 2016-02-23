package com.cinema.movie.info.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class MovieListResponse {

    @SerializedName("total")
    @Expose
    public Integer total;
    @SerializedName("movies")
    @Expose
    public List<Movies> movies = new ArrayList<Movies>();
    @SerializedName("links")
    @Expose
    public Links_ links;
    @SerializedName("link_template")
    @Expose
    public String linkTemplate;

    public List<Movies> getMovies() {
        return movies;
    }

    public class AbridgedCast {

        @SerializedName("name")
        @Expose
        public String name;
        @SerializedName("id")
        @Expose
        public String id;
        @SerializedName("characters")
        @Expose
        public List<String> characters = new ArrayList<String>();

        public String getName() {
            return name;
        }
    }

    public class AlternateIds {

        @SerializedName("imdb")
        @Expose
        public String imdb;

    }


    public class Links {

        @SerializedName("self")
        @Expose
        public String self;
        @SerializedName("alternate")
        @Expose
        public String alternate;
        @SerializedName("cast")
        @Expose
        public String cast;
        @SerializedName("reviews")
        @Expose
        public String reviews;
        @SerializedName("similar")
        @Expose
        public String similar;

    }

    public class Links_ {

        @SerializedName("self")
        @Expose
        public String self;
        @SerializedName("next")
        @Expose
        public String next;
        @SerializedName("alternate")
        @Expose
        public String alternate;

    }


    public class Posters {

        @SerializedName("thumbnail")
        @Expose
        public String thumbnail;
        @SerializedName("profile")
        @Expose
        public String profile;
        @SerializedName("detailed")
        @Expose
        public String detailed;
        @SerializedName("original")
        @Expose
        public String original;

        public String getThumbnail() {
            return thumbnail;
        }
    }

    public class Ratings {

        @SerializedName("critics_rating")
        @Expose
        public String criticsRating;
        @SerializedName("critics_score")
        @Expose
        public Integer criticsScore;
        @SerializedName("audience_rating")
        @Expose
        public String audienceRating;
        @SerializedName("audience_score")
        @Expose
        public Integer audienceScore;

        public Integer getAudienceScore() {
            return audienceScore;
        }
    }

    public class ReleaseDates {

        @SerializedName("theater")
        @Expose
        public String theater;

        public String getTheater() {
            return theater;
        }
    }

    /**
     * Created by Apurva on 12/27/2015.
     */
    public static class Movies {
        @SerializedName("id")
        @Expose
        public String id;
        @SerializedName("title")
        @Expose
        public String title;
        @SerializedName("year")
        @Expose
        public Integer year;
        @SerializedName("mpaa_rating")
        @Expose
        public String mpaaRating;
        @SerializedName("runtime")
        @Expose
        public String runtime;
        @SerializedName("critics_consensus")
        @Expose
        public String criticsConsensus;
        @SerializedName("release_dates")
        @Expose
        public ReleaseDates releaseDates;
        @SerializedName("ratings")
        @Expose
        public Ratings ratings;
        @SerializedName("synopsis")
        @Expose
        public String synopsis;
        @SerializedName("posters")
        @Expose
        public Posters posters;
        @SerializedName("abridged_cast")
        @Expose
        public List<AbridgedCast> abridgedCast = new ArrayList<AbridgedCast>();
        @SerializedName("alternate_ids")
        @Expose
        public AlternateIds alternateIds;
        @SerializedName("links")
        @Expose
        public Links links;


        public String getId() {
            return id;
        }

        public String getTitle() {
            return title;
        }

        public Ratings getRatings() {
            return ratings;
        }

        public Posters getPosters() {
            return posters;
        }

        public List<AbridgedCast> getAbridgedCast() {
            return abridgedCast;
        }

        public ReleaseDates getReleaseDates() {
            return releaseDates;
        }
    }
}