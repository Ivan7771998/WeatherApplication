package com.geekbrains.weatherapplication;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.geekbrains.weatherapplication.adapters.RecyclerViewAdapterCity;
import com.geekbrains.weatherapplication.fragments.CoatOfArmsFragment;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

public class WeatherPresenter implements IWeatherPresenter {

    private Context context;
    private Activity activity;
    private TextView emptyTextView; //И чтобы установить пустую View тоже метода нет у Recycler
    private boolean isExistCoatOfArms;  // Можно ли расположить рядом фрагмент с гербом
    private int currentPosition = 0;    // Текущая позиция (выбранный город)
    private RecyclerView mRecyclerView;
    private RecyclerViewAdapterCity adapterCity;
    private EditText addCityText;
    private Button btnAddCity;

    public WeatherPresenter(Activity activity, Context context) {
        this.context = context;
        this.activity = activity;
    }

    @Override
    public void onActivityCreatedStart(Bundle savedInstanceState) {
        isExistCoatOfArms = context.getResources().getConfiguration().orientation
                == Configuration.ORIENTATION_LANDSCAPE;
        if (savedInstanceState != null) {
            currentPosition = savedInstanceState.getInt(MainActivity.INDEX_ITEM, 0);
        }
        if (isExistCoatOfArms) {
            //listView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
            showCoatOfArms();
        }
    }

    @Override
    public void initViewComponent(View view) {
        mRecyclerView = view.findViewById(R.id.cities_list_view);
        emptyTextView = view.findViewById(R.id.cities_list_empty_view);
        addCityText = view.findViewById(R.id.add_new_city);
        btnAddCity = view.findViewById(R.id.id_btn_add_city);
    }

    private void addNewCity() {
        btnAddCity.setOnClickListener(v -> {
            if (addCityText.getText().length() != 0) {
                adapterCity.addItem(addCityText.getText().toString());
            }
        });
    }

    @Override
    public void initList() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(activity.getBaseContext());
        mRecyclerView.setLayoutManager(layoutManager);

        adapterCity = new RecyclerViewAdapterCity(new ArrayList<>(Arrays.asList(activity
                .getResources().getStringArray(R.array.name_city))), position -> {
            currentPosition = position;
            showCoatOfArms();
        });
        mRecyclerView.setAdapter(adapterCity);
        addNewCity();
    }

    private void showCoatOfArms() {
        MainActivity mainActivity = (MainActivity) activity;
        if (isExistCoatOfArms) {
            //listView.setItemChecked(currentPosition, true); метода у RecyclerView такого нет( не стал заморачиваться с выделением
            CoatOfArmsFragment detail = (CoatOfArmsFragment)
                    Objects.requireNonNull(mainActivity).getSupportFragmentManager()
                            .findFragmentByTag(MainActivity.TAG_FRAGMENT);
            Bundle bundle = new Bundle();
            bundle.putInt(MainActivity.INDEX_ITEM, currentPosition);
            if (detail == null || detail.getIndex() != currentPosition) {
                detail = CoatOfArmsFragment.create(getCurrentPosition());
                detail.setArguments(bundle);
                FragmentTransaction ft = mainActivity.getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.container, detail);
                ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                ft.commit();
            }
        } else {
            Intent intent = new Intent();
            intent.setClass(Objects.requireNonNull(mainActivity), CurrentCityWeatherActivity.class);
            intent.putExtra(MainActivity.INDEX_ITEM, getCurrentPosition());
            mainActivity.startActivity(intent);
        }
    }

    public int getCurrentPosition() {
        return currentPosition;
    }
}

