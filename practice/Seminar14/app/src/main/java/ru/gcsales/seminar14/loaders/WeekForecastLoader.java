package ru.gcsales.seminar14.loaders;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.AsyncTaskLoader;
import android.util.Log;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import ru.gcsales.seminar14.api.ApiMapper;
import ru.gcsales.seminar14.api.RetrofitHelper;
import ru.gcsales.seminar14.database.DayEntity;
import ru.gcsales.seminar14.database.WeatherDatabase;
import ru.gcsales.seminar14.models.BaseItem;
import ru.gcsales.seminar14.models.DayItem;
import ru.gcsales.seminar14.webmodels.DailyData;
import ru.gcsales.seminar14.webmodels.Forecast;

public class WeekForecastLoader extends AsyncTaskLoader<List<BaseItem>> {

    private static final String TAG = "WeekForecastLoader";

    private Bundle mArgs;
    private WeatherDatabase mWeatherDatabase;

    public WeekForecastLoader(@NonNull Context context, Bundle args) {
        super(context);
        this.mArgs = args;
        mWeatherDatabase = Room.databaseBuilder(context, WeatherDatabase.class, "weather")
                .build();
    }

    @Nullable
    @Override
    public List<BaseItem> loadInBackground() {
        try {
            // Get raw data from internet
            Forecast forecast = new ApiMapper(new RetrofitHelper()).getWeekForecastSync();
            List<DailyData> dailyData = forecast.getDaily().getData();
            // Transform raw data to models
            List<BaseItem> dayItems = new ArrayList<>();
            for (DailyData d : dailyData) {
                // Convert to model
                DayItem dayItem = new DayItem(d.getTemperatureMax(), d.getTemperatureMin(),
                        new Date(TimeUnit.SECONDS.toMillis(d.getTime())), d.getIcon(),
                        d.getSummary());
                dayItems.add(dayItem);
                // Convert to entity
                DayEntity dayEntity = new DayEntity(d.getTime(), d.getTemperatureMax(),
                        d.getTemperatureMin(), d.getIcon(), d.getSummary());
                mWeatherDatabase.getDayDao().insert(dayEntity);
            }
            // Return transformed data
            return dayItems;
        } catch (IOException e) {
            Log.e(TAG, "loadInBackground: " + e.getMessage());
            List<BaseItem> dayItems = new ArrayList<>();
            List<DayEntity> list = mWeatherDatabase.getDayDao().getDays(TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis()));
            for (DayEntity dayEntity : list) {
                DayItem dayItem = new DayItem(dayEntity.getMaxTemp(), dayEntity.getMinTemp(),
                        new Date(TimeUnit.SECONDS.toMillis(dayEntity.getTimeStamp())),
                        dayEntity.getIconName(), dayEntity.getSummary());
                dayItems.add(dayItem);
            }
            return dayItems;
        }
    }
}
