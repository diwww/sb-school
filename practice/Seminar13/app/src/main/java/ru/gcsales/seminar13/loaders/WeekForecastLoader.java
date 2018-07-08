package ru.gcsales.seminar13.loaders;

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

import ru.gcsales.seminar13.api.ApiMapper;
import ru.gcsales.seminar13.api.RetrofitHelper;
import ru.gcsales.seminar13.models.BaseItem;
import ru.gcsales.seminar13.models.DayItem;
import ru.gcsales.seminar13.webmodels.DailyData;
import ru.gcsales.seminar13.webmodels.Forecast;

public class WeekForecastLoader extends AsyncTaskLoader<List<BaseItem>> {

    private static final String TAG = "WebDataLoader";

    private Bundle mArgs;

    public WeekForecastLoader(@NonNull Context context, Bundle args) {
        super(context);
        this.mArgs = args;
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
                DayItem dayItem = new DayItem(d.getTemperatureMax(), d.getTemperatureMin(),
                        new Date(TimeUnit.SECONDS.toMillis(d.getTime())), d.getIcon(),
                        d.getSummary());
                dayItems.add(dayItem);
            }
            // Return transformed data
            return dayItems;
        } catch (IOException e) {
            Log.e(TAG, "loadInBackground: " + e.getMessage());
        }
        return null;
    }
}
