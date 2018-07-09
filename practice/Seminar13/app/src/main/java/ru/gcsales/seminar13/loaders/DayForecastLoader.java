package ru.gcsales.seminar13.loaders;

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

import ru.gcsales.seminar13.activities.DayActivity;
import ru.gcsales.seminar13.api.ApiMapper;
import ru.gcsales.seminar13.api.RetrofitHelper;
import ru.gcsales.seminar13.database.HourEntity;
import ru.gcsales.seminar13.database.WeatherDatabase;
import ru.gcsales.seminar13.models.BaseItem;
import ru.gcsales.seminar13.models.HourItem;
import ru.gcsales.seminar13.webmodels.Forecast;
import ru.gcsales.seminar13.webmodels.HourlyData;

public class DayForecastLoader extends AsyncTaskLoader<List<BaseItem>> {

    private static final String TAG = "DayForecastLoader";
    public static final int SECONDS_IN_DAY = 86400;
    private long mTime;
    private WeatherDatabase mWeatherDatabase;

    public DayForecastLoader(@NonNull Context context, Bundle args) {
        super(context);
        mTime = args.getLong(DayActivity.TIME_EXTRA);
        mWeatherDatabase = Room.databaseBuilder(context, WeatherDatabase.class, "weather")
                .build();
    }

    @Nullable
    @Override
    public List<BaseItem> loadInBackground() {
        try {
            // Get raw data from internet
            Forecast forecast = new ApiMapper(new RetrofitHelper()).getDayForecastSync(mTime);
            List<HourlyData> hourlyData = forecast.getHourly().getData();
            // Transform raw data to models
            List<BaseItem> hourItems = new ArrayList<>();
            for (HourlyData d : hourlyData) {
                // Convert to model
                HourItem hourItem = new HourItem()
                        .setHumidity(d.getHumidity())
                        .setPressure(d.getPressure())
                        .setTemp(d.getTemperature())
                        .setTime(new Date(TimeUnit.SECONDS.toMillis(d.getTime())))
                        .setSummary(d.getSummary())
                        .setWindSpeed(d.getWindSpeed())
                        .setIcon(d.getIcon());
                hourItems.add(hourItem);
                // Convert to entity
                HourEntity hourEntity = new HourEntity(d.getTime(), d.getTemperature(), d.getSummary(),
                        d.getHumidity(), d.getWindSpeed(), d.getPressure(), d.getIcon());
                mWeatherDatabase.getHourDao().insert(hourEntity);
            }
            return hourItems;
        } catch (IOException e) {
            Log.e(TAG, "loadInBackground: " + e.getMessage());
            List<BaseItem> hourItems = new ArrayList<>();
            List<HourEntity> list = mWeatherDatabase.getHourDao().getHours(mTime, mTime + SECONDS_IN_DAY);
            for (HourEntity hourEntity : list) {
                HourItem hourItem = new HourItem()
                        .setTime(new Date(TimeUnit.SECONDS.toMillis(hourEntity.getTimeStamp())))
                        .setTemp(hourEntity.getTemp())
                        .setWindSpeed(hourEntity.getWindSpeed())
                        .setSummary(hourEntity.getSummary())
                        .setIcon(hourEntity.getIcon())
                        .setHumidity(hourEntity.getHumidity())
                        .setPressure(hourEntity.getPressure());
                hourItems.add(hourItem);
            }
            return hourItems;
        }
    }
}
