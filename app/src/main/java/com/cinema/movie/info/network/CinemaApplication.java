package com.cinema.movie.info.network;

import android.app.Application;
import android.content.Context;

/**
 * Created by Apurva on 11/29/2015.
 */
public class CinemaApplication extends Application {
    private static CinemaApplication singleInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        singleInstance = this;
    }


    public static CinemaApplication getInstance() {
        return singleInstance;
    }

    public static Context getAppContext() {
        return singleInstance.getApplicationContext();
    }
}
