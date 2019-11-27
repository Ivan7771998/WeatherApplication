package com.geekbrains.weatherapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.TypedArray;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private TextView name_city;
    private TextView current_temperature;
    private ImageView coat_of_arms;
    private Spinner select_city;
    private String [] name_city_list;
    private String [] temperature_city_list;
    private TypedArray id_image_list;
    private List<CityModelWeather> mCityModelWeathers;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name_city=findViewById(R.id.name_city);
        current_temperature=findViewById(R.id.temperature);
        coat_of_arms=findViewById(R.id.coat_of_arms);
        select_city=findViewById(R.id.spinner);

        name_city_list=getResources().getStringArray(R.array.name_city);
        temperature_city_list=getResources().getStringArray(R.array.temperature_array);
        id_image_list=getResources().obtainTypedArray(R.array.icons_city);
        mCityModelWeathers=new ArrayList<>();
        filling_out_list();

        select_city.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                name_city.setText(mCityModelWeathers.get(i).getName_city());
                current_temperature.setText(mCityModelWeathers.get(i).getTemperature());
                coat_of_arms.setImageDrawable(mCityModelWeathers.get(i).getRes_image());

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }


    private void filling_out_list(){
        for (int i = 0; i <name_city_list.length ; i++) {
            mCityModelWeathers.add(new CityModelWeather(name_city_list[i],temperature_city_list[i],id_image_list.getDrawable(i)));
        }
    }


}
