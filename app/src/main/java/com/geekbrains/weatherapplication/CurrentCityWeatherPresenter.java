package com.geekbrains.weatherapplication;

import android.app.Activity;
import android.content.res.TypedArray;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.geekbrains.weatherapplication.adapters.RecyclerViewAdapterTemperature;
import com.geekbrains.weatherapplication.model.CityModelWeather;
import com.geekbrains.weatherapplication.model.HistoryDataTemperature;

import java.util.ArrayList;
import java.util.List;


public class CurrentCityWeatherPresenter implements ICurrentCityWeatherPresenter {

    private Activity activity;
    private String[] nameCityList;
    private String[] temperatureCityList;
    private String[] textOvercastList;
    private String[] textHumidityList;
    private String[] textHistoryTimeList;
    private String[] textHistoryTemperatureList;
    private String[] url;
    private TextView nameCity;
    private TextView currentTemperature;
    private TextView textOvercast;
    private TextView textHumidity;
    private ImageView imageCity;
    private TypedArray idImageList;
    private RecyclerView recyclerView;
    private List<CityModelWeather> mCityModelWeathers;

    public CurrentCityWeatherPresenter(Activity activity) {
        this.activity = activity;
        mCityModelWeathers = new ArrayList<>();
    }

    @Override
    public void initComponent(View view) {
        nameCity = view.findViewById(R.id.current_name_city);
        currentTemperature = view.findViewById(R.id.current_temperature);
        textHumidity = view.findViewById(R.id.current_text_humidity);
        textOvercast = view.findViewById(R.id.current_text_overcast);
        imageCity = view.findViewById(R.id.image_city);
        recyclerView = view.findViewById(R.id.history_temperature_recycler);
    }

    @Override
    public void initArrays() {
        nameCityList = activity.getResources().getStringArray(R.array.name_city);
        temperatureCityList = activity.getResources().getStringArray(R.array.temperature_array);
        textHumidityList = activity.getResources().getStringArray(R.array.humidity_array);
        textOvercastList = activity.getResources().getStringArray(R.array.overcast_array);
        idImageList = activity.getResources().obtainTypedArray(R.array.icons_city);
        url = activity.getResources().getStringArray(R.array.url_city_weather);
        textHistoryTimeList = activity.getResources().getStringArray(R.array.temperature_array_history_time);
        textHistoryTemperatureList = activity.getResources().getStringArray(R.array.temperature_array_history);
    }

    @Override
    public void fillingOutList() {
        for (int i = 0; i < nameCityList.length; i++) {
            mCityModelWeathers.add(new CityModelWeather(nameCityList[i], temperatureCityList[i],
                    idImageList.getDrawable(i), textOvercastList[i], textHumidityList[i], url[i]));
        }
    }

    @Override
    public void initListHistoryTemperature() {
        HistoryDataTemperature historyDataTemperature = new HistoryDataTemperature(textHistoryTimeList,
                textHistoryTemperatureList);
        LinearLayoutManager layoutManager = new LinearLayoutManager(activity);
        RecyclerViewAdapterTemperature adapter = new RecyclerViewAdapterTemperature(historyDataTemperature);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void setCurrentCity(int position) {
        nameCity.setText(mCityModelWeathers.get(position).name_city);
        currentTemperature.setText(mCityModelWeathers.get(position).temperature);
        textHumidity.setText(mCityModelWeathers.get(position).humidity);
        textOvercast.setText(mCityModelWeathers.get(position).overcast);
        imageCity.setImageDrawable(mCityModelWeathers.get(position).res_image);
    }
}
