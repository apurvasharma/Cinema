package com.cinema.movie.info.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;


public class MovieImagesResponse {

    @SerializedName("page")
    @Expose
    public Integer page;
    @SerializedName("results")
    @Expose
    public List<Result> results = new ArrayList<Result>();
    @SerializedName("dates")
    @Expose
    public Dates dates;
    @SerializedName("total_pages")
    @Expose
    public Integer totalPages;
    @SerializedName("total_results")
    @Expose
    public Integer totalResults;

    public List<Result> getResults() {
        return results;
    }

    public class Dates {

        @SerializedName("maximum")
        @Expose
        public String maximum;
        @SerializedName("minimum")
        @Expose
        public String minimum;

    }


    public class Result {

        @SerializedName("poster_path")
        @Expose
        public String posterPath;
        @SerializedName("adult")
        @Expose
        public Boolean adult;
        @SerializedName("overview")
        @Expose
        public String overview;
        @SerializedName("release_date")
        @Expose
        public String releaseDate;
        @SerializedName("genre_ids")
        @Expose
        public List<Integer> genreIds = new ArrayList<Integer>();
        @SerializedName("id")
        @Expose
        public Integer id;
        @SerializedName("original_title")
        @Expose
        public String originalTitle;
        @SerializedName("original_language")
        @Expose
        public String originalLanguage;
        @SerializedName("title")
        @Expose
        public String title;
        @SerializedName("backdrop_path")
        @Expose
        public String backdropPath;
        @SerializedName("popularity")
        @Expose
        public Double popularity;
        @SerializedName("vote_count")
        @Expose
        public Integer voteCount;
        @SerializedName("video")
        @Expose
        public Boolean video;
        @SerializedName("vote_average")
        @Expose
        public Double voteAverage;

        public String getReleaseDate() {
            return releaseDate;
        }

        public String getPosterPath() {
            return posterPath;
        }

        public Integer getId() {
            return id;
        }

        public String getTitle() {
            return title;
        }

        public String getBackdropPath() {
            return backdropPath;
        }

        public Integer getVoteCount() {
            return voteCount;
        }
    }
}