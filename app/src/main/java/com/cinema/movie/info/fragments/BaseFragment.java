package com.cinema.movie.info.fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

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
import com.cinema.movie.info.adapter.UpcomingMoviesListAdapter;
import com.cinema.movie.info.model.Movies;
import com.cinema.movie.info.model.MovieResponse;
import com.cinema.movie.info.network.CinemaApplication;
import com.cinema.movie.info.network.VolleySingleton;
import com.google.gson.Gson;

import java.util.List;

/**
 * Created by Apurva on 12/30/2015.
 */
@SuppressLint("ValidFragment")
public class BaseFragment extends Fragment {
    private Gson gson = new Gson();
    private NewMoviesListAdapter mNewMoviesListAdapter;
    private UpcomingMoviesListAdapter mUpcomingMoviesListAdapter;

    protected ProgressBar mProgressBar;


    public BaseFragment(Object listAdapter) {
        if (listAdapter instanceof NewMoviesListAdapter){
            mNewMoviesListAdapter = (NewMoviesListAdapter) listAdapter;
        }else if(listAdapter instanceof UpcomingMoviesListAdapter){
            mUpcomingMoviesListAdapter = (UpcomingMoviesListAdapter) listAdapter;
        }

    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return null;
    }


    protected void makeNetworkRequest(String URL) {
        URL = URL.concat(getString(R.string.api_key));
        RequestQueue requestQueue = VolleySingleton.getInstance().getRequestQueue();

        //start progress bar
        mProgressBar.setVisibility(View.VISIBLE);

        // Request a string response from the provided NEW_MOVIES_URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //stop progress bar
                        mProgressBar.setVisibility(View.GONE);
                        MovieResponse movieResponse = gson.fromJson(response, MovieResponse.class);
                        List<Movies> movieList = movieResponse.getMovies();
                        if (movieList != null) {
                            updateNewMovieListAdapter(movieList);
                        }
                        //  Log.d("response = ", response);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //stop progress bar
                mProgressBar.setVisibility(View.GONE);
                Toast.makeText(CinemaApplication.getAppContext(), error.getMessage(), Toast.LENGTH_LONG).show();

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

    private void updateNewMovieListAdapter(List<Movies> movieList) {
        //update the adapter to display the parsed list
        if (mNewMoviesListAdapter != null) {
            mNewMoviesListAdapter.updateList(movieList);
        }else if(mUpcomingMoviesListAdapter != null){
            mUpcomingMoviesListAdapter.updateList(movieList);
        }
    }
}
