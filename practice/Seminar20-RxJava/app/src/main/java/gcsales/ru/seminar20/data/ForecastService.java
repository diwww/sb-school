package gcsales.ru.seminar20.data;

import java.util.Map;

import gcsales.ru.seminar20.data.model.Forecast;
import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;

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
    Observable<Forecast> getForecasts(@Path("key") String key,
                                      @Path("coordsTime") String coordsTime,
                                      @QueryMap Map<String, String> options);

}

