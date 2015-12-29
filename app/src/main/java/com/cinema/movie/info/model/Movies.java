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
        public NewMovies.ReleaseDates releaseDates;
        @SerializedName("ratings")
        @Expose
        public NewMovies.Ratings ratings;
        @SerializedName("synopsis")
        @Expose
        public String synopsis;
        @SerializedName("posters")
        @Expose
        public NewMovies.Posters posters;
        @SerializedName("abridged_cast")
        @Expose
        public List<NewMovies.AbridgedCast> abridgedCast = new ArrayList<NewMovies.AbridgedCast>();
        @SerializedName("alternate_ids")
        @Expose
        public NewMovies.AlternateIds alternateIds;
        @SerializedName("links")
        @Expose
        public NewMovies.Links links;


        public String getId() {
                return id;
        }

        public String getTitle() {
                return title;
        }

        public NewMovies.Ratings getRatings() {
                return ratings;
        }

        public NewMovies.Posters getPosters() {
                return posters;
        }


}
