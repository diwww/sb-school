package ru.gcsales.seminar14.ui.main;

import android.arch.persistence.room.Room;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ProgressBar;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import ru.gcsales.seminar14.R;
import ru.gcsales.seminar14.data.DataManager;
import ru.gcsales.seminar14.data.local.ForecastDatabase;
import ru.gcsales.seminar14.data.model.DailyData;
import ru.gcsales.seminar14.data.remote.ForecastService;
import ru.gcsales.seminar14.databinding.ActivityMainBinding;
import ru.gcsales.seminar14.ui.base.BaseActivity;
import ru.gcsales.seminar14.util.DataManagerProvider;
import ru.gcsales.seminar14.util.MyApplication;


public class MainActivity extends BaseActivity {

    private MainViewModel mMainViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        mMainViewModel = new MainViewModel(this, ((DataManagerProvider) getApplication()).getDataManager());
        mMainViewModel.init(binding);
    }

}
