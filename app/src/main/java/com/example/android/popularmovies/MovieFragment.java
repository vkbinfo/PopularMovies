package com.example.android.popularmovies;

import android.app.Fragment;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridLayout;
import android.widget.GridView;
import android.widget.ProgressBar;
import android.widget.Toast;

import org.json.JSONException;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by vikashkumarbijarnia on 24/07/16.
 */
public class MovieFragment extends Fragment {
    MovieDataAdapter mMovieAdapter;
    MovieDataParser dataParser;
    GridView gridView;
    ProgressBar progressBar;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        update("popularity.desc");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.movie_fragment_view,container,false);
        gridView=(GridView) view.findViewById(R.id.grid_layout);
        dataParser=new MovieDataParser(getActivity());
        progressBar=(ProgressBar)view.findViewById(R.id.progressBar);
        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.main_activity_menu,menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==R.id.popular_movies){
            progressBar.setVisibility(View.VISIBLE);
            gridView.setVisibility(View.GONE);
            update("popularity.desc");
        }
        if(item.getItemId()==R.id.toprated_movies){
            progressBar.setVisibility(View.VISIBLE);
            gridView.setVisibility(View.INVISIBLE);
            update("vote_average.desc");
        }
        return true;
    }

    public void update(String para){
        FetchMovieData wetherInfo=new FetchMovieData();
        wetherInfo.execute("https://api.themoviedb.org/3/discover/movie?api_key=61841776ff5020cff6cbc0587cb78f78&sort_by="+para);
    }

    @Override
    public void onStart() {
        super.onStart();
        setHasOptionsMenu(true);

    }

    public class FetchMovieData extends AsyncTask<String,Void,ArrayList > {

        @Override
        protected ArrayList<MovieModelClass> doInBackground(String... strings) {

            return getMovieData(strings[0]);
        }

        @Override
        protected void onPostExecute(final ArrayList arrayList) {
            progressBar.setVisibility(View.GONE);
            gridView.setVisibility(View.VISIBLE);
            if(mMovieAdapter!=null){
                mMovieAdapter.clear();
            }
            mMovieAdapter=new MovieDataAdapter(getActivity(),arrayList);
            gridView.setAdapter(mMovieAdapter);
            gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    MovieModelClass data=(MovieModelClass) arrayList.get(i);
                    Intent intent=new Intent(getActivity(),DetailActivity.class);
                    intent.putExtra("title", data.getTitle());
                    ByteArrayOutputStream stream = new ByteArrayOutputStream();
                    data.getImageThumbnailUrl().compress(Bitmap.CompressFormat.PNG, 100, stream);
                    byte[] byteArray = stream.toByteArray();
                    intent.putExtra("bytearray",byteArray);
                    intent.putExtra("overview",data.getOverviewOfMovie());
                    intent.putExtra("release_date",data.getReleaseDate());
                    intent.putExtra("user_rating",data.getUserRating());
                    startActivity(intent);
                }
            });
        }
    }
    private ArrayList<MovieModelClass> getMovieData(String urls) {
        // These two need to be declared outside the try/catch
// so that they can be closed in the finally block.
        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;

        // Will contain the raw JSON response as a string.
        String MovieJsonStr = null;

        try {
            URL url = new URL(urls);
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();
            // Read the input stream into a String
            InputStream inputStream = urlConnection.getInputStream();
            StringBuffer buffer = new StringBuffer();
            if (inputStream == null) {
                // Nothing to do.
                MovieJsonStr = null;
            }
            reader = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            while ((line = reader.readLine()) != null) {
                // Since it's JSON, adding a newline isn't necessary (it won't affect parsing)
                // But it does make debugging a *lot* easier if you print out the completed
                // buffer for debugging.
                buffer.append(line + "\n");
            }
            if (buffer.length() == 0) {
                // Stream was empty.  No point in parsing.
                MovieJsonStr = null;
            }
            MovieJsonStr = buffer.toString();
            return dataParser.getWeatherDataFromJson(MovieJsonStr);
        } catch (IOException e) {
            Log.e("PlaceholderFragment", "Error ", e);
            // If the code didn't successfully get the weather data, there's no point in attempting
            // to parse it.
            MovieJsonStr = null;
        } catch (JSONException e) {
            e.printStackTrace();
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (reader != null) {
                try {
                    reader.close();
                } catch (final IOException e) {
                    Log.e("PlaceholderFragment", "Error closing stream", e);
                }
            }
        }
        return null;
    }
}





