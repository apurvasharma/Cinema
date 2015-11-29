package com.cinema.movie.info.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cinema.movie.info.R;

/**
 * Created by Apurva on 11/29/2015.
 */
public class UpcomingMoviesListAdapter extends RecyclerView.Adapter<UpcomingMoviesListAdapter.ViewHolder> {

    Context mContext;


    public UpcomingMoviesListAdapter(Context context) {
        this.mContext = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.upcoming_movie_single_row, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
//        final Place place = new PlaceData().placeList().get(position);
//        holder.placeName.setText(place.name);
//        Picasso.with(mContext).load(place.getImageResourceId(mContext)).into(holder.placeImage);
    }

    @Override
    public int getItemCount() {
        //  return new PlaceData().placeList().size();
        return 0;
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        public LinearLayout upcomingMovieHolder;
        public LinearLayout upcomingMovieNameHolder;
        public TextView upcomingMovieName;
        public ImageView upcomingMovieImage;

        public ViewHolder(View itemView) {
            super(itemView);
            upcomingMovieHolder = (LinearLayout) itemView.findViewById(R.id.upcomingMovieHolder);
            upcomingMovieName = (TextView) itemView.findViewById(R.id.upcomingMovieName);
            upcomingMovieNameHolder = (LinearLayout) itemView.findViewById(R.id.upcomingMovieNameHolder);
            upcomingMovieImage = (ImageView) itemView.findViewById(R.id.upcomingMovieImage);
        }
    }
}