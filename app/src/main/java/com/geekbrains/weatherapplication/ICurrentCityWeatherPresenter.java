package com.geekbrains.weatherapplication;

import android.view.View;

import com.geekbrains.weatherapplication.model.CityModelWeather;

import java.util.List;

public interface ICurrentCityWeatherPresenter {

    void initArrays();

    void setCurrentCity(int position);

    void fillingOutList();

    void initComponent(View view);

    void initListHistoryTemperature();
}
