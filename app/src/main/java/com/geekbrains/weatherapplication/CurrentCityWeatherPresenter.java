package com.geekbrains.weatherapplication;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;

import static android.app.Activity.RESULT_OK;

class CurrentCityWeatherPresenter implements ICurrentCityWeatherPresenter {

    private Activity activity;

    CurrentCityWeatherPresenter(Activity activity) {
        this.activity = activity;

    }

    @Override
    public void gobBtnBack() {
        Intent intent = new Intent();
        activity.setResult(RESULT_OK, intent);
        activity.finish();
    }

    @Override
    public void gobBtnWebsite(String url) {
        Uri uri = Uri.parse(url);
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        activity.startActivity(intent);
    }
}
