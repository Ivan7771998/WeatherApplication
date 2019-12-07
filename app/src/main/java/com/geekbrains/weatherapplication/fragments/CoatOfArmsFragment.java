package com.geekbrains.weatherapplication.fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.geekbrains.weatherapplication.CurrentCityWeatherPresenter;
import com.geekbrains.weatherapplication.MainActivity;
import com.geekbrains.weatherapplication.R;

public class CoatOfArmsFragment extends Fragment {
    CurrentCityWeatherPresenter currentCityWeatherPresenter;

    public static CoatOfArmsFragment create(int position) {
        CoatOfArmsFragment fragment = new CoatOfArmsFragment();    // создание
        Bundle args = new Bundle();
        args.putInt("CurrentCity", position);
        fragment.setArguments(args);
        return fragment;
    }

    public int getIndex() {
        try {
            int index = getArguments().getInt("CurrentCity");
            Log.d("INDEX  ", "" + index);
            currentCityWeatherPresenter.setCurrentCity(index);
            return index;
        } catch (Exception e) {
            return 0;
        }
    }

    @Override
    @SuppressLint("Recycle")
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_current_city, container, false);
        currentCityWeatherPresenter = new CurrentCityWeatherPresenter(getActivity());
        currentCityWeatherPresenter.initComponent(view);
        currentCityWeatherPresenter.initArrays();
        currentCityWeatherPresenter.fillingOutList();
        try {
            currentCityWeatherPresenter.setCurrentCity(getArguments().getInt("index"));
        } catch (Exception e) {
            currentCityWeatherPresenter.setCurrentCity(0);
        }
        return view;   // Вместо макета используем сразу картинку
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}
