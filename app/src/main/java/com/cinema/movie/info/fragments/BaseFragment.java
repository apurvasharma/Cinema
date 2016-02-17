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
import com.cinema.movie.info.model.MovieImagesResponse;
import com.cinema.movie.info.model.MovieListResponse;
import com.cinema.movie.info.model.Movies;
import com.cinema.movie.info.network.CinemaApplication;
import com.cinema.movie.info.network.VolleySingleton;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Apurva on 12/30/2015.
 */
@SuppressLint("ValidFragment")
public abstract class BaseFragment extends Fragment {
    private Gson gson = new Gson();
    protected ProgressBar mProgressBar;
    protected HashMap<String, MovieImagesResponse.Result> movieImages = new HashMap<>();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return null;
    }


    protected void makeNetworkRequest(String URL) {
        RequestQueue requestQueue = VolleySingleton.getInstance().getRequestQueue();

        //start progress bar
        mProgressBar.setVisibility(View.VISIBLE);

        // Request a string response from the given URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //stop progress bar
                        mProgressBar.setVisibility(View.GONE);

                            MovieListResponse movieResponse = gson.fromJson(response, MovieListResponse.class);
                            List<Movies> movieList = movieResponse.getMovies();
                            if (movieList != null) {
                                updateAdapter(movieList);
                            }
                        }
                        //  Log.d("response = ", response);

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

    public abstract void updateAdapter(List<Movies> movieList);

}
