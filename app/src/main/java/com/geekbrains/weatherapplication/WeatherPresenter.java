package com.geekbrains.weatherapplication;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.geekbrains.weatherapplication.activities.CurrentCityWeatherActivity;
import com.geekbrains.weatherapplication.activities.MainActivity;
import com.geekbrains.weatherapplication.adapters.RecyclerViewAdapterCity;
import com.geekbrains.weatherapplication.fragments.CoatOfArmsFragment;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

public class WeatherPresenter implements IWeatherPresenter {

    private Context context;
    private Activity activity;
    private TextView emptyTextView; //И чтобы установить пустую View тоже метода нет у Recycler
    private boolean isExistCoatOfArms;  // Можно ли расположить рядом фрагмент с гербом
    private String currentCity;    // Текущая позиция (выбранный город)
    private RecyclerView mRecyclerView;
    private RecyclerViewAdapterCity adapterCity;
    private EditText addCityText;
    private FloatingActionButton btnAddCity;
    private CoordinatorLayout coordinatorLayout;
    private String [] cities;

    public WeatherPresenter(Activity activity, Context context) {
        this.context = context;
        this.activity = activity;
    }

    @Override
    public void onActivityCreatedStart(Bundle savedInstanceState) {
        isExistCoatOfArms = context.getResources().getConfiguration().orientation
                == Configuration.ORIENTATION_LANDSCAPE;
        if (savedInstanceState != null) {
            currentCity = savedInstanceState.getString(MainActivity.CURRENT_CITY,"city");
        }
        if (isExistCoatOfArms) {
            showCoatOfArms(currentCity);
        }
    }

    @Override
    public void initViewComponent(View view) {
        mRecyclerView = view.findViewById(R.id.cities_list_view);
        emptyTextView = view.findViewById(R.id.cities_list_empty_view);
        addCityText = view.findViewById(R.id.add_new_city);
        btnAddCity = view.findViewById(R.id.id_btn_add_city);
        coordinatorLayout = view.findViewById(R.id.coordinatorLayout);
    }

    private void addNewCity() {
        btnAddCity.setOnClickListener(v -> {
            if (addCityText.getText().length() != 0&&!adapterCity.checkIsItemInData(addCityText.getText().toString())) {
                Snackbar.make(coordinatorLayout,
                                    context.getResources()
                                            .getString(R.string.btn_add_city_complete),
                        Snackbar.LENGTH_SHORT).show();
                adapterCity.addItem(addCityText.getText().toString());
            } else if (addCityText.getText().length() == 0){
                Snackbar snackbar = Snackbar
                        .make(coordinatorLayout,
                                context.getResources()
                                        .getString(R.string.btn_add_city_no_text),
                                Snackbar.LENGTH_LONG);
                snackbar.show();
            }else{
                Snackbar snackbar = Snackbar
                        .make(coordinatorLayout,
                                context.getResources()
                                        .getString(R.string.btn_add_city_repeat_city),
                                Snackbar.LENGTH_LONG);
                snackbar.show();
            }
        });
    }

    @Override
    public void initList() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(activity.getBaseContext());
        mRecyclerView.setLayoutManager(layoutManager);
        cities=activity.getResources().getStringArray(R.array.name_city);
        adapterCity = new RecyclerViewAdapterCity(new ArrayList<>(Arrays.asList(cities)), currentCity -> {
            showCoatOfArms(currentCity);
        });
        mRecyclerView.setAdapter(adapterCity);
        addNewCity();
    }

    private void showCoatOfArms(String currentCity) {
        MainActivity mainActivity = (MainActivity) activity;
        if (isExistCoatOfArms) {
            //listView.setItemChecked(currentPosition, true); метода у RecyclerView такого нет( не стал заморачиваться с выделением
            CoatOfArmsFragment detail = (CoatOfArmsFragment)
                    Objects.requireNonNull(mainActivity).getSupportFragmentManager()
                            .findFragmentByTag(MainActivity.TAG_FRAGMENT);
            Bundle bundle = new Bundle();
            bundle.putString(MainActivity.CURRENT_CITY,currentCity );
            if (detail == null) {
                detail = CoatOfArmsFragment.create(currentCity);
                detail.setArguments(bundle);
                FragmentTransaction ft = mainActivity.getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.container, detail);
                ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                ft.commit();
            }
        } else {
            Intent intent = new Intent();
            intent.setClass(Objects.requireNonNull(mainActivity), CurrentCityWeatherActivity.class);
            intent.putExtra(MainActivity.CURRENT_CITY, addCityText.getText());
            mainActivity.startActivity(intent);
        }
    }
}

