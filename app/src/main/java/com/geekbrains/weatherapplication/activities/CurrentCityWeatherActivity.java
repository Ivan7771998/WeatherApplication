package com.geekbrains.weatherapplication.activities;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.geekbrains.weatherapplication.R;
import com.geekbrains.weatherapplication.activities.MainActivity;
import com.geekbrains.weatherapplication.fragments.CoatOfArmsFragment;

public class CurrentCityWeatherActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current_city_wearher);
        addFragment();
    }

    private void addFragment() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        CoatOfArmsFragment coatOfArmsFragment = (CoatOfArmsFragment)
                fragmentManager.findFragmentByTag(MainActivity.TAG_FRAGMENT);
        Bundle bundle = new Bundle();
        if (coatOfArmsFragment == null) {
            coatOfArmsFragment = new CoatOfArmsFragment();
            bundle.putString(MainActivity.CURRENT_CITY, getIntent()
                    .getStringExtra(MainActivity.CURRENT_CITY));
            coatOfArmsFragment.setArguments(bundle);
            transaction.add(R.id.container, coatOfArmsFragment, MainActivity.TAG_FRAGMENT);
            transaction.commit();
        } else {
            transaction.remove(coatOfArmsFragment);
            coatOfArmsFragment = new CoatOfArmsFragment();
            bundle.putString(MainActivity.CURRENT_CITY, getIntent()
                    .getStringExtra(MainActivity.CURRENT_CITY));
            coatOfArmsFragment.setArguments(bundle);
            transaction.add(R.id.container, coatOfArmsFragment, MainActivity.TAG_FRAGMENT);
            transaction.commit();
        }
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



