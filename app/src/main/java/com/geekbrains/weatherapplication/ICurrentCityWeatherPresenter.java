package com.geekbrains.weatherapplication;

import android.view.View;

import com.geekbrains.weatherapplication.model.CityModelWeather;

import java.util.List;

public interface ICurrentCityWeatherPresenter {

    void setCurrentCity(String position);

    void initComponent(View view);

}
