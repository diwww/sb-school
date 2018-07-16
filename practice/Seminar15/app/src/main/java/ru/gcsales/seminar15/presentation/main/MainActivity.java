package ru.gcsales.seminar15.presentation.main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;

import java.util.ArrayList;
import java.util.List;

import ru.gcsales.seminar15.R;
import ru.gcsales.seminar15.presentation.base.BaseActivity;
import ru.gcsales.seminar15.presentation.model.DayModel;

public class MainActivity extends BaseActivity implements MainMvpView {

    private ProgressBar mProgressBar;
    private RecyclerView mRecyclerView;
    private DaysAdapter mDaysAdapter;
    private MainPresenter mMainPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mProgressBar = findViewById(R.id.progress_bar);
        mRecyclerView = findViewById(R.id.recycler_days);
        mDaysAdapter = new DaysAdapter(this, new ArrayList<DayModel>());
        mRecyclerView.setAdapter(mDaysAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mRecyclerView.getContext()));

        mMainPresenter = new MainPresenter();
        mMainPresenter.attachView(this);

        mMainPresenter.loadData();
    }

    @Override
    public void showProgress() {
        mProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        mProgressBar.setVisibility(View.GONE);
    }

    @Override
    public void showData(List<DayModel> data) {
        mDaysAdapter.setData(data);
    }
}
