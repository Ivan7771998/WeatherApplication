package com.geekbrains.weatherapplication.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.geekbrains.weatherapplication.R;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewAdapterCity extends RecyclerView.Adapter<RecyclerViewAdapterCity.ViewHolder> {

    private ArrayList<String> data;
    private OnItemClickListener listener;

    public RecyclerViewAdapterCity(ArrayList<String> data, OnItemClickListener listener) {
        if (data != null) {
            this.data = data;
            this.listener = listener;
        }
    }

    public void addItem(String newCity) {
        data.add(1, newCity);
        notifyItemInserted(1);
    }

    @NonNull
    @Override
    public RecyclerViewAdapterCity.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        View view = LayoutInflater.from(context)
                .inflate(R.layout.recycler_item_city, parent, false);
        return new RecyclerViewAdapterCity.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.textCity.setText(data.get(position));
        holder.textCity.setOnClickListener(view -> {
            listener.onItemClick(position);
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView textCity;

        ViewHolder(View view) {
            super(view);
            textCity = itemView.findViewById(R.id.text_city);
        }
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }
}
