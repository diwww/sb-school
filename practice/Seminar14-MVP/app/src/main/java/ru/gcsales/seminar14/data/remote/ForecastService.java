package ru.gcsales.seminar14.data.remote;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Map;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;
import ru.gcsales.seminar14.data.model.Forecast;

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

    class Creator {
        private static final String BASE_URL = "https://api.darksky.net/";

        public static ForecastService newForecastService() {
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
}
