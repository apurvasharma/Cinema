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
import com.cinema.movie.info.model.Movies;

import java.util.List;

/**
 * Created by Apurva on 11/22/2015.
 */
public class NewMoviesListAdapter extends RecyclerView.Adapter<NewMoviesListAdapter.ViewHolder> {

    Context mContext;


    public NewMoviesListAdapter(Context context) {
        this.mContext = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.new_movie_single_row, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
      //  return new PlaceData().placeList().size();
        return 0;
    }

    public void setNewMovieList(List<Movies> newMovieList) {
        //update the adapter to display the list of new movies
        notifyItemRangeChanged(0, newMovieList.size());
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        public LinearLayout newMovieHolder;
        public LinearLayout newMovieNameHolder;
        public TextView newMovieName;
        public ImageView newMovieImage;

        public ViewHolder(View itemView) {
            super(itemView);
            newMovieHolder = (LinearLayout) itemView.findViewById(R.id.newMovieHolder);
            newMovieName = (TextView) itemView.findViewById(R.id.newMovieName);
            newMovieNameHolder = (LinearLayout) itemView.findViewById(R.id.newMovieNameHolder);
            newMovieImage = (ImageView) itemView.findViewById(R.id.newMovieImage);
        }
    }
}