package ru.gcsales.seminar14.ui.main;

import android.os.AsyncTask;

import java.util.List;

import ru.gcsales.seminar14.data.DataManager;
import ru.gcsales.seminar14.data.model.HourlyData;
import ru.gcsales.seminar14.ui.base.BasePresenter;

public class DayPresenter extends BasePresenter<DayMvpView> {

    private final DataManager mDataManager;

    public DayPresenter(DataManager dataManager) {
        mDataManager = dataManager;
    }

    public void loadHourlyData(long time) {
        checkViewAttached();
        getMvpView().showProgress();
        new HourlyDataAsyncTask().execute(time);
    }

    private class HourlyDataAsyncTask extends AsyncTask<Long, Void, List<HourlyData>> {

        @Override
        protected List<HourlyData> doInBackground(Long... longs) {
            return mDataManager.getHourlyData(longs[0]);
        }

        @Override
        protected void onPostExecute(List<HourlyData> hourlyData) {
            getMvpView().hideProgress();
            getMvpView().showData(hourlyData);
        }
    }
}
