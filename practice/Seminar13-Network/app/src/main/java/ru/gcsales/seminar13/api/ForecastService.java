package ru.gcsales.seminar13.api;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;
import ru.gcsales.seminar13.webmodels.Forecast;

/**
 * Forecast service
 */
public interface ForecastService {

    /**
     * Makes a request to the server
     *
     * @param key        access token
     * @param coordsTime coordinates of a place
     *                   and time (optionally, if a specific day forecast is needed)
     * @param options    query params map
     * @return response object
     */
    @GET("forecast/{key}/{coordsTime}")
    Call<Forecast> getForecasts(@Path("key") String key,
                                @Path("coordsTime") String coordsTime,
                                @QueryMap Map<String, String> options);
}
