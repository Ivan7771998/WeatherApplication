package com.geekbrains.weatherapplication;

import android.os.Bundle;
import android.view.View;

interface IWeatherPresenter {

    void initViewComponent(View view);

    void initList();

    void onActivityCreatedStart(Bundle savedInstanceState);
}
