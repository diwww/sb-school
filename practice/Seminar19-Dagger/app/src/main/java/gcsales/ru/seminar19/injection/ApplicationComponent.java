package gcsales.ru.seminar19.injection;

import javax.inject.Singleton;

import dagger.Component;
import gcsales.ru.seminar19.presentation.day.DayActivity;
import gcsales.ru.seminar19.presentation.week.WeekActivity;

@Singleton
@Component(modules = {RepositoryMoudle.class, ThreadingModule.class, DataMapperModule.class})
public interface ApplicationComponent {
    void inject(WeekActivity activity);

    void inject(DayActivity activity);
}
