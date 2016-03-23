package com.cinema.movie.info.network;

import android.graphics.Bitmap;
import android.util.Log;
import android.util.LruCache;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.NetworkError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.cinema.movie.info.model.MovieDetailsResponse;
import com.cinema.movie.info.model.MovieImagesResponse;
import com.cinema.movie.info.model.MovieListResponse;
import com.google.gson.Gson;

/**
 * Created by Apurva on 2/22/2016.
 */
public class VolleyNetworkRequest {
    private RequestQueue mRequestQueue;
    private Gson mGson;
    private Object mPojoClass;
    public static VolleyNetworkRequest volleyNetworkRequest;

    private VolleyNetworkRequest() {
        mRequestQueue = VolleySingleton.getInstance().getRequestQueue();
        mGson = new Gson();
    }

    public static VolleyNetworkRequest getInstance() {
        if (volleyNetworkRequest == null)
            volleyNetworkRequest = new VolleyNetworkRequest();
        return volleyNetworkRequest;
    }


    public void makeNetworkRequest(final String URL, final Object responseClass, final IVolleyNetworkResponse volleyNetworkResponse, final ProgressBar progressBar) {
        // Request a string response from the given URL.
        progressBar.setVisibility(View.VISIBLE);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //stop progress bar
                        progressBar.setVisibility(View.GONE);
                        mPojoClass = responseClass;
                        if (mPojoClass == MovieImagesResponse.class)
                            mPojoClass = mGson.fromJson(response, MovieImagesResponse.class);
                        else if (mPojoClass == MovieListResponse.class)
                            mPojoClass = mGson.fromJson(response, MovieListResponse.class);
                        else if (mPojoClass == MovieDetailsResponse.class)
                            mPojoClass = mGson.fromJson(response, MovieDetailsResponse.class);

                        volleyNetworkResponse.processNetworkResponse(mPojoClass);
                        Log.d("response = ", response);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //stop progress bar
                progressBar.setVisibility(View.GONE);
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
        mRequestQueue.add(stringRequest);


    }


    /**
     * Created by Apurva on 11/29/2015.
     */
    private static class VolleySingleton {
        private static VolleySingleton singleInstance = null;
        private RequestQueue requestQueue;
        private ImageLoader imageLoader;


        private VolleySingleton() {
            requestQueue = Volley.newRequestQueue(CinemaApplication.getAppContext());
            imageLoader = new ImageLoader(requestQueue, new ImageLoader.ImageCache() {

                private LruCache<String, Bitmap> cache = new LruCache<>((int) (Runtime.getRuntime().maxMemory() / 1024) / 8);

                @Override
                public Bitmap getBitmap(String url) {
                    return cache.get(url);
                }

                @Override
                public void putBitmap(String url, Bitmap bitmap) {
                    cache.put(url, bitmap);
                }
            });
        }

        public static VolleySingleton getInstance() {
            if (singleInstance == null)
                singleInstance = new VolleySingleton();
            return singleInstance;
        }


        public RequestQueue getRequestQueue() {
            return requestQueue;
        }


        public ImageLoader getImageLoader() {
            return imageLoader;
        }


    }
}
