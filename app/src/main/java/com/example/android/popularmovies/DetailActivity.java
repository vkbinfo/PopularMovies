package com.example.android.popularmovies;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by vikashkumarbijarnia on 26/07/16.
 */
public class DetailActivity extends AppCompatActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ImageView imageView;
        TextView title =(TextView) findViewById(R.id.movie_title_in_detail);
        TextView overview =(TextView) findViewById(R.id.movie_overview_in_detail);
        TextView releaseDate =(TextView) findViewById(R.id.release_date_in_detail);
        TextView rating =(TextView) findViewById(R.id.user_rating_in_detail);
        Intent intent= getIntent();
        byte[] bytearrays= intent.getByteArrayExtra("bytearray");
        Bitmap bitmap = BitmapFactory.decodeByteArray(bytearrays , 0, bytearrays.length);
        imageView=(ImageView)findViewById(R.id.image_for_movie_poster);
        imageView.setImageBitmap(bitmap);
        title.setText("TITLE: "+intent.getStringExtra("title"));
        overview.setText("OVERVIEW: "+intent.getStringExtra("overview"));
        releaseDate.setText("RELEASE DATE: "+intent.getStringExtra("release_date"));
        rating.setText("USER RATINGS: "+intent.getStringExtra("user_rating")+"/10");
    }
}
