package com.cinema.movie.info.fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cinema.movie.info.R;

/**
 * Created by Apurva on 11/22/2015.
 */
public class UpcomingMoviesFragment extends Fragment {
    int color;
    public UpcomingMoviesFragment() {
    }
    @SuppressLint("ValidFragment")
    public UpcomingMoviesFragment(int color) {
        this.color = color;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.upcoming_movie_list_fragment, container, false);

        return view;
    }
}