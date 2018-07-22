package gcsales.ru.seminar19.presentation.week;

import android.content.Context;
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
import java.util.concurrent.TimeUnit;

import gcsales.ru.seminar19.R;
import gcsales.ru.seminar19.presentation.day.DayActivity;
import gcsales.ru.seminar19.presentation.model.DayModel;


public class DaysAdapter extends RecyclerView.Adapter<DaysAdapter.DayViewHolder> {

    private List<DayModel> mData = new ArrayList<>();
    private Context mContext;

    public DaysAdapter(Context context) {
        mContext = context;
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
        final DayModel dayModel = mData.get(position);
        holder.dayTempTextView.setText(String.format(Locale.getDefault(), "%.2f", dayModel.getDayTemperature()));
        holder.nightTempTextView.setText(String.format(Locale.getDefault(), "%.2f", dayModel.getNightTemperature()));
        holder.summaryTextView.setText(dayModel.getSummary());
        holder.dateTextView.setText(String.format(Locale.getDefault(),
                "%1$ta, %1$td %1$tb", dayModel.getDate()));

        // TODO
        holder.iconImageView.setImageResource(R.drawable.ic_launcher_background);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mContext.startActivity(DayActivity.newIntent(mContext, TimeUnit.MILLISECONDS.toSeconds(dayModel.getDate().getTime())));
            }
        });
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
