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
import java.util.ArrayList;

/**
 * Created by vikashkumarbijarnia on 24/07/16.
 */
public class MovieDataAdapter extends ArrayAdapter<MovieModelClass> {

    public MovieDataAdapter(Context context,ArrayList<MovieModelClass> objects) {
        super(context, 0, objects);
    }

    public class ViewHolder{
        public ImageView image;
        ViewHolder(View V){
            image=(ImageView) V.findViewById(R.id.imageView_in_gridview);
        }
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        MovieModelClass movieData =  getItem(position);
        View v=convertView;
        ViewHolder viewHolder=null;
        // Adapters recycle views to AdapterViews.
        // If this is a new View object we're getting, then inflate the layout.
        // If not, this view already has the layout inflated from a previous call to getView,
        // and we modify the View widgets as usual.
        if (v == null) {
            v = LayoutInflater.from(getContext()).inflate(R.layout.grid_item_view, parent, false);
             viewHolder=new ViewHolder(v);
            v.setTag(viewHolder);
        }
        else{
         viewHolder=(ViewHolder) v.getTag();
        }

        final ImageView imageView=viewHolder.image;
             imageView.setImageBitmap(movieData.getImageThumbnailUrl());
        return v;
    }
}
