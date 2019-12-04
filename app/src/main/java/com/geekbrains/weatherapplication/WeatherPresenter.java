package com.geekbrains.weatherapplication;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;

import com.geekbrains.weatherapplication.model.CityModelWeather;

import java.util.ArrayList;
import java.util.List;

class WeatherPresenter implements IWeatherPresenter {

    private Context context;
    private List<CityModelWeather> mCityModelWeathers;
    private String[] nameCityList;
    private String[] temperatureCityList;
    private String[] textOvercastList;
    private String[] textHumidityList;
    private String[] url;
    private TypedArray idImageList;

    WeatherPresenter(Context context) {
        this.context = context;
        mCityModelWeathers = new ArrayList<>();
    }

    @Override
    public void initArrays() {
        nameCityList = context.getResources().getStringArray(R.array.name_city);
        temperatureCityList = context.getResources().getStringArray(R.array.temperature_array);
        textHumidityList = context.getResources().getStringArray(R.array.humidity_array);
        textOvercastList = context.getResources().getStringArray(R.array.overcast_array);
        idImageList = context.getResources().obtainTypedArray(R.array.icons_city);
        url = context.getResources().getStringArray(R.array.url_city_weather);
    }

    @Override
    public void fillingOutList() {
        for (int i = 0; i < nameCityList.length; i++) {
            mCityModelWeathers.add(new CityModelWeather(nameCityList[i], temperatureCityList[i],
                    idImageList.getDrawable(i), textOvercastList[i], textHumidityList[i], url[i]));
        }
    }

    @Override
    public List<CityModelWeather> getCityModelWeathers() {
        return mCityModelWeathers;
    }

    @Override
    public void goToCurrentCityWeather(int indexCity, Activity activity) {
        Intent intent = new Intent(context, CurrentCityWeatherActivity.class);
        intent.putExtra(MainActivity.KEY_TO_DATA, mCityModelWeathers.get(indexCity));
        activity.startActivityForResult(intent, 1);
    }

}

