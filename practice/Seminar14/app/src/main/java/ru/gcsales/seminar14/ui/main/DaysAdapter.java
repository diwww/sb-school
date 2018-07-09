package ru.gcsales.seminar14.ui.main;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import ru.gcsales.seminar14.R;
import ru.gcsales.seminar14.util.Config;
import ru.gcsales.seminar14.data.model.DailyData;

public class DaysAdapter extends RecyclerView.Adapter<DaysAdapter.DayViewHolder> {

    private List<DailyData> mData;

    public DaysAdapter(List<DailyData> data) {
        mData = data;
    }


    @NonNull
    @Override
    public DayViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_day, parent, false);
        return new DayViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull DayViewHolder holder, int position) {
        DailyData dailyData = mData.get(position);
        holder.dateTextView.setText(String.format(Locale.getDefault(),
                "%1$ta, %1$td %1$tb", TimeUnit.SECONDS.toMillis(dailyData.getTime())));
        holder.dayTempTextView.setText(String.format(Locale.getDefault(),
                "%2.0f\u2103", dailyData.getTemperatureHigh()));
        holder.nightTempTextView.setText(String.format(Locale.getDefault(),
                "%2.0f\u2103", dailyData.getTemperatureLow()));
        holder.summaryTextView.setText(dailyData.getSummary());
        holder.iconImageView.setImageResource(Config.ICON_DRAWABLE_MAP.get(dailyData.getIcon()));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO
            }
        });
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class DayViewHolder extends RecyclerView.ViewHolder {

        TextView dateTextView;
        TextView dayTempTextView;
        TextView nightTempTextView;
        ImageView iconImageView;
        TextView summaryTextView;

        public DayViewHolder(View itemView) {
            super(itemView);
            dateTextView = itemView.findViewById(R.id.text_date);
            dayTempTextView = itemView.findViewById(R.id.text_day_temp);
            nightTempTextView = itemView.findViewById(R.id.text_night_temp);
            iconImageView = itemView.findViewById(R.id.image_icon);
            summaryTextView = itemView.findViewById(R.id.text_summary);
        }
    }
}
