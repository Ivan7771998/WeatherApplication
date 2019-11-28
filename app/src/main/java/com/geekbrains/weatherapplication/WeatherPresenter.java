package com.geekbrains.weatherapplication;

import android.content.Context;
import android.content.res.TypedArray;

import java.util.ArrayList;
import java.util.List;

class WeatherPresenter implements IWeatherPresenter {

    private Context context;
    private List<CityModelWeather> mCityModelWeathers;
    private String[] nameCityList;
    private String[] temperatureCityList;
    private String[] textOvercastList;
    private String[] textHumidityList;
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
    }

    @Override
    public void fillingOutList() {
        for (int i = 0; i < nameCityList.length; i++) {
            mCityModelWeathers.add(new CityModelWeather(nameCityList[i], temperatureCityList[i],
                    idImageList.getDrawable(i), textOvercastList[i], textHumidityList[i]));
        }
    }

    @Override
    public List<CityModelWeather> getCityModelWeathers() {
        return mCityModelWeathers;
    }

    @Override
    public void destroyObjects() {
        mCityModelWeathers.clear();
        nameCityList = null;
        temperatureCityList=null;
        textOvercastList=null;
        textHumidityList=null;
        idImageList=null;
    }
}

