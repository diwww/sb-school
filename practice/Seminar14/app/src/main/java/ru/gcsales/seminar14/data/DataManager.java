package ru.gcsales.seminar14.data;

import android.util.Log;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import retrofit2.Response;
import ru.gcsales.seminar14.data.local.DayDao;
import ru.gcsales.seminar14.data.local.DayEntity;
import ru.gcsales.seminar14.data.local.DbUtils;
import ru.gcsales.seminar14.data.local.HourDao;
import ru.gcsales.seminar14.data.local.HourEntity;
import ru.gcsales.seminar14.data.local.ForecastDatabase;
import ru.gcsales.seminar14.data.model.DailyData;
import ru.gcsales.seminar14.data.model.Forecast;
import ru.gcsales.seminar14.data.model.HourlyData;
import ru.gcsales.seminar14.data.remote.ForecastService;

public class DataManager {

    private static final String TAG = "DataManager";
    /**
     * Access token
     */
    public static final String KEY = "eb42cb6a8ffa70963fcc15e417c08922";
    /**
     * Moscow coordinates
     */
    public static final String COORDS = "55.7549,37.6219";
    public static final int SECONDS_IN_DAY = 86400;

    private final ForecastService mForecastService;
    private final ForecastDatabase mForecastDatabase;

    public DataManager(ForecastService forecastService, ForecastDatabase forecastDatabase) {
        mForecastService = forecastService;
        mForecastDatabase = forecastDatabase;
    }

    /**
     * Get week forecast from internet, or
     * from database, if there is no connection.
     * Must be called on a separate thread.
     *
     * @return list of daily data
     */
    public List<DailyData> getDailyData() {
        // Query params map
        Map<String, String> options = new HashMap<>();
        options.put("lang", "ru");
        options.put("units", "si");
        options.put("exclude", "currently,hourly,minutely,alerts,flags");

        try {
            // Get response and write to database
            Response<Forecast> response = mForecastService.getForecasts(KEY, COORDS, options)
                    .execute();
            insertDailyData(response.body());
        } catch (IOException e) {
            // If there is no internet connection
            Log.e(TAG, "downloadDailyData: " + e.getMessage());
        }

        // Get data from database
        return readDailyData();
    }

    /**
     * Get day forecast from internet, of
     * from database, if there is no connection.
     * Must be called on a separate thread.
     *
     * @param time UNIX timestamp of the given day
     * @return list of hourly data for a given day
     */
    public List<HourlyData> getHourlyData(long time) {
        // Query params map
        Map<String, String> options = new HashMap<>();
        options.put("lang", "ru");
        options.put("units", "si");
        options.put("exclude", "currently,daily,minutely,alerts,flags");

        String path = COORDS + "," + time;
        try {
            // Get response and write to database
            Response<Forecast> response = mForecastService.getForecasts(KEY, path, options)
                    .execute();
            insertHourlyData(response.body());
        } catch (IOException e) {
            // If there is no internet connection
            Log.e(TAG, "getHourlyData: " + e.getMessage());
        }

        // One day time bounds
        long timeLower = time;
        long timeUpper = time + SECONDS_IN_DAY;

        // Get data from database
        return readHourlyData(timeLower, timeUpper);
    }

    private void insertDailyData(Forecast forecast) {
        List<DailyData> dailyData = forecast.getDaily().getData();
        DayDao dayDao = mForecastDatabase.getDayDao();
        for (DailyData d : dailyData) {
            dayDao.insert(DbUtils.modelToEntity(d));
        }
    }

    private List<DailyData> readDailyData() {
        DayDao dayDao = mForecastDatabase.getDayDao();
        List<DayEntity> dayEntities = dayDao
                .getDays(TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis()));
        List<DailyData> dailyData = new ArrayList<>();
        for (DayEntity d : dayEntities) {
            dailyData.add(DbUtils.entityToModel(d));
        }
        return dailyData;
    }

    private void insertHourlyData(Forecast forecast) {
        List<HourlyData> hourlyData = forecast.getHourly().getData();
        HourDao hourDao = mForecastDatabase.getHourDao();
        for (HourlyData d : hourlyData) {
            hourDao.insert(DbUtils.modelToEntity(d));
        }
    }

    private List<HourlyData> readHourlyData(long timeLower, long timeUpper) {
        HourDao hourDao = mForecastDatabase.getHourDao();
        List<HourEntity> hourEntities = hourDao.getHours(timeLower, timeUpper);
        List<HourlyData> hourlyData = new ArrayList<>();
        for (HourEntity d : hourEntities) {
            hourlyData.add(DbUtils.entityToModel(d));
        }
        return hourlyData;
    }
}

