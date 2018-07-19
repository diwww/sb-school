package ru.gcsales.seminar13.recyclerview;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import ru.gcsales.seminar13.R;
import ru.gcsales.seminar13.models.BaseItem;
import ru.gcsales.seminar13.models.DayItem;
import ru.gcsales.seminar13.models.HourItem;

public class WeatherAdapter extends RecyclerView.Adapter {
    public static final int HOUR_ITEM_TYPE = 2;

    private SparseArray<ViewHolderFactory> mFactoryMap;
    private List<ViewHolderBinder> mBinders;
    private Context mContext;

    public WeatherAdapter(List<BaseItem> data, Context context) {
        mBinders = new ArrayList<>();
        setData(data);

        mContext = context;

        mFactoryMap = new SparseArray<>();
        mFactoryMap.put(DayItem.DAY_ITEM_TYPE, new DayItemViewHolderFactory());
        mFactoryMap.put(HourItem.HOUR_ITEM_TYPE, new HourItemViewHolderFactory());
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ViewHolderFactory factory = mFactoryMap.get(viewType);
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        return factory.createViewHolder(parent, inflater);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ViewHolderBinder binder = mBinders.get(position);
        if (binder != null) {
            binder.bindViewHolder(holder);
        }
    }

    @Override
    public int getItemViewType(int position) {
        return mBinders.get(position).viewType;
    }

    @Override
    public int getItemCount() {
        return mBinders.size();
    }

    public void setData(List<BaseItem> items) {
        mBinders.clear();
        for (BaseItem item : items) {
            mBinders.add(generateBinder(item));
        }
        notifyDataSetChanged();
    }

    private ViewHolderBinder generateBinder(BaseItem item) {
        if (item.getType() == DayItem.DAY_ITEM_TYPE) {
            return new DayItemViewHolderBinder(item, DayItem.DAY_ITEM_TYPE, mContext);
        }
        if (item.getType() == HourItem.HOUR_ITEM_TYPE) {
            return new HourItemViewHolderBinder(item, HourItem.HOUR_ITEM_TYPE, mContext);
        }

        return null;
    }

    public static class DayViewHolder extends RecyclerView.ViewHolder {

        public TextView dateTextView;
        public TextView maxTempTextView;
        public TextView minTempTextView;
        public ImageView iconImageView;
        public TextView summaryTextView;

        public DayViewHolder(View itemView) {
            super(itemView);
            dateTextView = itemView.findViewById(R.id.text_date);
            maxTempTextView = itemView.findViewById(R.id.text_temp);
            minTempTextView = itemView.findViewById(R.id.text_min_temp);
            iconImageView = itemView.findViewById(R.id.image_icon);
            summaryTextView = itemView.findViewById(R.id.text_summary);
        }
    }

    public static class HourViewHolder extends RecyclerView.ViewHolder {

        public TextView timeTextView;
        public TextView tempTextView;
        public TextView summaryTextView;
        public ImageView iconImageView;
        public TextView humidityTextView;
        public TextView pressureTextView;
        public TextView windSpeedTextView;

        public HourViewHolder(View itemView) {
            super(itemView);
            timeTextView = itemView.findViewById(R.id.text_time);
            tempTextView = itemView.findViewById(R.id.text_temp);
            iconImageView = itemView.findViewById(R.id.image_icon);
            summaryTextView = itemView.findViewById(R.id.text_summary);
            humidityTextView = itemView.findViewById(R.id.text_humidity);
            pressureTextView = itemView.findViewById(R.id.text_pressure);
            windSpeedTextView = itemView.findViewById(R.id.text_windspeed);
        }
    }
}
