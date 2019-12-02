package com.geekbrains.weatherapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.res.TypedArray;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private TextView nameCity;
    private TextView currentTemperature;
    private TextView textOvercast;
    private TextView textHumidity;
    private ImageView coatOfArms;
    private Spinner selectCity;
    private CheckBox mCheckBoxOvercast;
    private CheckBox mCheckBoxHumidity;
    private List<CityModelWeather> mCityModelWeathers;
    private final String TAG = "States";
    private final String CHECKBOX_HUMIDITY = "humidity";
    private final String CHECKBOX_OVERCAST = "overcast";
    private WeatherPresenter weatherPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initComponent();
        itemSpinnerSelected();
        itemCheckBoxStatus();
    }

    private void initComponent() {
        weatherPresenter = new WeatherPresenter(this);
        weatherPresenter.initArrays();
        weatherPresenter.fillingOutList();
        mCityModelWeathers = weatherPresenter.getCityModelWeathers();
        nameCity = findViewById(R.id.name_city);
        currentTemperature = findViewById(R.id.temperature);
        coatOfArms = findViewById(R.id.coat_of_arms);
        selectCity = findViewById(R.id.spinner);
        textHumidity = findViewById(R.id.text_humidity);
        textOvercast = findViewById(R.id.text_overcast);
        mCheckBoxHumidity = findViewById(R.id.checkBox_humidity);
        mCheckBoxOvercast = findViewById(R.id.checkBox_overcast);
    }


    private void itemSpinnerSelected() {
        selectCity.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                nameCity.setText(mCityModelWeathers.get(i).name_city);
                currentTemperature.setText(mCityModelWeathers.get(i).temperature);
                coatOfArms.setImageDrawable(mCityModelWeathers.get(i).res_image);
                textOvercast.setText(mCityModelWeathers.get(i).overcast);
                textHumidity.setText(mCityModelWeathers.get(i).humidity);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
    }

    private void itemCheckBoxStatus() {
        mCheckBoxOvercast.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked) {
                    textOvercast.setVisibility(View.VISIBLE);
                } else {
                    textOvercast.setVisibility(View.INVISIBLE);
                }
            }
        });

        mCheckBoxHumidity.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked) {
                    textHumidity.setVisibility(View.VISIBLE);
                } else {
                    textHumidity.setVisibility(View.INVISIBLE);
                }
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "MainActivity: onStart()");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "MainActivity: onResume()");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "MainActivity: onPause()");
    }

    @Override
    protected void onStop() {
        weatherPresenter.destroyObjects();
        super.onStop();
        Log.d(TAG, "MainActivity: onStop()");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "MainActivity: onDestroy()");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG, "MainActivity: onRestart()");
    }

    public void onBackPressed() {
        super.onBackPressed();
        Log.d(TAG, "MainActivity: onBackPressed()");
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putBoolean(CHECKBOX_HUMIDITY, mCheckBoxHumidity.isChecked());
        outState.putBoolean(CHECKBOX_OVERCAST, mCheckBoxOvercast.isChecked());
        super.onSaveInstanceState(outState);
        Log.d(TAG, "MainActivity: onSaveInstanceState()");
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        mCheckBoxOvercast.setChecked(savedInstanceState.getBoolean(CHECKBOX_OVERCAST));
        mCheckBoxHumidity.setChecked(savedInstanceState.getBoolean(CHECKBOX_HUMIDITY));
        super.onRestoreInstanceState(savedInstanceState);
        Log.d(TAG, "MainActivity: onRestoreInstanceState()");
    }
}
