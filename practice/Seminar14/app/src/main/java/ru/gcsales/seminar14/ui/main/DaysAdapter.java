package ru.gcsales.seminar14.ui.main;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import ru.gcsales.seminar14.R;
import ru.gcsales.seminar14.data.model.DailyData;
import ru.gcsales.seminar14.databinding.ItemDayBinding;
import ru.gcsales.seminar14.ui.day.DayActivity;

public class DaysAdapter extends RecyclerView.Adapter<DaysAdapter.DayViewHolder> {

    private List<DailyData> mData;
    private Context mContext;

    public DaysAdapter(Context context, List<DailyData> data) {
        mContext = context;
        mData = data;
    }


    @NonNull
    @Override
    public DayViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        ItemDayBinding binding = DataBindingUtil.inflate(inflater, R.layout.item_day, parent, false);
        return new DayViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull final DayViewHolder holder, int position) {
        final DailyData dailyData = mData.get(position);
        holder.binding.setDailyData(dailyData);
        holder.binding.getRoot().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mContext.startActivity(DayActivity.newIntent(mContext, dailyData.getTime()));
            }
        });
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public void setData(List<DailyData> data) {
        mData = data;
        notifyDataSetChanged();
    }

    public static class DayViewHolder extends RecyclerView.ViewHolder {

        ItemDayBinding binding;

        public DayViewHolder(ItemDayBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
