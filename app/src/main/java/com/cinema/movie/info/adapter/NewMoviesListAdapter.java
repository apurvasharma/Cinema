package com.cinema.movie.info.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
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
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.new_movie_single_row, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {

        //set value of view at a given position
        Movies movies = newMovieList.get(position);
        holder.newMovieTitle.setText(movies.getTitle());

        //load image from JSON image URL
        String imageURL = movies.getPosters().getThumbnail();

        holder.newMovieImage.setScaleType(ImageView.ScaleType.FIT_CENTER);

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
        // public LinearLayout newMovieHolder;
        // public LinearLayout newMovieNameHolder;
        // public int newMovieId;
        public TextView newMovieTitle;
        public ImageView newMovieImage;

        public ViewHolder(View itemView) {
            super(itemView);
            //   newMovieHolder = (LinearLayout) itemView.findViewById(R.id.newMovieHolder);
            //  newMovieNameHolder = (LinearLayout) itemView.findViewById(R.id.newMovieNameHolder);
            newMovieTitle = (TextView) itemView.findViewById(R.id.newMovieTitle);
            newMovieImage = (ImageView) itemView.findViewById(R.id.newMovieImage);
        }
    }
}