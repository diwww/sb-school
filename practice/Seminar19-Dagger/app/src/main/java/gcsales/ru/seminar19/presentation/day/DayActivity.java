package gcsales.ru.seminar19.presentation.day;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;

import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import gcsales.ru.seminar19.R;
import gcsales.ru.seminar19.WeatherApplication;
import gcsales.ru.seminar19.presentation.model.HourModel;


public class DayActivity extends AppCompatActivity implements DayMvpView {

    public static final String TIME_EXTRA = "ru.gcsales.extra.TIME";

    @Inject
    DayPresenter mDayPresenter;

    private ProgressBar mProgressBar;
    private RecyclerView mRecyclerView;
    private HoursAdapter mHoursAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_day);
        ((WeatherApplication) getApplication()).getApplicationComponent().inject(this);
        initView();

        mDayPresenter.attachView(this);
        mDayPresenter.getData(getIntent().getLongExtra(TIME_EXTRA, 0));

        long time = getIntent().getLongExtra(TIME_EXTRA, 0);
        setTitle(String.format(Locale.getDefault(), "%1$ta, %1$td %1$tb",
                TimeUnit.SECONDS.toMillis(time)));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mDayPresenter.detachView();
    }

    @Override
    public void showData(List<HourModel> data) {
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

    private void initView() {
        mRecyclerView = findViewById(R.id.recycler_hours);
        mHoursAdapter = new HoursAdapter();
        mRecyclerView.setAdapter(mHoursAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mProgressBar = findViewById(R.id.progress_bar);
    }

    public static Intent newIntent(Context context, long time) {
        Intent intent = new Intent(context, DayActivity.class);
        intent.putExtra(TIME_EXTRA, time);
        return intent;
    }
}
