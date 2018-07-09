package ru.gcsales.seminar14.api;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import ru.gcsales.seminar14.webmodels.Forecast;

/**
 * Class for convenient access to server's API
 */
public class ApiMapper {

    /**
     * Access token
     */
    public static final String KEY = "eb42cb6a8ffa70963fcc15e417c08922";
    /**
     * Moscow coordinates
     */
    public static final String COORDS = "55.7549,37.6219";

    private RetrofitHelper mRetrofitHelper;

    /**
     * Constructor
     *
     * @param retrofitHelper {@link RetrofitHelper} object
     */
    public ApiMapper(RetrofitHelper retrofitHelper) {
        mRetrofitHelper = retrofitHelper;
    }

    /**
     * Gets week forecasts.
     * This method should be run on a separate thread.
     *
     * @return {@link Forecast} object
     * @throws IOException if network error occurs
     */
    public Forecast getWeekForecastSync() throws IOException {
        // Query params map
        Map<String, String> options = new HashMap<>();
        options.put("exclude", "currently,minutely,hourly,alerts,flags");
        options.put("lang", "ru");
        options.put("units", "si");

        return mRetrofitHelper.getService()
                .getForecasts(KEY, COORDS, options)
                .execute()
                .body();
    }

    public Forecast getDayForecastSync(long time) throws IOException {
        // Query params map
        Map<String, String> options = new HashMap<>();
        options.put("exclude", "currently,minutely,daily,alerts,flags");
        options.put("lang", "ru");
        options.put("units", "si");

        String path = COORDS + "," + time;

        return mRetrofitHelper.getService()
                .getForecasts(KEY, path, options)
                .execute()
                .body();
    }
}
