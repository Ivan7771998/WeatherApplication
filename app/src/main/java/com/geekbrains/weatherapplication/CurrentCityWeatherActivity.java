package com.geekbrains.weatherapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Parcelable;
import android.provider.ContactsContract;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.geekbrains.weatherapplication.model.CityModelWeather;

import java.io.Serializable;

public class CurrentCityWeatherActivity extends AppCompatActivity {

    private TextView nameCity;
    private TextView currentTemperature;
    private TextView textOvercast;
    private TextView textHumidity;
    private Button btnBack;
    private Button btnMore;
    private CityModelWeather cityModelWeather;
    private CurrentCityWeatherPresenter currentCityWeatherPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current_city_wearher);
        currentCityWeatherPresenter = new CurrentCityWeatherPresenter(this);
        initComponent();
        getDataFromFirstActivity();
        btnBackOnClick();
        btnMoreonClick();
    }

    private void initComponent() {
        currentCityWeatherPresenter = new CurrentCityWeatherPresenter(this);
        nameCity = findViewById(R.id.current_name_city);
        currentTemperature = findViewById(R.id.current_temperature);
        textHumidity = findViewById(R.id.current_text_humidity);
        textOvercast = findViewById(R.id.current_text_overcast);
        btnBack = findViewById(R.id.btn_back);
        btnMore = findViewById(R.id.btn_internet);
    }

    private void getDataFromFirstActivity() {
        cityModelWeather = getIntent().getParcelableExtra(MainActivity.KEY_TO_DATA);
        assert cityModelWeather != null;
        nameCity.setText(cityModelWeather.name_city);
        currentTemperature.setText(cityModelWeather.temperature);
        textHumidity.setText(cityModelWeather.humidity);
        textOvercast.setText(cityModelWeather.overcast);
    }

    void btnBackOnClick() {
        btnBack.setOnClickListener(v -> {
            currentCityWeatherPresenter.gobBtnBack();
        });
    }

    void btnMoreonClick() {
        btnMore.setOnClickListener(v -> {
            currentCityWeatherPresenter.gobBtnWebsite(cityModelWeather.url);
        });
    }

}



