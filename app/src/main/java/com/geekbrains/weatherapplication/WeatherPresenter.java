package com.geekbrains.weatherapplication;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.fragment.app.FragmentTransaction;

import com.geekbrains.weatherapplication.fragments.CoatOfArmsFragment;

import java.util.Objects;

public class WeatherPresenter implements IWeatherPresenter {

    private ListView listView;
    private Context context;
    private Activity activity;
    private TextView emptyTextView;
    private boolean isExistCoatOfArms;  // Можно ли расположить рядом фрагмент с гербом
    private int currentPosition = 0;    // Текущая позиция (выбранный город)

    public WeatherPresenter(Activity activity, Context context, ListView listView, TextView emptyTextView) {
        this.listView = listView;
        this.context = context;
        this.activity = activity;
        this.emptyTextView = emptyTextView;
    }

    @Override
    public void onActivityCreatedStart(Bundle savedInstanceState) {
        isExistCoatOfArms = context.getResources().getConfiguration().orientation
                == Configuration.ORIENTATION_LANDSCAPE;
        if (savedInstanceState != null) {
            currentPosition = savedInstanceState.getInt(MainActivity.INDEX_ITEM, 0);
        }
        if (isExistCoatOfArms) {
            listView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
            showCoatOfArms();
        }
    }

    @Override
    public void initList() {
        ArrayAdapter adapter =
                ArrayAdapter.createFromResource(Objects.requireNonNull(context), R.array.name_city,
                        android.R.layout.simple_list_item_activated_1);
        listView.setAdapter(adapter);
        listView.setEmptyView(emptyTextView);
        listView.setOnItemClickListener((parent, view, position, id) -> {
            currentPosition = position;
            showCoatOfArms();
        });
    }

    private void showCoatOfArms() {
        MainActivity mainActivity = (MainActivity) activity;
        if (isExistCoatOfArms) {
            listView.setItemChecked(currentPosition, true);
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

