package com.geekbrains.weatherapplication;

import java.util.List;

interface IWeatherPresenter {

    void initArrays();

    void fillingOutList();

    List<CityModelWeather> getCityModelWeathers();

    void destroyObjects();
}
