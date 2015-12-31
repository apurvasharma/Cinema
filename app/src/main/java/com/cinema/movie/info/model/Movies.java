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
        public MovieResponse.ReleaseDates releaseDates;
        @SerializedName("ratings")
        @Expose
        public MovieResponse.Ratings ratings;
        @SerializedName("synopsis")
        @Expose
        public String synopsis;
        @SerializedName("posters")
        @Expose
        public MovieResponse.Posters posters;
        @SerializedName("abridged_cast")
        @Expose
        public List<MovieResponse.AbridgedCast> abridgedCast = new ArrayList<MovieResponse.AbridgedCast>();
        @SerializedName("alternate_ids")
        @Expose
        public MovieResponse.AlternateIds alternateIds;
        @SerializedName("links")
        @Expose
        public MovieResponse.Links links;


        public String getId() {
                return id;
        }

        public String getTitle() {
                return title;
        }

        public MovieResponse.Ratings getRatings() {
                return ratings;
        }

        public MovieResponse.Posters getPosters() {
                return posters;
        }


        public MovieResponse.ReleaseDates getReleaseDates() {
                return releaseDates;
        }
}
