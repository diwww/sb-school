package gcsales.ru.seminar19.presentation.day;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import gcsales.ru.seminar19.R;
import gcsales.ru.seminar19.presentation.model.HourModel;

public class HoursAdapter extends RecyclerView.Adapter<HoursAdapter.HourViewHolder> {


    private List<HourModel> mData;

    public HoursAdapter() {
        mData = new ArrayList<>();
    }

    @NonNull
    @Override
    public HourViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_hour, parent, false);
        return new HourViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull HourViewHolder holder, int position) {
        HourModel hourModel = mData.get(position);
        holder.timeTextView.setText(String.format(Locale.getDefault(),
                "%1$tH:%1$tM", hourModel.getDate()));
        holder.tempTextView.setText(String.format(Locale.getDefault(),
                "%.0f\u2103", hourModel.getTemperature()));
        holder.summaryTextView.setText(hourModel.getSummary());
//        holder.iconImageView.setImageResource(Config.ICON_DRAWABLE_MAP.get(hourModel.getIcon()));
        holder.iconImageView.setImageResource(R.drawable.ic_launcher_background);
        // TODO: percent constant
        // TODO: Move text to string resources
        holder.humidityTextView.setText(String.format(Locale.getDefault(),
                "Влажность: %.0f%%", hourModel.getHumidity() * 100));
        holder.windSpeedTextView.setText(String.format(Locale.getDefault(),
                "Ветер: %.0f м/c", hourModel.getWindSpeed()));
        holder.pressureTextView.setText(String.format(Locale.getDefault(),
                "Давление: %.0f Па", hourModel.getPressure()));
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public void setData(List<HourModel> data) {
        mData = data;
        notifyDataSetChanged();
    }

    public static class HourViewHolder extends RecyclerView.ViewHolder {

        TextView timeTextView;
        TextView tempTextView;
        TextView summaryTextView;
        TextView humidityTextView;
        TextView pressureTextView;
        TextView windSpeedTextView;
        ImageView iconImageView;

        public HourViewHolder(View itemView) {
            super(itemView);
            timeTextView = itemView.findViewById(R.id.text_time);
            tempTextView = itemView.findViewById(R.id.text_temp);
            summaryTextView = itemView.findViewById(R.id.text_summary);
            humidityTextView = itemView.findViewById(R.id.text_humidity);
            pressureTextView = itemView.findViewById(R.id.text_pressure);
            windSpeedTextView = itemView.findViewById(R.id.text_windspeed);
            iconImageView = itemView.findViewById(R.id.image_icon);
        }
    }
}
