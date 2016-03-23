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
 * Created by Apurva on 11/22/2015.
 */
public class NewMoviesListAdapter extends  RecyclerView.Adapter<NewMoviesListAdapter.MoviesInTheaterViewHolder> {

    private List<MovieListResponse.Movies> newMovieList = Collections.emptyList();
    private ImageLoader imageLoader;
    private static final String logTAG = "NewMoviesListAdapter";
    private InTheatersItemClickListener mItemClickListener;

    public NewMoviesListAdapter() {
        VolleyNetworkRequest.VolleySingleton volleySingleton = VolleyNetworkRequest.VolleySingleton.getInstance();
        imageLoader = volleySingleton.getImageLoader();
    }

    @Override
    public MoviesInTheaterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.new_movie_list_item, parent, false);
        return new MoviesInTheaterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final MoviesInTheaterViewHolder holder, int position) {


        MovieListResponse.Movies movies = newMovieList.get(position);

        //set title
        holder.movieTitle.setText(movies.getTitle());

        //convert user-rating out of 10
        double score = movies.getRatings().getAudienceScore();
        float userRating = (float) ((score / 100) * 10);
        String rating = "" + userRating;
        holder.movieRating.setText(rating);


        //set release Date
        String releaseDate = AppUtils.changeDateFormat(movies.getReleaseDates().getTheater());
        holder.movieReleaseDate.setText(releaseDate);

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
        return newMovieList.size();

    }

    public void updateList(List<MovieListResponse.Movies> newMovieList) {
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
        public TextView movieRating;
        public TextView movieReleaseDate;
        public TextView movieActors;


        public MoviesInTheaterViewHolder(View itemView) {
            super(itemView);
            movieRating = (TextView) itemView.findViewById(R.id.newMovieRating);
            movieTitle = (TextView) itemView.findViewById(R.id.newMovieTitle);
            movieImage = (ImageView) itemView.findViewById(R.id.newMovieImage);
            movieReleaseDate = (TextView) itemView.findViewById(R.id.newMovieReleaseDate);
            itemContainer = (RelativeLayout) itemView.findViewById(R.id.newMovieListItemContainer);
            movieActors = (TextView) itemView.findViewById(R.id.newMovieActors);
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