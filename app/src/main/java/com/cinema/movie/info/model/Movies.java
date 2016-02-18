package com.cinema.movie.info.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Apurva on 12/27/2015.
 */
public class Movies {
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
    public MovieListResponse.ReleaseDates releaseDates;
    @SerializedName("ratings")
    @Expose
    public MovieListResponse.Ratings ratings;
    @SerializedName("synopsis")
    @Expose
    public String synopsis;
    @SerializedName("posters")
    @Expose
    public MovieListResponse.Posters posters;
    @SerializedName("abridged_cast")
    @Expose
    public List<MovieListResponse.AbridgedCast> abridgedCast = new ArrayList<MovieListResponse.AbridgedCast>();
    @SerializedName("alternate_ids")
    @Expose
    public MovieListResponse.AlternateIds alternateIds;
    @SerializedName("links")
    @Expose
    public MovieListResponse.Links links;


    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public MovieListResponse.Ratings getRatings() {
        return ratings;
    }

    public MovieListResponse.Posters getPosters() {
        return posters;
    }

    public List<MovieListResponse.AbridgedCast> getAbridgedCast() {
        return abridgedCast;
    }

    public MovieListResponse.ReleaseDates getReleaseDates() {
        return releaseDates;
    }
}