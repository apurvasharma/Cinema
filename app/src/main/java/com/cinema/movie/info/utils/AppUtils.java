package com.cinema.movie.info.utils;

import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.util.Log;

import com.android.volley.NetworkError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.cinema.movie.info.model.MovieImagesResponse;
import com.cinema.movie.info.network.VolleySingleton;
import com.google.gson.Gson;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

/**
 * Created by Apurva on 2/12/2016.
 */
public class AppUtils {
    private static Gson gson = new Gson();


    /**
     * getResizedBitmap method is used to Resized the Image according to custom width and height
     *
     * @param image newHeight (new desired height)
     *              newWidth  (new desired Width)
     * @return image (new resized image)
     */
    public static Bitmap getResizedBitmap(Bitmap image) {
        int width = image.getWidth();
        int height = image.getHeight();
//        float newHeight = (float) (height * (0.65));
//        float newWidth = (float) (width * (0.65));
        int newHeight = (height / 5);
        int newWidth = (width / 5);
        float scaleWidth = ((float) newWidth) / width;
        float scaleHeight = ((float) newHeight) / height;
        // create a matrix for the manipulation
        Matrix matrix = new Matrix();
        // resize the bit map
        matrix.postScale(scaleWidth, scaleHeight);
        // recreate the new Bitmap
        Bitmap resizedBitmap = Bitmap.createBitmap(image, 0, 0, width, height,
                matrix, false);
        return resizedBitmap;
    }


    public static HashMap<String, MovieImagesResponse.Result> makeNetworkRequest(String URL) {
        RequestQueue requestQueue = VolleySingleton.getInstance().getRequestQueue();
        final HashMap<String, MovieImagesResponse.Result> movieImages = new HashMap<>();

        // Request a string response from the given URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {


                        MovieImagesResponse movieImagesResponse = gson.fromJson(response, MovieImagesResponse.class);
                        List<MovieImagesResponse.Result> movieList = movieImagesResponse.getResults();
                        if (movieList != null) {
                            for (MovieImagesResponse.Result movie :
                                    movieList) {
                                if (!movieImages.containsKey(movie.getTitle()))
                                    movieImages.put(movie.getTitle().toLowerCase(), movie);
                            }

                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                //Toast.makeText(CinemaApplication.getAppContext(), error.getMessage(), Toast.LENGTH_LONG).show();

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
        return movieImages;
    }


    public static String changeDateFormat(String inputDate) {
        DateFormat srcDf = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
        Date date;

        try {
            // parse the date string into Date object
            date = srcDf.parse(inputDate);
            DateFormat destDf = new SimpleDateFormat("MMM dd, yyyy", Locale.US);
            // format the date into another format
            return destDf.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }


}
