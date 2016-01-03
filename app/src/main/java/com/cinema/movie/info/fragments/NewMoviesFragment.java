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
import android.widget.Toast;
import com.cinema.movie.info.R;
import com.cinema.movie.info.adapter.NewMoviesListAdapter;
import com.cinema.movie.info.network.CinemaApplication;
import com.cinema.movie.info.utils.AppConstants;
import com.cinema.movie.info.utils.SimpleDividerItemDecoration;


/**
 * Created by Apurva on 11/22/2015.
 */
@SuppressLint("ValidFragment")
public class NewMoviesFragment extends BaseFragment {


    private NewMoviesListAdapter mListAdapter;

    public NewMoviesFragment(NewMoviesListAdapter listAdapter) {
        super(listAdapter);
        mListAdapter = listAdapter;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.new_movie_list_fragment, container, false);

        ProgressBar mProgressBar = (ProgressBar) view.findViewById(R.id.newMovieProgressBar);
        super.mProgressBar = mProgressBar;

        RecyclerView mRecyclerView = (RecyclerView) view.findViewById(R.id.newMoviesRecyclerView);
        mRecyclerView.addItemDecoration(new SimpleDividerItemDecoration(getResources()));
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setAdapter(mListAdapter);
        mListAdapter.setOnItemClickListener(onItemClickListener);
        makeNetworkRequest(AppConstants.NEW_MOVIES_URL);
        return view;
    }

    NewMoviesListAdapter.OnItemClickListener onItemClickListener = new NewMoviesListAdapter.OnItemClickListener() {
        @Override
        public void onItemClick(View v, int position) {
            Toast.makeText(CinemaApplication.getAppContext(), "In Theaters " + position, Toast.LENGTH_SHORT).show();
        }
    };


}