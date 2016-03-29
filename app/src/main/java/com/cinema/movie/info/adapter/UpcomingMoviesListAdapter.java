package com.cinema.movie.info.adapter;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.cinema.movie.info.R;
import com.cinema.movie.info.model.MovieListResponse;
import com.cinema.movie.info.network.VolleyNetworkRequest;
import com.cinema.movie.info.utils.AppUtils;

import java.util.Collections;
import java.util.List;

/**
 * Created by Apurva on 11/29/2015.
 */
public class UpcomingMoviesListAdapter extends  RecyclerView.Adapter<UpcomingMoviesListAdapter.MoviesComingSoonViewHolder> {

    private List<MovieListResponse.Movies> upcomingMovieList = Collections.emptyList();
    private ImageLoader imageLoader;
    private static final String logTAG = "UpcomingMoviesAdapter";
    UpcomingItemClickListener mItemClickListener;

    public UpcomingMoviesListAdapter() {
        VolleyNetworkRequest volley = VolleyNetworkRequest.getInstance();
        imageLoader = volley.getImageLoader();
    }

    public interface UpcomingItemClickListener {
        void onItemClick(View view, int position);
    }

    public void setOnItemClickListener(final UpcomingItemClickListener mItemClickListener) {
        this.mItemClickListener = mItemClickListener;
    }

    @Override
    public MoviesComingSoonViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.upcoming_movie_list_item, parent, false);
        return new MoviesComingSoonViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final MoviesComingSoonViewHolder holder, int position) {
        MovieListResponse.Movies movie = upcomingMovieList.get(position);
        holder.bind(movie);

    }

    @Override
    public int getItemCount() {
        return upcomingMovieList.size();

    }

    public void updateList(List<MovieListResponse.Movies> newMovieList) {
        //update the adapter to display the list of new movies
        this.upcomingMovieList = newMovieList;
        notifyDataSetChanged();
    }

    public class MoviesComingSoonViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        // public int newMovieId;
        public RelativeLayout itemContainer;
        public TextView movieTitle;
        public ImageView movieImage;
        public TextView movieRating;
        public TextView movieReleaseDate;
        public TextView movieActors;

        public MoviesComingSoonViewHolder(View itemView) {
            super(itemView);
            movieRating = (TextView) itemView.findViewById(R.id.upcomingMovieRating);
            movieTitle = (TextView) itemView.findViewById(R.id.upcomingMovieTitle);
            movieImage = (ImageView) itemView.findViewById(R.id.upcomingMovieImage);
            movieReleaseDate = (TextView) itemView.findViewById(R.id.upcomingMovieReleaseDate);
            itemContainer = (RelativeLayout) itemView.findViewById(R.id.upcomingMovieListItemContainer);
            movieActors = (TextView) itemView.findViewById(R.id.upcomingMovieActors);
            itemContainer.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (mItemClickListener != null) {
                int position = getAdapterPosition();
                mItemClickListener.onItemClick(itemView, position);
            }
        }

        public void bind(MovieListResponse.Movies movie) {
            //set title
           movieTitle.setText(movie.getTitle());

            //set release Date
            String releaseDate = AppUtils.changeDateFormat(movie.getReleaseDates().getTheater());
           movieReleaseDate.setText(releaseDate);

            //convert user-rating out of 10
            double score = movie.getRatings().getAudienceScore();
            float userRating = (float) ((score / 100) * 10);
            String rating = "" + userRating;
           movieRating.setText(rating);

            //set actors
            String actors = "";
            if (movie.abridgedCast != null && movie.abridgedCast.size() > 0) {
                actors = actors.concat(movie.abridgedCast.get(0).getName());
                if (movie.abridgedCast.size() >= 2)
                    actors = actors.concat(", " + movie.abridgedCast.get(1).getName());

            }
           movieActors.setText(actors);

            //load thumbnail from JSON image URL
            String imageURL = movie.getPosters().getThumbnail();


            if (imageURL != null) {
                imageLoader.get(imageURL, new ImageLoader.ImageListener() {
                    @Override
                    public void onResponse(ImageLoader.ImageContainer response, boolean isImmediate) {
                        if (response.getBitmap() != null) {
                           movieImage.setImageBitmap(response.getBitmap());
                        } else {
                           movieImage.setImageBitmap(null);
                        }
                    }

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d(logTAG, "VolleyError");
                    }
                });
            } else {
                Log.d(logTAG, "image url is Null");
            }
        }
    }
}