package gcsales.ru.seminar20;

import android.app.Application;

import gcsales.ru.seminar20.injection.DaggerApplicationComponent;
import gcsales.ru.seminar20.injection.ApplicationComponent;
import gcsales.ru.seminar20.injection.NetworkModule;

public class WeatherApplication extends Application {

    private ApplicationComponent mApplicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        mApplicationComponent = DaggerApplicationComponent.create();
    }

    public ApplicationComponent getApplicationComponent() {
        return mApplicationComponent;
    }
}
