package com.geekbrains.weatherapplication.activities;


import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.geekbrains.weatherapplication.R;
import com.geekbrains.weatherapplication.fragments.CitiesFragment;

public class MainActivity extends AppCompatActivity {

    public static final String CURRENT_CITY = "city";
    public static final String TAG_FRAGMENT = "frag";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        createCitiesFragment();
    }

    private void createCitiesFragment() {
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.container, new CitiesFragment())
                .addToBackStack(null)
                .commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_resource, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.about_developer) {
            startActivity(new Intent(MainActivity.this,AboutDeveloperActivity.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        int countOfFragmentInManager = getSupportFragmentManager().getBackStackEntryCount();
        if (countOfFragmentInManager > 0) {
            getSupportFragmentManager().popBackStack();
        }
    }
}
