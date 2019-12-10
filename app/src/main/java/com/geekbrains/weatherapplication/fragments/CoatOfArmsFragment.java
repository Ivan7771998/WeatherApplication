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
import com.geekbrains.weatherapplication.MainActivity;
import com.geekbrains.weatherapplication.R;

public class CoatOfArmsFragment extends Fragment {
    private CurrentCityWeatherPresenter currentCityWeatherPresenter;

    public static CoatOfArmsFragment create(int position) {
        CoatOfArmsFragment fragment = new CoatOfArmsFragment();
        Bundle args = new Bundle();
        args.putInt(MainActivity.INDEX_ITEM, position);
        fragment.setArguments(args);
        return fragment;
    }

    public int getIndex() {
        try {
            assert getArguments() != null;
            int index = getArguments().getInt(MainActivity.INDEX_ITEM);
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
        currentCityWeatherPresenter.initListHistoryTemperature();
        currentCityWeatherPresenter.fillingOutList();
        try {
            assert getArguments() != null;
            currentCityWeatherPresenter.setCurrentCity(getArguments().getInt(MainActivity.INDEX_ITEM));
        } catch (Exception e) {
            currentCityWeatherPresenter.setCurrentCity(0);
        }
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}
