package ru.gcsales.seminar14.ui.main;

import android.content.Context;
import android.databinding.ObservableField;
import android.os.AsyncTask;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import ru.gcsales.seminar14.data.DataManager;
import ru.gcsales.seminar14.data.model.DailyData;
import ru.gcsales.seminar14.databinding.ActivityMainBinding;

public class MainViewModel {
    private Context mContext;
    private DataManager mDataManager;
    private RecyclerView mRecyclerView;
    private DaysAdapter mDaysAdapter;

    private ObservableField<Boolean> mLoading = new ObservableField<>(false);

    public ObservableField<Boolean> getLoading() {
        return mLoading;
    }

    public MainViewModel(Context context, DataManager dataManager) {
        mContext = context;
        mDataManager = dataManager;
    }

    public void init(ActivityMainBinding binding) {
        binding.setViewModel(this);
        mRecyclerView = binding.recyclerDays;
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mRecyclerView.getContext()));
        mDaysAdapter = new DaysAdapter(mContext, new ArrayList<DailyData>());
        mRecyclerView.setAdapter(mDaysAdapter);

        mLoading.set(true);
        new LoadDaysAsyncTask().execute();
    }

    private class LoadDaysAsyncTask extends AsyncTask<Void, Void, List<DailyData>> {

        @Override
        protected List<DailyData> doInBackground(Void... voids) {
            return mDataManager.getDailyData();
        }

        @Override
        protected void onPostExecute(List<DailyData> dailyData) {
            mDaysAdapter.setData(dailyData);
            mLoading.set(false);
        }
    }

}
