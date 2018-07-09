package ru.gcsales.seminar14.activities;

import android.content.Context;
import android.content.Intent;
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
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import ru.gcsales.seminar14.R;
import ru.gcsales.seminar14.loaders.DayForecastLoader;
import ru.gcsales.seminar14.models.BaseItem;
import ru.gcsales.seminar14.recyclerview.WeatherAdapter;

public class DayActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<BaseItem>> {

    public static final String TIME_EXTRA = "ru.gcsales.extra.TIME";
    private static final int LOADER_ID = 1002;

    private ProgressBar mProgressBar;
    private RecyclerView mRecyclerView;
    private WeatherAdapter mWeatherAdapter;
    private LinearLayoutManager mLayoutManager;
    private DividerItemDecoration mDividerItemDecoration;
    private Loader<List<BaseItem>> mLoader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_day);
        init();
        startLoader();
    }

    private void init() {
        long time = getIntent().getLongExtra(TIME_EXTRA, 0);
        setTitle(String.format(Locale.getDefault(), "%1$ta, %1$td %1$tb", TimeUnit.SECONDS.toMillis(time)));

        mProgressBar = findViewById(R.id.progress_bar);

        mRecyclerView = findViewById(R.id.recycler_hours);

        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        mWeatherAdapter = new WeatherAdapter(new ArrayList<BaseItem>(), this);
        mRecyclerView.setAdapter(mWeatherAdapter);

        mDividerItemDecoration = new DividerItemDecoration(mRecyclerView.getContext(),
                mLayoutManager.getOrientation());
        mRecyclerView.addItemDecoration(mDividerItemDecoration);
    }

    private void startLoader() {
        Bundle args = new Bundle();
        args.putLong(TIME_EXTRA, getIntent().getLongExtra(TIME_EXTRA, 0));
        mLoader = getSupportLoaderManager().initLoader(LOADER_ID, args, this);
        mLoader.forceLoad();
        mProgressBar.setVisibility(View.VISIBLE);
    }

    @NonNull
    @Override
    public Loader<List<BaseItem>> onCreateLoader(int id, Bundle args) {
        DayForecastLoader loader = null;
        if (id == LOADER_ID) {
            loader = new DayForecastLoader(this, args);
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

    public static final Intent newIntent(Context context, long time) {
        Intent intent = new Intent(context, DayActivity.class);
        intent.putExtra(TIME_EXTRA, time);
        return intent;
    }
}
