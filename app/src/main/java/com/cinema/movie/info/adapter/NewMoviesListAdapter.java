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
import com.android.volley.toolbox.NetworkImageView;
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
    private static final String logTAG = NewMoviesListAdapter.class.getSimpleName();
    private InTheatersItemClickListener mItemClickListener;

    public NewMoviesListAdapter() {
        VolleyNetworkRequest volley = VolleyNetworkRequest.getInstance();
        imageLoader = volley.getImageLoader();
    }

    @Override
    public MoviesInTheaterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.new_movie_list_item, parent, false);
        return new MoviesInTheaterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final MoviesInTheaterViewHolder holder, int position) {
        MovieListResponse.Movies movie = newMovieList.get(position);
        holder.bind(movie);
    }


    @Override
    public int getItemCount() {
        return newMovieList.size();

    }

    public void updateList(List<MovieListResponse.Movies> newMovieList) {
        //update the adapter to display the list of new movies
        this.newMovieList = newMovieList;
      //  notifyDataSetChanged();
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
        public NetworkImageView movieImage;
        public TextView movieRating;
        public TextView movieReleaseDate;
        public TextView movieActors;


        public MoviesInTheaterViewHolder(View itemView) {
            super(itemView);
            movieRating = (TextView) itemView.findViewById(R.id.newMovieRating);
            movieTitle = (TextView) itemView.findViewById(R.id.newMovieTitle);
            movieImage = (NetworkImageView) itemView.findViewById(R.id.newMovieImage);
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

        public void bind(MovieListResponse.Movies movie) {
            //set title
            movieTitle.setText(movie.getTitle());

            //convert user-rating out of 10
            double score = movie.getRatings().getAudienceScore();
            float userRating = (float) ((score / 100) * 10);
            String rating = "" + userRating;
            movieRating.setText(rating);


            //set release Date
            String releaseDate = AppUtils.changeDateFormat(movie.getReleaseDates().getTheater());
            movieReleaseDate.setText(releaseDate);

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
            movieImage.setImageUrl(imageURL,imageLoader);
        }
    }


}