package com.cinema.movie.info.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.cinema.movie.info.R;
import com.cinema.movie.info.utils.AppConstants;

/**
 * Created by Apurva on 2/10/2016.
 */
public class MovieDetailsActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.movie_details);

        Intent intent = getIntent();
        String movieId = intent.getStringExtra(AppConstants.MOVIE_ID);
    }
}
