package com.cinema.movie.info.fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import com.cinema.movie.info.R;
import com.cinema.movie.info.adapter.UpcomingMoviesListAdapter;
import com.cinema.movie.info.utils.AppConstants;
import com.cinema.movie.info.utils.SimpleDividerItemDecoration;


/**
 * Created by Apurva on 11/22/2015.
 */
@SuppressLint("ValidFragment")
public class UpcomingMoviesFragment extends BaseFragment {


    private UpcomingMoviesListAdapter mListAdapter;
    private ProgressBar mProgressBar;

    public UpcomingMoviesFragment(UpcomingMoviesListAdapter listAdapter) {
        super(listAdapter);
        mListAdapter = listAdapter;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.upcoming_movie_list_fragment, container, false);

        mProgressBar = (ProgressBar) view.findViewById(R.id.upcomingMovieProgressBar);
        super.mProgressBar = this.mProgressBar;

        RecyclerView mRecyclerView = (RecyclerView) view.findViewById(R.id.upcomingMoviesRecyclerView);
        mRecyclerView.addItemDecoration(new SimpleDividerItemDecoration(getResources()));
//        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setAdapter(mListAdapter);

        makeNetworkRequest(AppConstants.UPCOMING_MOVIES_URL);
        return view;
    }



}