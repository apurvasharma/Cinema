package com.cinema.movie.info.adapter;

import android.graphics.Bitmap;
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
import com.cinema.movie.info.model.MovieImagesResponse;
import com.cinema.movie.info.model.Movies;
import com.cinema.movie.info.network.VolleySingleton;
import com.cinema.movie.info.utils.AppUtils;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Apurva on 11/22/2015.
 */
public class NewMoviesListAdapter extends  RecyclerView.Adapter<NewMoviesListAdapter.MoviesInTheaterViewHolder> {

    private List<Movies> newMovieList = Collections.emptyList();
    private ImageLoader imageLoader;
    private static final String logTAG = "NewMoviesListAdapter";
    private InTheatersItemClickListener mItemClickListener;
    private String mApiKey;

    public NewMoviesListAdapter(String api_key) {
        VolleySingleton volleySingleton = VolleySingleton.getInstance();
        imageLoader = volleySingleton.getImageLoader();
        mApiKey = api_key;
    }

    @Override
    public MoviesInTheaterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.new_movie_list_item, parent, false);
        return new MoviesInTheaterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final MoviesInTheaterViewHolder holder, int position) {

        //set value of view at a given position
        Movies movies = newMovieList.get(position);
        holder.movieTitle.setText(movies.getTitle());

        //convert user-rating out of 5
        float userRating = (movies.getRatings().getAudienceScore() * 5) / 100;
        LayerDrawable layerDrawable = (LayerDrawable) holder.movieRating.getProgressDrawable();
        // DrawableCompat.setTint(DrawableCompat.wrap(layerDrawable.getDrawable(0)), Color.RED);   // Empty star
        // DrawableCompat.setTint(DrawableCompat.wrap(layerDrawable.getDrawable(1)), Color.GREEN); // Partial star
        DrawableCompat.setTint(DrawableCompat.wrap(layerDrawable.getDrawable(2)), Color.rgb(229, 193, 0)); // Full star
        holder.movieRating.setRating(userRating);


        String releaseDate = AppUtils.changeDateFormat(movies.getReleaseDates().getTheater());
        holder.movieReleaseDate.setText(releaseDate);

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
        return newMovieList.size();

    }

    public void updateList(List<Movies> newMovieList) {
        //update the adapter to display the list of new movies
        this.newMovieList = newMovieList;
        notifyDataSetChanged();
    }


    public interface InTheatersItemClickListener {
        void onItemClick(View view, int position );
    }

    public void setOnItemClickListener(final InTheatersItemClickListener mItemClickListener) {
        this.mItemClickListener = mItemClickListener;
    }

    public class MoviesInTheaterViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        public RelativeLayout itemContainer;
        public TextView movieTitle;
        public ImageView movieImage;
        public RatingBar movieRating;
        public TextView movieReleaseDate;


        public MoviesInTheaterViewHolder(View itemView) {
            super(itemView);
            movieRating = (RatingBar) itemView.findViewById(R.id.newMovieRatingBar);
            movieTitle = (TextView) itemView.findViewById(R.id.newMovieTitle);
            movieImage = (ImageView) itemView.findViewById(R.id.newMovieImage);
            movieReleaseDate = (TextView) itemView.findViewById(R.id.newMovieReleaseDate);
            itemContainer = (RelativeLayout) itemView.findViewById(R.id.newMovieListItemContainer);
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