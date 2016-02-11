package com.cinema.movie.info.fragments;

import android.annotation.SuppressLint;
import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.cinema.movie.info.R;
import com.cinema.movie.info.activity.MovieDetailsActivity;
import com.cinema.movie.info.adapter.UpcomingMoviesListAdapter;
import com.cinema.movie.info.model.Movies;
import com.cinema.movie.info.network.CinemaApplication;
import com.cinema.movie.info.utils.AppConstants;
import com.cinema.movie.info.utils.SimpleDividerItemDecoration;

import java.util.Collections;
import java.util.List;


/**
 * Created by Apurva on 11/22/2015.
 */
@SuppressLint("ValidFragment")
public class UpcomingMoviesFragment extends BaseFragment {

    private List<Movies> mMovieList = Collections.emptyList();
    private UpcomingMoviesListAdapter mListAdapter;

    public UpcomingMoviesFragment(UpcomingMoviesListAdapter listAdapter) {
        mListAdapter = listAdapter;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.upcoming_movie_list_fragment, container, false);

        super.mProgressBar = (ProgressBar) view.findViewById(R.id.upcomingMovieProgressBar);

        RecyclerView mRecyclerView = (RecyclerView) view.findViewById(R.id.upcomingMoviesRecyclerView);
        mRecyclerView.addItemDecoration(new SimpleDividerItemDecoration(getResources()));
        // mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setAdapter(mListAdapter);
        mListAdapter.setOnItemClickListener(onItemClickListener);
        makeNetworkRequest(AppConstants.UPCOMING_MOVIES_URL);
        return view;
    }

    @Override
    public void updateAdapter(List<Movies> movieList) {
        mMovieList = movieList;
        mListAdapter.updateList(movieList);
    }

    UpcomingMoviesListAdapter.UpcomingItemClickListener onItemClickListener = new UpcomingMoviesListAdapter.UpcomingItemClickListener() {
        @Override
        public void onItemClick(View v, int position) {
           // Toast.makeText(CinemaApplication.getAppContext(), "Upcoming: " + position, Toast.LENGTH_SHORT).show();
            Intent i = new Intent(getActivity(), MovieDetailsActivity.class);

            View sharedView = v.findViewById(R.id.upcomingMovieTitle);
            String transitionName = getString(R.string.element_transition_name);

            ActivityOptions transitionActivityOptions = ActivityOptions.makeSceneTransitionAnimation(getActivity(), sharedView, transitionName);
            getActivity().startActivity(i, transitionActivityOptions.toBundle());

        }
    };

}