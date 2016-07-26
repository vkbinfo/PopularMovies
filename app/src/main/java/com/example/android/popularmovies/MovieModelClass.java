package com.example.android.popularmovies;

import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

/**
 * Created by vikashkumarbijarnia on 24/07/16.
 */
public class MovieModelClass{
    private String title;
    private Bitmap imageThumbnailUrl;
    private String overviewOfMovie;
    private String userRating;
    private String releaseDate;


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Bitmap getImageThumbnailUrl() {
        return imageThumbnailUrl;
    }

    public void setImageThumbnailUrl(Bitmap imageThumbnailUrl) {
        this.imageThumbnailUrl = imageThumbnailUrl;
    }

    public String getOverviewOfMovie() {
        return overviewOfMovie;
    }

    public void setOverviewOfMovie(String overviewOfMovie) {
        this.overviewOfMovie = overviewOfMovie;
    }

    public String getUserRating() {
        return userRating;
    }

    public void setUserRating(String userRating) {
        this.userRating = userRating;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }
}
