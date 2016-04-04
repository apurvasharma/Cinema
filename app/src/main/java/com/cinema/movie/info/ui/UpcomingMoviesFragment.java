package com.cinema.movie.info.ui;

import android.annotation.SuppressLint;
import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.cinema.movie.info.R;
import com.cinema.movie.info.adapter.UpcomingMoviesListAdapter;
import com.cinema.movie.info.model.MovieListResponse;
import com.cinema.movie.info.network.IVolleyNetworkResponse;
import com.cinema.movie.info.network.VolleyNetworkRequest;
import com.cinema.movie.info.utils.AppConstants;

import java.util.Collections;
import java.util.List;


/**
 * Created by Apurva on 11/22/2015.
 */
@SuppressLint("ValidFragment")
public class UpcomingMoviesFragment extends Fragment implements IVolleyNetworkResponse {

    private List<MovieListResponse.Movies> mMovieList = Collections.emptyList();
    private UpcomingMoviesListAdapter mListAdapter;
    private static final String VOLLEY_TAG_FOR_UPCOMING_MOVIES = "VOLLEY_TAG_FOR_UPCOMING_MOVIES";
    public UpcomingMoviesFragment(UpcomingMoviesListAdapter listAdapter) {
        mListAdapter = listAdapter;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.upcoming_movie_list_fragment, container, false);

        ProgressBar mProgressBar = (ProgressBar) view.findViewById(R.id.upcomingMovieProgressBar);

        RecyclerView mRecyclerView = (RecyclerView) view.findViewById(R.id.upcomingMoviesRecyclerView);
      //  mRecyclerView.addItemDecoration(new SimpleDividerItemDecoration(getResources()));
        // mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
       // mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        mRecyclerView.setAdapter(mListAdapter);
        mListAdapter.setOnItemClickListener(onItemClickListener);
        VolleyNetworkRequest.getInstance().makeNetworkRequest(AppConstants.UPCOMING_MOVIES_URL.concat(getString(R.string.rt_api_key)), MovieListResponse.class, this, mProgressBar, VOLLEY_TAG_FOR_UPCOMING_MOVIES);
        return view;
    }



    UpcomingMoviesListAdapter.UpcomingItemClickListener onItemClickListener = new UpcomingMoviesListAdapter.UpcomingItemClickListener() {
        @Override
        public void onItemClick(View v, int position) {
           // Toast.makeText(CinemaApplication.getAppContext(), "Upcoming: " + position, Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(getActivity(), MovieDetailsActivity.class);

            View sharedView = v.findViewById(R.id.upcomingMovieTitle);
            String transitionName = getString(R.string.element_transition_name);

            ActivityOptions transitionActivityOptions = ActivityOptions.makeSceneTransitionAnimation(getActivity(), sharedView, transitionName);
            intent.putExtra(AppConstants.MOVIE_ID, mMovieList.get(position).getId());
            intent.putExtra(AppConstants.MOVIE_TITLE, mMovieList.get(position).getTitle());
            getActivity().startActivity(intent, transitionActivityOptions.toBundle());

        }
    };

    @Override
    public void processNetworkResponse(Object pojoClass) {
        if ((pojoClass != null) && (pojoClass instanceof MovieListResponse)) {
            mMovieList = ((MovieListResponse) pojoClass).getMovies();
            mListAdapter.updateList(mMovieList);
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        VolleyNetworkRequest.getInstance().cancelPendingRequest(VOLLEY_TAG_FOR_UPCOMING_MOVIES);
    }

}