package com.geekbrains.weatherapplication.fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.geekbrains.weatherapplication.CurrentCityWeatherPresenter;
import com.geekbrains.weatherapplication.activities.MainActivity;
import com.geekbrains.weatherapplication.R;

public class CoatOfArmsFragment extends Fragment {
    private CurrentCityWeatherPresenter currentCityWeatherPresenter;

    public static CoatOfArmsFragment create(String nameCity) {
        CoatOfArmsFragment fragment = new CoatOfArmsFragment();
        Bundle args = new Bundle();
        args.putString(MainActivity.CURRENT_CITY, nameCity);
        fragment.setArguments(args);
        return fragment;
    }

    public String getCurrentCity() {
        try {
            assert getArguments() != null;
            String currentCity = getArguments().getString(MainActivity.CURRENT_CITY);
            currentCityWeatherPresenter.setCurrentCity(currentCity);
            return currentCity;
        } catch (Exception e) {
            return "";
        }
    }

    @Override
    @SuppressLint("Recycle")
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_current_city, container, false);
        currentCityWeatherPresenter = new CurrentCityWeatherPresenter(getActivity());
        currentCityWeatherPresenter.initComponent(view);
        try {
            assert getArguments() != null;
            currentCityWeatherPresenter.setCurrentCity(getArguments().getString(MainActivity.CURRENT_CITY));
        } catch (Exception e) {
            currentCityWeatherPresenter.setCurrentCity("Moscow");
        }
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}
