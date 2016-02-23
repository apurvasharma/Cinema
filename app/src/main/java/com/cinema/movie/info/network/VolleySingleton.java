package com.cinema.movie.info.network;

import android.graphics.Bitmap;
import android.util.LruCache;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;

/**
 * Created by Apurva on 11/29/2015.
 */
public class VolleySingleton {
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
