package ru.gcsales.seminar13.recyclerview;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ru.gcsales.seminar13.R;

public class HourItemViewHolderFactory implements ViewHolderFactory {
    @Override
    public RecyclerView.ViewHolder createViewHolder(ViewGroup parent, LayoutInflater inflater) {
        View itemView = inflater.inflate(R.layout.item_forecast_hour, parent, false);
        return new WeatherAdapter.HourViewHolder(itemView);
    }
}
