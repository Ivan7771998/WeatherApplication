package com.geekbrains.weatherapplication;

import android.graphics.drawable.Drawable;

public class CityModelWeather {

    private String name_city;
    private String temperature;
    private Drawable res_image;


    public CityModelWeather(String name_city, String temperature, Drawable res_image) {
        this.name_city = name_city;
        this.temperature = temperature;
        this.res_image = res_image;
    }

    public String getName_city() {
        return name_city;
    }

    public void setName_city(String name_city) {
        this.name_city = name_city;
    }

    public String getTemperature() {
        return temperature+" \u00B0C";
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public Drawable getRes_image() {
        return res_image;
    }

    public void setRes_image(Drawable res_image) {
        this.res_image = res_image;
    }
}
