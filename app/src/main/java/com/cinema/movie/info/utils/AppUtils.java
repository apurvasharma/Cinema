package com.cinema.movie.info.utils;

import android.graphics.Bitmap;
import android.graphics.Matrix;

import com.google.gson.Gson;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Apurva on 2/12/2016.
 */
public class AppUtils {
    private static Gson gson = new Gson();
    private static String sMovieRuntime;


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


    public static String getRating(double score) {
        //convert user-rating out of 10
        float userRating = (float) ((score / 100) * 10);
        return "" + userRating;
    }

    public static String getMovieRuntime(int runtime) {
        int hours = runtime / 60;
        int minutes = runtime % 60;
        return String.valueOf(hours) + "h " + minutes + "m";
    }
}
