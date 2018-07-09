package ru.gcsales.seminar14.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Helper class for Retrofit
 *
 */
public class RetrofitHelper {
    /**
     * Base URL of a resource
     */
    private static final String BASE_URL = "https://api.darksky.net/";

    /**
     * Gets a new instance of {@link ForecastService}
     *
     * @return an instance of {@link ForecastService}
     */
    public ForecastService getService() {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        return retrofit.create(ForecastService.class);
    }
}
