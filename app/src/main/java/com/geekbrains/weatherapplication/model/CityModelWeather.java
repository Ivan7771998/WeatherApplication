package com.geekbrains.weatherapplication;

import android.graphics.drawable.Drawable;

import java.io.Serializable;

class CityModelWeather implements Serializable {

    String name_city;
    String temperature;
    String overcast;
    String humidity;
    Drawable res_image;

    CityModelWeather(String name_city, String temperature, Drawable res_image,
                     String overcast, String humidity) {
        this.name_city = name_city;
        this.temperature = temperature + " \u00B0C";
        this.res_image = res_image;
        this.overcast = overcast;
        this.humidity = humidity;
    }
}
