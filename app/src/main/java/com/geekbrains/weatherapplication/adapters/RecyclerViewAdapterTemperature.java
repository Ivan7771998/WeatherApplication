package com.geekbrains.weatherapplication.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.geekbrains.weatherapplication.R;
import com.geekbrains.weatherapplication.model.HistoryDataTemperature;

public class RecyclerViewAdapterTemperature
        extends RecyclerView.Adapter<RecyclerViewAdapterTemperature.ViewHolder> {

    private HistoryDataTemperature data;

    public RecyclerViewAdapterTemperature(HistoryDataTemperature data) {
        if (data != null) {
            this.data = data;
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();

        View view = LayoutInflater.from(context)
                .inflate(R.layout.history_temperature_item, parent, false);
        return new ViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        holder.time.setText(data.list_time_history[position]);
        holder.temperature.setText(data.list_temperature_history[position] + " \u00B0C");
    }

    @Override
    public int getItemCount() {
        return data.list_temperature_history.length;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView time;
        TextView temperature;

        ViewHolder(View view) {
            super(view);
            time = itemView.findViewById(R.id.text_temperature_history_time);
            temperature = itemView.findViewById(R.id.text_temperature_history);
        }
    }
}
