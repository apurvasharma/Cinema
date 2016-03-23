package com.cinema.movie.info.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.cinema.movie.info.R;
import com.cinema.movie.info.model.MovieImagesResponse;
import com.cinema.movie.info.network.VolleyNetworkRequest;
import com.cinema.movie.info.network.IVolleyNetworkResponse;
import com.cinema.movie.info.utils.AppConstants;

/**
 * Created by Apurva on 2/10/2016.
 */
public class MovieDetailsActivity extends AppCompatActivity implements IVolleyNetworkResponse {
    private ImageLoader imageLoader;
    private ImageView mBackdrop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.movie_details);
        VolleyNetworkRequest.VolleySingleton volleySingleton = VolleyNetworkRequest.VolleySingleton.getInstance();
        imageLoader = volleySingleton.getImageLoader();
        ProgressBar mProgressBar = (ProgressBar) findViewById(R.id.movieDetailsProgressBar);
        TextView mMovieTitleTV = (TextView) findViewById(R.id.collapsingToolbarTitle);
        mBackdrop = (ImageView) findViewById(R.id.backdrop);
        Intent intent = getIntent();
        String movieId = intent.getStringExtra(AppConstants.MOVIE_ID);
        String movieTitle = intent.getStringExtra(AppConstants.MOVIE_TITLE);
        if ((movieTitle != null) && (movieId != null)) {
            String title = movieTitle.replaceAll(" ", "%20");
            mMovieTitleTV.setText(movieTitle);
            // VolleyNetworkRequest.getInstance().makeNetworkRequest(AppConstants.getMovieDetailsUrl(movieId),this);
            VolleyNetworkRequest.getInstance().makeNetworkRequest(AppConstants.getMovieBackdropUrl(getString(R.string.mdb_api_key), title),MovieImagesResponse.class, this, mProgressBar);

        }
    }


    private void displayBackdrop(MovieImagesResponse movieImagesResponse) {
        if (movieImagesResponse.getResults() != null && movieImagesResponse.getResults().size() > 0) {
            String backdropPath = movieImagesResponse.getResults().get(0).getBackdropPath();
            if (backdropPath != null) {

                imageLoader.get(AppConstants.getPosterURl(backdropPath), new ImageLoader.ImageListener() {
                    @Override
                    public void onResponse(ImageLoader.ImageContainer response, boolean isImmediate) {
                        if (response.getBitmap() != null) {
                            mBackdrop.setImageBitmap(response.getBitmap());
                        }
                    }

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("class", "VolleyError");
                    }
                });
            }
        }
    }


    @Override
    public void processNetworkResponse(Object pojoClass) {
        if ((pojoClass != null) && (pojoClass instanceof MovieImagesResponse))
            displayBackdrop((MovieImagesResponse) pojoClass);
    }
}
