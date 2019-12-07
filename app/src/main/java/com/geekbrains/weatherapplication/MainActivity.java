package com.geekbrains.weatherapplication;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.geekbrains.weatherapplication.model.CityModelWeather;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private TextView nameCity;
    private ImageView coatOfArms;
    private Spinner selectCity;
    private Button btnShowWeather;
    private List<CityModelWeather> mCityModelWeathers;
    private WeatherPresenter weatherPresenter;
    final static String KEY_TO_DATA = "KEY_TO_DATA";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initComponent();
        itemSpinnerSelected();
    }

    private void initComponent() {
        weatherPresenter = new WeatherPresenter(this);
        weatherPresenter.initArrays();
        weatherPresenter.fillingOutList();
        mCityModelWeathers = weatherPresenter.getCityModelWeathers();
        nameCity = findViewById(R.id.name_city);
        selectCity = findViewById(R.id.spinner);
        coatOfArms = findViewById(R.id.image_city);
        btnShowWeather = findViewById(R.id.btn_weather);
        btnClick();
    }

    private void btnClick() {
        btnShowWeather.setOnClickListener(v -> {
            weatherPresenter.goToCurrentCityWeather(selectCity.getSelectedItemPosition(), this);
        });
    }

    private void itemSpinnerSelected() {
        selectCity.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                nameCity.setText(mCityModelWeathers.get(i).name_city);
                coatOfArms.setImageDrawable(mCityModelWeathers.get(i).res_image);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }
}
