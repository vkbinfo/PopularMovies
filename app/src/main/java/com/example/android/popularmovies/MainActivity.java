package com.example.android.popularmovies;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_activity_menu,menu);
       return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==R.id.popular_movies){
            Toast.makeText(getApplicationContext(),"popular movies selected",Toast.LENGTH_LONG).show();
        }
        if(item.getItemId()==R.id.toprated_movies){
            Toast.makeText(getApplicationContext(),"top rated selected",Toast.LENGTH_LONG).show();
        }
    return true;
    }
}
