package gcsales.ru.seminar20.injection;

import javax.inject.Singleton;

import dagger.Component;
import gcsales.ru.seminar20.presentation.day.DayActivity;
import gcsales.ru.seminar20.presentation.week.WeekActivity;

@Singleton
@Component(modules = {NetworkModule.class, ThreadingModule.class, DataMapperModule.class,
        DataRepositoryModule.class})
public interface ApplicationComponent {
    void inject(WeekActivity activity);

    void inject(DayActivity activity);
}
