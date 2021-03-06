package gcsales.ru.seminar20.presentation.week;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;

import java.util.List;

import javax.inject.Inject;

import gcsales.ru.seminar20.R;
import gcsales.ru.seminar20.WeatherApplication;
import gcsales.ru.seminar20.presentation.model.DayModel;

public class WeekActivity extends AppCompatActivity implements WeekMvpView {

    @Inject
    WeekPresenter mWeekPresenter;

    private RecyclerView mRecyclerView;
    private DaysAdapter mAdapter;
    private ProgressBar mProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_week);
        ((WeatherApplication) getApplication()).getApplicationComponent().inject(this);
        initView();

        mWeekPresenter.attachView(this);
        mWeekPresenter.getData();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        mWeekPresenter.detachView();
    }

    @Override
    public void showData(List<DayModel> data) {
        mAdapter.setData(data);
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
        mRecyclerView = findViewById(R.id.recycler_view_days);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mRecyclerView.getContext()));
        mAdapter = new DaysAdapter();
        mRecyclerView.setAdapter(mAdapter);
        mProgressBar = findViewById(R.id.progress_bar);
    }
}
