package com.cinema.movie.info.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.cinema.movie.info.R;
import com.cinema.movie.info.model.MovieDetailsResponse;
import com.cinema.movie.info.model.MovieImagesResponse;
import com.cinema.movie.info.network.IVolleyNetworkResponse;
import com.cinema.movie.info.network.VolleyNetworkRequest;
import com.cinema.movie.info.utils.AppConstants;
import com.cinema.movie.info.utils.AppUtils;

import java.util.List;

/**
 * Created by Apurva on 2/10/2016.
 */
public class MovieDetailsActivity extends AppCompatActivity implements IVolleyNetworkResponse {
    private ImageLoader imageLoader;
    private NetworkImageView mBackdrop;
    private ProgressBar mProgressBar;
    private static final String VOLLEY_TAG_FOR_MOVIE_DETAILS= "VOLLEY_TAG_FOR_MOVIE_DETAILS";
    private TextView mMovieTitleTV, mSynopsis, mCast, mDirector, mRuntime, mGenre, mRating;
    private static final String comma = ", ";




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.movie_details);
        VolleyNetworkRequest volley = VolleyNetworkRequest.getInstance();
        imageLoader = volley.getImageLoader();
        mProgressBar = (ProgressBar) findViewById(R.id.movieDetailsProgressBar);
        mMovieTitleTV = (TextView) findViewById(R.id.collapsingToolbarTitle);
        mBackdrop = (NetworkImageView) findViewById(R.id.backdrop);
        mSynopsis = (TextView) findViewById(R.id.synopsis);
        mCast = (TextView) findViewById(R.id.cast_info);
        mDirector = (TextView) findViewById(R.id.director_info);
        mRuntime = (TextView) findViewById(R.id.runtime_info);
        mRating = (TextView) findViewById(R.id.movieDetailRating);
        mGenre = (TextView) findViewById(R.id.genre_info);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Intent intent = getIntent();
        String movieId = intent.getStringExtra(AppConstants.MOVIE_ID);
        String movieTitle = intent.getStringExtra(AppConstants.MOVIE_TITLE);
        if ((movieTitle != null) && (movieId != null)) {
            String title = movieTitle.replaceAll(" ", "%20");
            mMovieTitleTV.setText(movieTitle);
            VolleyNetworkRequest.getInstance().makeNetworkRequest(AppConstants.getMovieBackdropUrl(getString(R.string.mdb_api_key), title), MovieImagesResponse.class, this, mProgressBar, VOLLEY_TAG_FOR_MOVIE_DETAILS);
            VolleyNetworkRequest.getInstance().makeNetworkRequest(AppConstants.getMovieDetailsUrl(getString(R.string.rt_api_key), movieId), MovieDetailsResponse.class, this, mProgressBar, VOLLEY_TAG_FOR_MOVIE_DETAILS);
        }
    }

    private void displayBackdrop(MovieImagesResponse movieImagesResponse) {
        if (movieImagesResponse.getResults() != null && movieImagesResponse.getResults().size() > 0) {
            String backdropPath = movieImagesResponse.getResults().get(0).getBackdropPath();
            mBackdrop.setImageUrl(AppConstants.getPosterURl(backdropPath), imageLoader);

        }
    }


    @Override
    public void processNetworkResponse(Object pojoClass) {
        if (pojoClass == null) {
            return;
        }
        if (pojoClass instanceof MovieImagesResponse) {
            displayBackdrop((MovieImagesResponse) pojoClass);
        } else if (pojoClass instanceof MovieDetailsResponse) {
            displayMovieDetails((MovieDetailsResponse) pojoClass);
        }
    }

    private void displayMovieDetails(MovieDetailsResponse movie) {
        String rating = AppUtils.getRating(movie.getRatings().getAudienceScore());
        mRating.setText(rating);

        mSynopsis.setText(movie.getSynopsis());
        List<MovieDetailsResponse.AbridgedCast> castList = movie.getAbridgedCast();
        StringBuilder cast = new StringBuilder();
        for(int i=0;i<castList.size();i++){
            cast.append(castList.get(i).getName());
            if(i!=castList.size()-1){
                cast.append(comma);
            }
        }
        mCast.setText(cast.toString());
        StringBuilder director = new StringBuilder();
        List<MovieDetailsResponse.AbridgedDirector> directorList = movie.getAbridgedDirectors();
        for (int i = 0; i < directorList.size(); i++) {
                director.append(directorList.get(i).getName());
                if(i!=directorList.size()-1){
                    director.append(comma);
                }
        }

        mDirector.setText(director.toString());
        String runtime = AppUtils.getMovieRuntime(movie.getRuntime());
        mRuntime.setText(runtime);

        List<String> genreList = movie.getGenres();
        StringBuilder genre = new StringBuilder();
        for (int i = 0; i < genreList.size(); i++) {
            genre.append(genreList.get(i));
            if (i != genreList.size() - 1) {
                genre.append(comma);
            }
        }
        mGenre.setText(genre.toString());
    }

    @Override
    public void onStop() {
        super.onStop();
        VolleyNetworkRequest.getInstance().cancelPendingRequest(VOLLEY_TAG_FOR_MOVIE_DETAILS);
    }

}
