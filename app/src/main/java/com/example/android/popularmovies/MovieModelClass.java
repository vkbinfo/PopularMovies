package com.example.android.popularmovies;

/**
 * Created by vikashkumarbijarnia on 24/07/16.
 */
public class MovieModelClass {
    private String title;
    private String imageThumbnailUrl;
    private String overviewOfMovie;
    private String userRating;
    private String releaseDate;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImageThumbnailUrl() {
        return imageThumbnailUrl;
    }

    public void setImageThumbnailUrl(String imageThumbnailUrl) {
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
