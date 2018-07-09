package ru.gcsales.seminar14.recyclerview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;


import java.util.Locale;
import java.util.concurrent.TimeUnit;

import ru.gcsales.seminar14.Config;
import ru.gcsales.seminar14.activities.DayActivity;
import ru.gcsales.seminar14.models.BaseItem;
import ru.gcsales.seminar14.models.DayItem;

public class DayItemViewHolderBinder extends ViewHolderBinder {

    private final DayItem mDayItem;
    private Context mContext;

    public DayItemViewHolderBinder(BaseItem item, int viewType, Context context) {
        super(viewType);

        mContext = context;

        mDayItem = (DayItem) item;
    }

    @Override
    public void bindViewHolder(RecyclerView.ViewHolder viewHolder) {
        WeatherAdapter.DayViewHolder holder = (WeatherAdapter.DayViewHolder) viewHolder;
        holder.dateTextView.setText(String.format(Locale.getDefault(), "%1$ta, %1$td %1$tb", mDayItem.getDate()));
        holder.maxTempTextView.setText(String.format(Locale.getDefault(), "%2.0f\u2103", mDayItem.getMaxTemp()));
        holder.minTempTextView.setText(String.format(Locale.getDefault(), "%2.0f\u2103", mDayItem.getMinTemp()));
        holder.summaryTextView.setText(mDayItem.getSummary());
        holder.iconImageView.setImageResource(Config.ICON_DRAWABLE_MAP.get(mDayItem.getIconName()));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mContext.startActivity(DayActivity.newIntent(mContext, TimeUnit.MILLISECONDS.toSeconds(mDayItem.getDate().getTime())));
            }
        });
    }

    @Override
    public BaseItem getItem() {
        return mDayItem;
    }
}
