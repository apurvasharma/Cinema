package com.cinema.movie.info.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.android.volley.NetworkError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.cinema.movie.info.R;
import com.cinema.movie.info.adapter.NewMoviesListAdapter;
import com.cinema.movie.info.model.Movies;
import com.cinema.movie.info.model.NewMovies;
import com.cinema.movie.info.network.VolleySingleton;
import com.cinema.movie.info.utils.AppConstants;
import com.google.gson.Gson;

import java.util.List;

/**
 * Created by Apurva on 11/22/2015.
 */
public class NewMoviesFragment extends Fragment {
    private ProgressBar progressBar;
    private Gson gson = new Gson();
    private NewMoviesListAdapter newMoviesListAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.new_movie_list_fragment, container, false);

        progressBar = (ProgressBar) view.findViewById(R.id.progress_bar);

        RecyclerView mRecyclerView = (RecyclerView) view.findViewById(R.id.newMoviesRecyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        newMoviesListAdapter = new NewMoviesListAdapter(getActivity());
        mRecyclerView.setAdapter(newMoviesListAdapter);

        makeNetworkRequest(AppConstants.NEW_MOVIES_URL + getString(R.string.api_key));
        return view;
    }


    private void makeNetworkRequest(String URL) {

        RequestQueue requestQueue = VolleySingleton.getInstance().getRequestQueue();

        //start progress bar
        progressBar.setVisibility(View.VISIBLE);

        // Request a string response from the provided NEW_MOVIES_URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //stop progress bar
                        progressBar.setVisibility(View.GONE);
                        updateNewMovieListAdapter(response);
                        Log.d("response = ", response);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //stop progress bar
                progressBar.setVisibility(View.GONE);
                NetworkResponse networkResponse = error.networkResponse;
                if (networkResponse != null) {

                    //if web server fails to response
                    if (networkResponse.statusCode == 404) {
                        Log.d("ERROR = ", networkResponse.toString());

                    }

                    //if unable to connect to internet
                    if (error instanceof NetworkError || error instanceof TimeoutError) {
                        Log.d("ERROR = ", networkResponse.toString());

                    }

                }
            }
        });

        // Add the request to the Volley RequestQueue
        requestQueue.add(stringRequest);
    }

    private void updateNewMovieListAdapter(String response) {
        NewMovies newMovies = gson.fromJson(response, NewMovies.class);
        List<Movies> newMovieList= newMovies.getMovies();
        if (newMovieList != null) {
            //update the adapter to display the parsed list
            newMoviesListAdapter.setNewMovieList(newMovieList);
        }
    }
}