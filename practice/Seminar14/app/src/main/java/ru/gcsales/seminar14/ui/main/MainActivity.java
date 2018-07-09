package ru.gcsales.seminar14.ui.main;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ProgressBar;

import java.util.ArrayList;

import ru.gcsales.seminar14.R;
import ru.gcsales.seminar14.data.model.DailyData;
import ru.gcsales.seminar14.ui.base.BaseActivity;


public class MainActivity extends BaseActivity {

    private ProgressBar mProgressBar;
    private RecyclerView mRecyclerView;
    private DaysAdapter mDaysAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    private void init() {
        mProgressBar = findViewById(R.id.progress_bar);
        mRecyclerView = findViewById(R.id.recycler_days);
        mDaysAdapter = new DaysAdapter(new ArrayList<DailyData>());
        mRecyclerView.setAdapter(mDaysAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}
