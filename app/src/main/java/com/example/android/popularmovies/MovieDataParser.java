package com.example.android.popularmovies;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.drawable.Drawable;
import android.text.format.Time;
import android.util.Log;
import android.view.animation.Transformation;

import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 * Created by vikashkumarbijarnia on 24/07/16.
 */
public class MovieDataParser {
    Context context;
    MovieDataParser(Context context){
        this.context=context;
    }

    public ArrayList<MovieModelClass> getWeatherDataFromJson(String movieJsonStr)
                throws JSONException {
            ArrayList<MovieModelClass> arrayList=new ArrayList<MovieModelClass>();
            MovieModelClass tempObject;
            JSONObject jsonObject=new JSONObject(movieJsonStr);
            JSONArray jsonArray=jsonObject.getJSONArray("results");
            for(int i=0;i<jsonArray.length();i++){
                JSONObject singleMovie=jsonArray.getJSONObject(i);
                tempObject=new MovieModelClass();
                tempObject.setTitle(singleMovie.getString("original_title"));
                tempObject.setReleaseDate(singleMovie.getString("release_date"));
                tempObject.setOverviewOfMovie(singleMovie.getString("overview"));
                tempObject.setUserRating(singleMovie.getString("vote_average"));
                tempObject.setImageThumbnailUrl(getBitmap("https://image.tmdb.org/t/p/original"+singleMovie.getString("poster_path")));
                arrayList.add(tempObject);
            }
            Log.v("whatthehack",arrayList.toString());
            return  arrayList;
        }
    public Bitmap getBitmap(String url){

        URL urlforweb = null;
        try {
            urlforweb = new URL(url);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        try {
            Bitmap image = BitmapFactory.decodeStream(urlforweb.openConnection().getInputStream());
            return getResizedBitmap(image,300,300);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    public Bitmap getResizedBitmap(Bitmap bm, int newWidth, int newHeight) {
        int width = bm.getWidth();
        int height = bm.getHeight();
        float scaleWidth = ((float) newWidth) / width;
        float scaleHeight = ((float) newHeight) / height;
        // CREATE A MATRIX FOR THE MANIPULATION
        Matrix matrix = new Matrix();
        // RESIZE THE BIT MAP
        matrix.postScale(scaleWidth, scaleHeight);
        // "RECREATE" THE NEW BITMAP
        Bitmap resizedBitmap = Bitmap.createBitmap(
                bm, 0, 0, width, height, matrix, false);
        return resizedBitmap;
    }

}


