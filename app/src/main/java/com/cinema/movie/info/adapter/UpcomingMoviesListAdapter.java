package com.cinema.movie.info.adapter;

import android.graphics.Color;
import android.graphics.drawable.LayerDrawable;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.cinema.movie.info.R;
import com.cinema.movie.info.model.Movies;
import com.cinema.movie.info.network.VolleySingleton;
import com.cinema.movie.info.utils.AppUtils;

import java.util.Collections;
import java.util.List;

/**
 * Created by Apurva on 11/29/2015.
 */
public class UpcomingMoviesListAdapter extends  RecyclerView.Adapter<UpcomingMoviesListAdapter.MoviesComingSoonViewHolder> {

    private List<Movies> upcomingMovieList = Collections.emptyList();
    private ImageLoader imageLoader;
    private static final String logTAG = "UpcomingMoviesAdapter";
    UpcomingItemClickListener mItemClickListener;

    public UpcomingMoviesListAdapter() {
        VolleySingleton volleySingleton = VolleySingleton.getInstance();
        imageLoader = volleySingleton.getImageLoader();
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

        Movies movies = upcomingMovieList.get(position);

        //set title
        holder.movieTitle.setText(movies.getTitle());

        //set release Date
        String releaseDate = AppUtils.changeDateFormat(movies.getReleaseDates().getTheater());
        holder.movieReleaseDate.setText(releaseDate);

        //convert user-rating out of 5
        float userRating = (movies.getRatings().getAudienceScore() * 5) / 100;
        LayerDrawable layerDrawable = (LayerDrawable) holder.movieRating.getProgressDrawable();
        DrawableCompat.setTint(DrawableCompat.wrap(layerDrawable.getDrawable(2)), Color.rgb(229, 193, 0)); // Full star
        holder.movieRating.setRating(userRating);

        //set actors
        String actors = "";
        if (movies.abridgedCast != null && movies.abridgedCast.size() > 0) {
            actors = actors.concat(movies.abridgedCast.get(0).getName());
            if(movies.abridgedCast.size() >= 2)
                actors = actors.concat(", "+movies.abridgedCast.get(1).getName());

        }
        holder.movieActors.setText(actors);

        //load thumbnail from JSON image URL
        String imageURL = movies.getPosters().getThumbnail();



        if (imageURL != null) {
            imageLoader.get(imageURL, new ImageLoader.ImageListener() {
                @Override
                public void onResponse(ImageLoader.ImageContainer response, boolean isImmediate) {
                    if (response.getBitmap() != null) {
                        holder.movieImage.setImageBitmap(response.getBitmap());
                    } else {
                        holder.movieImage.setImageBitmap(null);

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

    @Override
    public int getItemCount() {
        return upcomingMovieList.size();

    }

    public void updateList(List<Movies> newMovieList) {
        //update the adapter to display the list of new movies
        this.upcomingMovieList = newMovieList;
        notifyDataSetChanged();
    }

    public class MoviesComingSoonViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        // public int newMovieId;
        public RelativeLayout itemContainer;
        public TextView movieTitle;
        public ImageView movieImage;
        public RatingBar movieRating;
        public TextView movieReleaseDate;
        public TextView movieActors;

        public MoviesComingSoonViewHolder(View itemView) {
            super(itemView);
            movieRating = (RatingBar) itemView.findViewById(R.id.upcomingMovieRatingBar);
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
    }
}