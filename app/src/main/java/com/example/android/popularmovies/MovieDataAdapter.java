package com.example.android.popularmovies;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.lang.annotation.Target;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by vikashkumarbijarnia on 24/07/16.
 */
public class MovieDataAdapter extends ArrayAdapter<MovieModelClass> {

    public MovieDataAdapter(Context context,ArrayList<MovieModelClass> objects) {
        super(context, 0, objects);
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        MovieModelClass movieData =  getItem(position);
        View v;
        // Adapters recycle views to AdapterViews.
        // If this is a new View object we're getting, then inflate the layout.
        // If not, this view already has the layout inflated from a previous call to getView,
        // and we modify the View widgets as usual.
        if (convertView == null) {
            v = LayoutInflater.from(getContext()).inflate(R.layout.grid_item_view, parent, false);
        }
        else{
          v=(View)convertView;
        }


        final ImageView image = (ImageView) v.findViewById(R.id.imageView_in_gridview);


        Log.v("bbbbb",movieData.getImageThumbnailUrl().toString());
        Picasso.with(getContext())
                .load("https://image.tmdb.org/t/p/original"+movieData.getImageThumbnailUrl())
                .into(new com.squareup.picasso.Target() {
                    @Override
                    public void onBitmapLoaded(final Bitmap bitmap, Picasso.LoadedFrom from) {
                /* Save the bitmap or do something with it here */

                        //Set it in the ImageView
                        image.setImageBitmap(bitmap);
                    }

                    @Override
                    public void onBitmapFailed(Drawable errorDrawable) {

                    }

                    @Override
                    public void onPrepareLoad(Drawable placeHolderDrawable) {

                    }

                });
                    return v;


    }

}
