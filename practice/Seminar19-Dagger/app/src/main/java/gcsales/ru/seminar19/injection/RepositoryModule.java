package gcsales.ru.seminar19.injection;

import java.util.List;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import gcsales.ru.seminar19.domain.model.Day;
import gcsales.ru.seminar19.domain.model.Hour;
import gcsales.ru.seminar19.domain.repository.Repository;

@Module
public class RepositoryModule {

    @Provides
    @Singleton
    public Repository provideRepository() {
        return new Repository() {
            @Override
            public List<Day> getWeekly() {
                return null;
            }

            @Override
            public List<Hour> getDaily() {
                return null;
            }
        };
    }
}
