package gcsales.ru.seminar19;

import android.app.Application;

import gcsales.ru.seminar19.injection.DaggerRepositoryComponent;
import gcsales.ru.seminar19.injection.RepositoryComponent;

public class WeatherApplication extends Application {

    private void init() {
        RepositoryComponent repositoryComponent = DaggerRepositoryComponent.builder()
                .build();
    }
}
