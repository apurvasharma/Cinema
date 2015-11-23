package com.cinema.movie.info.fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.cinema.movie.info.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Apurva on 11/22/2015.
 */
public class NewMoviesFragment extends Fragment {
    int color;
    public NewMoviesFragment() {
    }
    @SuppressLint("ValidFragment")
    public NewMoviesFragment(int color) {
        this.color = color;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.new_movie_list_fragment, container, false);

        return view;
    }
}