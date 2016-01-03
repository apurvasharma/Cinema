package com.cinema.movie.info.adapter;

import android.content.Context;
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

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Created by Apurva on 11/29/2015.
 */
public class UpcomingMoviesListAdapter extends  RecyclerView.Adapter<UpcomingMoviesListAdapter.CustomViewHolder> {

    Context mContext;
    List<Movies> upcomingMovieList = Collections.emptyList();
    private ImageLoader imageLoader;
    private static final String logTAG = "UpcomingMoviesAdapter";
    OnItemClickListener mItemClickListener;

    public UpcomingMoviesListAdapter(Context context) {
        this.mContext = context;
        VolleySingleton volleySingleton = VolleySingleton.getInstance();
        imageLoader = volleySingleton.getImageLoader();
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    public void setOnItemClickListener(final OnItemClickListener mItemClickListener) {
        this.mItemClickListener = mItemClickListener;
    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.upcoming_movie_list_item, parent, false);
        return new CustomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final CustomViewHolder holder, int position) {

        //set value of view at a given position
        Movies movies = upcomingMovieList.get(position);
        holder.movieTitle.setText(movies.getTitle());

        String releaseDate= movies.getReleaseDates().getTheater();
        DateFormat srcDf = new SimpleDateFormat("yyyy-mm-dd", Locale.US);
        Date date;

        try {
            // parse the date string into Date object
            date = srcDf.parse(releaseDate);
            DateFormat destDf = new SimpleDateFormat("MMM dd, yyyy",Locale.US);
            // format the date into another format
            releaseDate = destDf.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        holder.movieReleaseDate.setText(releaseDate);

        //convert user-rating out of 5
        float userRating = (movies.getRatings().getAudienceScore() * 5) / 100;

        LayerDrawable layerDrawable = (LayerDrawable) holder.movieRating.getProgressDrawable();
        DrawableCompat.setTint(DrawableCompat.wrap(layerDrawable.getDrawable(2)), Color.rgb(229, 193, 0)); // Full star

        holder.movieRating.setRating(userRating);

        //load image from JSON image URL
        String imageURL = movies.getPosters().getThumbnail();

        if (imageURL != null) {
            imageLoader.get(imageURL, new ImageLoader.ImageListener() {
                @Override
                public void onResponse(ImageLoader.ImageContainer response, boolean isImmediate) {
                    holder.movieImage.setImageBitmap(response.getBitmap());
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
        notifyItemRangeChanged(0, newMovieList.size());
    }

    public class CustomViewHolder extends  RecyclerView.ViewHolder implements View.OnClickListener{

        // public int newMovieId;
        public RelativeLayout itemContainer;
        public TextView movieTitle;
        public ImageView movieImage;
        public RatingBar movieRating;
        public TextView movieReleaseDate;

        public CustomViewHolder(View itemView) {
            super(itemView);
            movieRating = (RatingBar) itemView.findViewById(R.id.upcomingMovieRatingBar);
            movieTitle = (TextView) itemView.findViewById(R.id.upcomingMovieTitle);
            movieImage = (ImageView) itemView.findViewById(R.id.upcomingMovieImage);
            movieReleaseDate = (TextView) itemView.findViewById(R.id.upcomingMovieReleaseDate);
            itemContainer = (RelativeLayout) itemView.findViewById(R.id.upcomingMovieListItemContainer);
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