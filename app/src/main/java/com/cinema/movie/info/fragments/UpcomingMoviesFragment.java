package com.cinema.movie.info.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cinema.movie.info.R;
import com.cinema.movie.info.adapter.NewMoviesListAdapter;
import com.cinema.movie.info.adapter.UpcomingMoviesListAdapter;

/**
 * Created by Apurva on 11/22/2015.
 */
public class UpcomingMoviesFragment extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.upcoming_movie_list_fragment, container, false);


        RecyclerView mRecyclerView = (RecyclerView) view.findViewById(R.id.upcomingMoviesRecyclerView);
        StaggeredGridLayoutManager mStaggeredLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(mStaggeredLayoutManager);

        UpcomingMoviesListAdapter upcomingMoviesListAdapter = new UpcomingMoviesListAdapter(getActivity());
        mRecyclerView.setAdapter(upcomingMoviesListAdapter);

        return view;
    }
}