package com.geekbrains.weatherapplication.model;

public class HistoryDataTemperature {
    public String[] list_time_history;
    public String[] list_temperature_history;

    public HistoryDataTemperature(String[] list_time_history, String[] list_temperature_history) {
        this.list_temperature_history = list_temperature_history;
        this.list_time_history = list_time_history;
    }
}
