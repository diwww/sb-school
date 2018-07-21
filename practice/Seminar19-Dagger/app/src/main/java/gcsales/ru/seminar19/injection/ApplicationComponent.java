package gcsales.ru.seminar19.injection;

import javax.inject.Singleton;

import dagger.Component;
import gcsales.ru.seminar19.presentation.week.WeekActivity;

@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {
    void inject(WeekActivity activity);
}
