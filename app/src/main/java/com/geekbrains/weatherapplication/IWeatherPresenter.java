package com.geekbrains.weatherapplication;


import android.app.Activity;
import android.os.Bundle;

import com.geekbrains.weatherapplication.model.CityModelWeather;

import java.util.List;

interface IWeatherPresenter {

    void initList();

    int getCurrentPosition();

    void onActivityCreatedStart(Bundle savedInstanceState);
}
