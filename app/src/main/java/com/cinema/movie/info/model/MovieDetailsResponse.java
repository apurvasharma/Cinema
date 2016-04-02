package com.cinema.movie.info.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Apurva on 2/11/2016.
 */


public class MovieDetailsResponse {

    @SerializedName("id")
    @Expose
    public int id;
    @SerializedName("title")
    @Expose
    public String title;
    @SerializedName("year")
    @Expose
    public int year;
    @SerializedName("genres")
    @Expose
    public List<String> genres = new ArrayList<String>();
    @SerializedName("mpaa_rating")
    @Expose
    public String mpaaRating;
    @SerializedName("runtime")
    @Expose
    public int runtime;
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
    @SerializedName("abridged_directors")
    @Expose
    public List<AbridgedDirector> abridgedDirectors = new ArrayList<AbridgedDirector>();
    @SerializedName("studio")
    @Expose
    public String studio;
    @SerializedName("alternate_ids")
    @Expose
    public AlternateIds alternateIds;
    @SerializedName("links")
    @Expose
    public Links links;

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


    public class AbridgedDirector {

        @SerializedName("name")
        @Expose
        public String name;

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

    }

    public class Ratings {

        @SerializedName("critics_rating")
        @Expose
        public String criticsRating;
        @SerializedName("critics_score")
        @Expose
        public int criticsScore;
        @SerializedName("audience_rating")
        @Expose
        public String audienceRating;
        @SerializedName("audience_score")
        @Expose
        public int audienceScore;

    }

    public class ReleaseDates {

        @SerializedName("theater")
        @Expose
        public String theater;
        @SerializedName("dvd")
        @Expose
        public String dvd;

    }

    public int getRuntime() {
        return runtime;
    }

    public List<String> getGenres() {
        return genres;
    }

    public ReleaseDates getReleaseDates() {
        return releaseDates;
    }

    public Ratings getRatings() {
        return ratings;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public List<AbridgedCast> getAbridgedCast() {
        return abridgedCast;
    }

    public List<AbridgedDirector> getAbridgedDirectors() {
        return abridgedDirectors;
    }

    public int getId() {
        return id;
    }

    public int getYear() {
        return year;
    }

    public String getMpaaRating() {
        return mpaaRating;
    }

    public String getCriticsConsensus() {
        return criticsConsensus;
    }

    public Posters getPosters() {
        return posters;
    }

    public String getStudio() {
        return studio;
    }

    public AlternateIds getAlternateIds() {
        return alternateIds;
    }

    public Links getLinks() {
        return links;
    }

    public String getTitle() {
        return title;
    }

}