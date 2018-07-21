package gcsales.ru.seminar19;

import android.app.Application;

import gcsales.ru.seminar19.injection.DaggerApplicationComponent;
import gcsales.ru.seminar19.injection.ApplicationComponent;
import gcsales.ru.seminar19.injection.ApplicationModule;

public class WeatherApplication extends Application {

    private ApplicationComponent mApplicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        mApplicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule())
                .build();
    }

    public ApplicationComponent getApplicationComponent() {
        return mApplicationComponent;
    }
}
