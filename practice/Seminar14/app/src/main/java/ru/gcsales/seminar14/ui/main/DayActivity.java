package ru.gcsales.seminar14.ui.main;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import ru.gcsales.seminar14.R;
import ru.gcsales.seminar14.data.DataManager;
import ru.gcsales.seminar14.data.local.ForecastDatabase;
import ru.gcsales.seminar14.data.model.HourlyData;
import ru.gcsales.seminar14.data.remote.ForecastService;
import ru.gcsales.seminar14.ui.base.BaseActivity;

public class DayActivity extends BaseActivity implements DayMvpView {

    public static final String TIME_EXTRA = "ru.gcsales.extra.TIME";

    private ProgressBar mProgressBar;
    private RecyclerView mRecyclerView;
    private HoursAdapter mHoursAdapter;
    private DayPresenter mDayPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_day);

        mProgressBar = findViewById(R.id.progress_bar);
        mRecyclerView = findViewById(R.id.recycler_hours);
        mHoursAdapter = new HoursAdapter(new ArrayList<HourlyData>());
        mRecyclerView.setAdapter(mHoursAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        // This will be replaced with dependency injection in future
        ForecastService service = ForecastService.Creator.newForecastService();
        ForecastDatabase database = Room.databaseBuilder(this, ForecastDatabase.class, "forecastdb")
                .build();
        DataManager dataManager = new DataManager(service, database);

        mDayPresenter = new DayPresenter(dataManager);
        mDayPresenter.attachView(this);
        mDayPresenter.loadHourlyData(getIntent().getLongExtra(TIME_EXTRA, 0));

        long time = getIntent().getLongExtra(TIME_EXTRA, 0);
        setTitle(String.format(Locale.getDefault(), "%1$ta, %1$td %1$tb",
                TimeUnit.SECONDS.toMillis(time)));
    }

    @Override
    public void showData(List<HourlyData> data) {
        mHoursAdapter.setData(data);
    }

    @Override
    public void showProgress() {
        mProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        mProgressBar.setVisibility(View.GONE);
    }

    public static Intent newIntent(Context context, long time) {
        Intent intent = new Intent(context, DayActivity.class);
        intent.putExtra(TIME_EXTRA, time);
        return intent;
    }
}
