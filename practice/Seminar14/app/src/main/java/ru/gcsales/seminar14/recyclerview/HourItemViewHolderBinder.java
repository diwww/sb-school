package ru.gcsales.seminar14.recyclerview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;

import java.util.Locale;

import ru.gcsales.seminar14.Config;
import ru.gcsales.seminar14.models.BaseItem;
import ru.gcsales.seminar14.models.HourItem;

public class HourItemViewHolderBinder extends ViewHolderBinder {

    private final HourItem mHourItem;
    private Context mContext;

    public HourItemViewHolderBinder(BaseItem item, int viewType, Context context) {
        super(viewType);

        mHourItem = (HourItem) item;
        mContext = context;
    }


    @Override
    public void bindViewHolder(RecyclerView.ViewHolder viewHolder) {
        WeatherAdapter.HourViewHolder holder = (WeatherAdapter.HourViewHolder) viewHolder;

        holder.timeTextView.setText(String.format(Locale.getDefault(), "%1$tH:%1$tM", mHourItem.getTime()));
        holder.tempTextView.setText(String.format(Locale.getDefault(), "%.0f\u2103", mHourItem.getTemp()));
        holder.summaryTextView.setText(mHourItem.getSummary());
        holder.iconImageView.setImageResource(Config.ICON_DRAWABLE_MAP.get(mHourItem.getIcon()));
        holder.humidityTextView.setText(String.format(Locale.getDefault(), "Влажность: %.0f%%", mHourItem.getHumidity() * 100));
        holder.windSpeedTextView.setText(String.format(Locale.getDefault(), "Ветер: %.0f м/c", mHourItem.getWindSpeed()));
        holder.pressureTextView.setText(String.format(Locale.getDefault(), "Давление: %.0f Па", mHourItem.getPressure()));
    }

    @Override
    public BaseItem getItem() {
        return null;
    }
}
