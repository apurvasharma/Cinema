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
 * Created by Apurva on 11/22/2015.
 */
public class NewMovieListAdapter extends RecyclerView.Adapter<NewMovieListAdapter.ViewHolder> {

    Context mContext;


    public NewMovieListAdapter(Context context) {
        this.mContext = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.new_movie_list_single_row, parent, false);
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
        public LinearLayout placeHolder;
        public LinearLayout placeNameHolder;
        public TextView placeName;
        public ImageView placeImage;

        public ViewHolder(View itemView) {
            super(itemView);
            placeHolder = (LinearLayout) itemView.findViewById(R.id.mainHolder);
            placeName = (TextView) itemView.findViewById(R.id.placeName);
            placeNameHolder = (LinearLayout) itemView.findViewById(R.id.placeNameHolder);
            placeImage = (ImageView) itemView.findViewById(R.id.placeImage);
        }
    }
}