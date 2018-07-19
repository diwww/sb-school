package ru.gcsales.seminar15.presentation.main;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import ru.gcsales.seminar15.R;
import ru.gcsales.seminar15.presentation.model.DayModel;


public class DaysAdapter extends RecyclerView.Adapter<DaysAdapter.DayViewHolder> {

    private List<DayModel> mData;
    private Context mContext;

    public DaysAdapter(Context context, List<DayModel> data) {
        mContext = context;
        mData = data;
    }


    @NonNull
    @Override
    public DayViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_day, parent, false);
        return new DayViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DayViewHolder holder, int position) {
        DayModel dailyData = mData.get(position);
        holder.dayTempTextView.setText(dailyData.getDayTemperature() + "");
        holder.nightTempTextView.setText(dailyData.getNightTemperature() + "");
        holder.summaryTextView.setText(dailyData.getSummary());
        // TODO

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public void setData(List<DayModel> data) {
        mData = data;
        notifyDataSetChanged();
    }

    public static class DayViewHolder extends RecyclerView.ViewHolder {

        TextView dayTempTextView;
        TextView nightTempTextView;
        ImageView iconImageView;
        TextView dateTextView;
        TextView summaryTextView;

        public DayViewHolder(View itemView) {
            super(itemView);
            dayTempTextView = itemView.findViewById(R.id.text_day_temp);
            nightTempTextView = itemView.findViewById(R.id.text_night_temp);
            iconImageView = itemView.findViewById(R.id.image_icon);
            dateTextView = itemView.findViewById(R.id.text_date);
            summaryTextView = itemView.findViewById(R.id.text_summary);
        }
    }
}
