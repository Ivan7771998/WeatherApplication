package com.geekbrains.weatherapplication;

import android.app.Activity;
import android.content.res.TypedArray;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.geekbrains.weatherapplication.activities.MainActivity;
import com.geekbrains.weatherapplication.model.CityModelWeather;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class CurrentCityWeatherPresenter implements ICurrentCityWeatherPresenter {

    private Activity activity;
    private TextView nameCity;
    private TextView todayDate;
    private TextView currentTemperature;
    private TextView textOvercast;
    private TextView textHumidity;
    private final Handler handler = new Handler();
    public CurrentCityWeatherPresenter(Activity activity) {
        this.activity = activity;
    }

    @Override
    public void initComponent(View view) {
        nameCity = view.findViewById(R.id.current_name_city);
        todayDate=view.findViewById(R.id.todayDate);
        currentTemperature = view.findViewById(R.id.current_temperature_title);
        textHumidity = view.findViewById(R.id.current_title_humidity);
        textOvercast = view.findViewById(R.id.current_text_overcast);
    }

    @Override
    public void setCurrentCity(String city) {
       updateWeatherData(city);
    }



    private void updateWeatherData(final String city) {
        new Thread() {
            public void run() {
                final JSONObject json = WeatherDataLoader.getJSONData(Objects.requireNonNull(activity), city);
                if (json == null) {
                    handler.post(() -> Toast.makeText(activity, activity.getString(R.string.city_not_found),
                            Toast.LENGTH_LONG).show());
                }
                else handler.post(() -> renderWeather(json));
            }
        }.start();
    }

    private void renderWeather(JSONObject json) {
        Log.d("JSON", "json " + json.toString());
        try {
            nameCity.setText(json.getString("name"));
            JSONObject details = json.getJSONArray("weather").getJSONObject(0);
            JSONObject main = json.getJSONObject("main");
            currentTemperature.setText(String.valueOf(main.getDouble("temp")));
            textHumidity.setText(String.valueOf(main.getInt("humidity")));
            textOvercast.setText(details.getString("main"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
