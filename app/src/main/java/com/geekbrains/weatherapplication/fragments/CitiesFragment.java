package com.geekbrains.weatherapplication.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.geekbrains.weatherapplication.activities.MainActivity;
import com.geekbrains.weatherapplication.R;
import com.geekbrains.weatherapplication.WeatherPresenter;


public class CitiesFragment extends Fragment {
    private WeatherPresenter weatherPresenter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_list_cities, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initViews(view);
        weatherPresenter.initList();
    }

    private void initViews(View view) {
        weatherPresenter = new WeatherPresenter(getActivity(), getContext());
        weatherPresenter.initViewComponent(view);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        weatherPresenter.onActivityCreatedStart(savedInstanceState);
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putInt(MainActivity.INDEX_ITEM, weatherPresenter.getCurrentPosition());
        super.onSaveInstanceState(outState);
    }
}

