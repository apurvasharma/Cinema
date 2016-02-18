package com.cinema.movie.info.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
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
import com.cinema.movie.info.model.MovieDetailsResponse;
import com.cinema.movie.info.network.CinemaApplication;
import com.cinema.movie.info.network.VolleySingleton;
import com.cinema.movie.info.utils.AppConstants;
import com.google.gson.Gson;

/**
 * Created by Apurva on 2/10/2016.
 */
public class MovieDetailsActivity extends AppCompatActivity {
    private ProgressBar mProgressBar;
    private Gson gson = new Gson();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.movie_details);

        TextView mMovieTitleTV = (TextView) findViewById(R.id.collapsingToolbarTitle);
        Intent intent = getIntent();
        String movieId = intent.getStringExtra(AppConstants.MOVIE_ID);
        String movieTitle = intent.getStringExtra(AppConstants.MOVIE_TITLE);
        mMovieTitleTV.setText(movieTitle);
      //  makeNetworkRequest(AppConstants.getMovieDetailsUrl(movieId));
    }

    protected void makeNetworkRequest(String URL) {
        URL = URL.concat(getString(R.string.rt_api_key));
        RequestQueue requestQueue = VolleySingleton.getInstance().getRequestQueue();

        //start progress bar;
        mProgressBar.setVisibility(View.VISIBLE);

        // Request a string response from the given URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //stop progress bar
                        mProgressBar.setVisibility(View.GONE);


                            MovieDetailsResponse movieDetailsResponse = gson.fromJson(response, MovieDetailsResponse.class);

                            if (movieDetailsResponse != null) {
                                display(movieDetailsResponse);
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

    private void display(MovieDetailsResponse movieDetailsResponse) {

    }

}
