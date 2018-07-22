package gcsales.ru.seminar19.injection;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import gcsales.ru.seminar19.data.ForecastService;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class NetworkModule {

    private static final String BASE_URL = "https://api.darksky.net/";

    @Provides
    @Singleton
    public ForecastService provideForecastService() {
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
