package gcsales.ru.seminar19.data;

import android.util.Log;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import gcsales.ru.seminar19.data.mapper.DailyDataMapper;
import gcsales.ru.seminar19.data.mapper.HourlyDataMapper;
import gcsales.ru.seminar19.data.model.Forecast;
import gcsales.ru.seminar19.domain.model.Day;
import gcsales.ru.seminar19.domain.model.Hour;
import gcsales.ru.seminar19.domain.repository.Repository;
import retrofit2.Response;

public class ForecastDataRepository implements Repository {

    private static final String TAG = "ForecastDataRepository";

    /**
     * Access token
     */
    public static final String KEY = "eb42cb6a8ffa70963fcc15e417c08922";
    /**
     * Moscow coordinates
     */
    public static final String COORDS = "55.7549,37.6219";
    public static final int SECONDS_IN_DAY = 86400;

    private ForecastService mForecastService;
    private DailyDataMapper mDailyDataMapper;
    private HourlyDataMapper mHourlyDataMapper;

    public ForecastDataRepository(ForecastService forecastService,
                                  DailyDataMapper dailyDataMapper,
                                  HourlyDataMapper hourlyDataMapper) {
        mForecastService = forecastService;
        mDailyDataMapper = dailyDataMapper;
        mHourlyDataMapper = hourlyDataMapper;
    }

    @Override
    public List<Day> getWeekly() {
        // Query params map
        Map<String, String> options = new HashMap<>();
        options.put("lang", "ru");
        options.put("units", "si");
        options.put("exclude", "currently,hourly,minutely,alerts,flags");

        try {
            Response<Forecast> response = mForecastService.getForecasts(KEY, COORDS, options)
                    .execute();
            Forecast forecast = response.body();

            return mDailyDataMapper.transform(forecast.getDaily().getData());
        } catch (IOException e) {
            // If there is no internet connection
            Log.e(TAG, "getWeekly: " + e.getMessage());

            return Collections.emptyList();
        }
    }

    @Override
    public List<Hour> getDaily(long time) {
        // Query params map
        Map<String, String> options = new HashMap<>();
        options.put("lang", "ru");
        options.put("units", "si");
        options.put("exclude", "currently,daily,minutely,alerts,flags");

        String path = COORDS + "," + time;
        try {
            Response<Forecast> response = mForecastService.getForecasts(KEY, path, options)
                    .execute();
            Forecast forecast = response.body();

            return mHourlyDataMapper.transform(forecast.getHourly().getData());
        } catch (IOException e) {
            // If there is no internet connection
            Log.e(TAG, "getDaily: " + e.getMessage());

            return Collections.emptyList();
        }
    }
}
