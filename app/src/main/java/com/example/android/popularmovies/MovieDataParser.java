package com.example.android.popularmovies;

import android.app.Activity;
import android.text.format.Time;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 * Created by vikashkumarbijarnia on 24/07/16.
 */
public class MovieDataParser {


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
                tempObject.setImageThumbnailUrl(singleMovie.getString("poster_path"));
                arrayList.add(tempObject);
            }
            Log.v("whatthehack",arrayList.toString());
            return  arrayList;
        }
    }


