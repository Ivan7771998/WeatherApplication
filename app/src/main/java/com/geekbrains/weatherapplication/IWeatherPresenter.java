package com.geekbrains.weatherapplication;

import android.os.Bundle;

interface IWeatherPresenter {

    void initList();

    int getCurrentPosition();

    void onActivityCreatedStart(Bundle savedInstanceState);
}
