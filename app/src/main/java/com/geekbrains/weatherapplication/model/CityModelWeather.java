package com.geekbrains.weatherapplication.model;

import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.os.Parcelable;

public class CityModelWeather implements Parcelable {

    public String name_city;
    public String temperature;
    public String overcast;
    public String humidity;
    public Drawable res_image;
    private String url;

    public CityModelWeather(String name_city, String temperature, Drawable res_image,
                            String overcast, String humidity, String url) {
        this.name_city = name_city;
        this.temperature = temperature + " \u00B0C";
        this.res_image = res_image;
        this.overcast = overcast;
        this.humidity = humidity;
        this.url = url;
    }

    private CityModelWeather(Parcel in) {
        name_city = in.readString();
        temperature = in.readString();
        overcast = in.readString();
        humidity = in.readString();
        url = in.readString();
    }

    public static final Creator<CityModelWeather> CREATOR = new Creator<CityModelWeather>() {
        @Override
        public CityModelWeather createFromParcel(Parcel in) {
            return new CityModelWeather(in);
        }

        @Override
        public CityModelWeather[] newArray(int size) {
            return new CityModelWeather[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name_city);
        dest.writeString(temperature);
        dest.writeString(overcast);
        dest.writeString(humidity);
        dest.writeString(url);
    }
}
