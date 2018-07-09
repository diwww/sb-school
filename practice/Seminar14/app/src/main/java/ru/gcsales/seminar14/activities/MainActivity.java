package ru.gcsales.seminar14.activities;

import android.support.annotation.NonNull;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;

import java.util.ArrayList;
import java.util.List;

import ru.gcsales.seminar14.R;
import ru.gcsales.seminar14.loaders.WeekForecastLoader;
import ru.gcsales.seminar14.recyclerview.WeatherAdapter;
import ru.gcsales.seminar14.models.BaseItem;


public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<BaseItem>> {

    private static final int LOADER_ID = 1001;

    private ProgressBar mProgressBar;
    private RecyclerView mRecyclerView;
    private WeatherAdapter mWeatherAdapter;
    private LinearLayoutManager mLayoutManager;
    private DividerItemDecoration mDividerItemDecoration;
    private Loader<List<BaseItem>> mLoader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        startLoader();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    private void init() {
        mProgressBar = findViewById(R.id.progress_bar);

        mRecyclerView = findViewById(R.id.recycler_days);

        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        mWeatherAdapter = new WeatherAdapter(new ArrayList<BaseItem>(), this);
        mRecyclerView.setAdapter(mWeatherAdapter);

        mDividerItemDecoration = new DividerItemDecoration(mRecyclerView.getContext(),
                mLayoutManager.getOrientation());
        mRecyclerView.addItemDecoration(mDividerItemDecoration);
    }

    private void startLoader() {
        mLoader = getSupportLoaderManager().initLoader(LOADER_ID, null, this);
        mLoader.forceLoad();
        mProgressBar.setVisibility(View.VISIBLE);
    }

    @NonNull
    @Override
    public Loader<List<BaseItem>> onCreateLoader(int id, Bundle args) {
        WeekForecastLoader loader = null;
        if (id == LOADER_ID) {
            loader = new WeekForecastLoader(this, args);
        }
        return loader;
    }

    @Override
    public void onLoadFinished(@NonNull Loader<List<BaseItem>> loader, List<BaseItem> data) {
        mWeatherAdapter.setData(data);
        mProgressBar.setVisibility(View.GONE);
    }

    @Override
    public void onLoaderReset(@NonNull Loader<List<BaseItem>> loader) {
        mProgressBar.setVisibility(View.GONE);
    }
}
