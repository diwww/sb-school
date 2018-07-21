package gcsales.ru.seminar19.injection;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import gcsales.ru.seminar19.domain.model.Day;
import gcsales.ru.seminar19.domain.model.Hour;
import gcsales.ru.seminar19.domain.repository.Repository;
import gcsales.ru.seminar19.presentation.UIThread;
import gcsales.ru.seminar19.presentation.mapper.DayModelDataMapper;

@Module
public class ApplicationModule {

    @Provides
    @Singleton
    public Repository provideRepository() {
        return new Repository() {
            @Override
            public List<Day> getWeekly() {
                List<Day> list = new ArrayList<>();

                Day day = new Day();
                day.setDate(new Date());
                day.setDayTemperature(33.33);
                day.setNightTemperature(33.33);
                day.setSummary("Test");

                list.add(day);
                return list;
            }

            @Override
            public List<Hour> getDaily() {
                return null;
            }
        };
    }

    @Provides
    @Singleton
    public DayModelDataMapper provideDayModelDataMapper() {
        return new DayModelDataMapper();
    }

    @Provides
    @Singleton
    public UIThread provideUIThread() {
        return new UIThread();
    }

    @Provides
    @Singleton
    public ExecutorService provideExecutorService() {
        return Executors.newFixedThreadPool(2);
    }
}
