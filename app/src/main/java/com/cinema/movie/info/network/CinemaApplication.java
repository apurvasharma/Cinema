package com.cinema.movie.info.network;

import android.app.Application;
import android.content.Context;
import android.graphics.Bitmap;
import android.util.LruCache;

/**
 * Created by Apurva on 11/29/2015.
 */
public class CinemaApplication extends Application {
    private static CinemaApplication singleInstance;
    private LruCache<String, Bitmap> mMemoryCache;

    @Override
    public void onCreate() {
        super.onCreate();
        singleInstance = this;

        // Get max available VM memory, exceeding this amount will throw an
        // OutOfMemory exception. Stored in kilobytes as LruCache takes an
        // int in its constructor.
        final int maxMemory = (int) (Runtime.getRuntime().maxMemory() / 1024);

        // Use 1/8th of the available memory for this memory cache.
        final int cacheSize = maxMemory / 8;

        mMemoryCache = new LruCache<String, Bitmap>(cacheSize) {
            @Override
            protected int sizeOf(String key, Bitmap bitmap) {
                // The cache size will be measured in kilobytes rather than
                // number of items.
                return bitmap.getByteCount() / 1024;
            }
        };
    }


    public static CinemaApplication getInstance() {
        return singleInstance;
    }

    public static Context getAppContext() {
        return singleInstance.getApplicationContext();
    }

    public LruCache<String, Bitmap> getMemoryCache() {
        return mMemoryCache;
    }

}
