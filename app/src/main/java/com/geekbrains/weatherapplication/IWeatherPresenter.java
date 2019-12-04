package com.geekbrains.weatherapplication;


import android.app.Activity;

import com.geekbrains.weatherapplication.model.CityModelWeather;

import java.util.List;

interface IWeatherPresenter {

    void initArrays();

    void fillingOutList();

    List<CityModelWeather> getCityModelWeathers();

    void goToCurrentCityWeather(int indexCity, Activity activity);

}
