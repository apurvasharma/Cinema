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
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.cinema.movie.info.R;
import com.cinema.movie.info.model.Movies;
import com.cinema.movie.info.network.VolleySingleton;

import java.util.Collections;
import java.util.List;

/**
 * Created by Apurva on 11/22/2015.
 */
public class NewMoviesListAdapter extends RecyclerView.Adapter<NewMoviesListAdapter.ViewHolder> {

    Context mContext;
    List<Movies> newMovieList = Collections.emptyList();
    private ImageLoader imageLoader;
    private static final String logTAG = "NewMoviesListAdapter";

    public NewMoviesListAdapter(Context context) {
        this.mContext = context;
        VolleySingleton volleySingleton = VolleySingleton.getInstance();
        imageLoader = volleySingleton.getImageLoader();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.new_movie_list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {

        //set value of view at a given position
        Movies movies = newMovieList.get(position);
        holder.newMovieTitle.setText(movies.getTitle());

        //convert user-rating out of 5
        float userRating = (movies.getRatings().getAudienceScore() * 5) / 100;

        LayerDrawable layerDrawable = (LayerDrawable) holder.newMoviesRating.getProgressDrawable();
        // DrawableCompat.setTint(DrawableCompat.wrap(layerDrawable.getDrawable(0)), Color.RED);   // Empty star
        // DrawableCompat.setTint(DrawableCompat.wrap(layerDrawable.getDrawable(1)), Color.GREEN); // Partial star
        DrawableCompat.setTint(DrawableCompat.wrap(layerDrawable.getDrawable(2)), Color.rgb(229, 193, 0)); // Full star

        holder.newMoviesRating.setRating(userRating);

        //load image from JSON image URL
        String imageURL = movies.getPosters().getThumbnail();

        if (imageURL != null) {
            imageLoader.get(imageURL, new ImageLoader.ImageListener() {
                @Override
                public void onResponse(ImageLoader.ImageContainer response, boolean isImmediate) {
                    holder.newMovieImage.setImageBitmap(response.getBitmap());
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

    public void setNewMovieList(List<Movies> newMovieList) {
        //update the adapter to display the list of new movies
        this.newMovieList = newMovieList;
        notifyItemRangeChanged(0, newMovieList.size());
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        // public int newMovieId;
        public TextView newMovieTitle;
        public ImageView newMovieImage;
        public RatingBar newMoviesRating;

        public ViewHolder(View itemView) {
            super(itemView);
            newMoviesRating = (RatingBar) itemView.findViewById(R.id.ratingBar);
            newMovieTitle = (TextView) itemView.findViewById(R.id.newMovieTitle);
            newMovieImage = (ImageView) itemView.findViewById(R.id.newMovieImage);
        }
    }
}