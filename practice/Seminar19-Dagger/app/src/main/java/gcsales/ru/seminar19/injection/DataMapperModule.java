package gcsales.ru.seminar19.injection;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import gcsales.ru.seminar19.data.mapper.DailyDataMapper;
import gcsales.ru.seminar19.data.mapper.HourlyDataMapper;
import gcsales.ru.seminar19.presentation.mapper.DayModelDataMapper;
import gcsales.ru.seminar19.presentation.mapper.HourModelDataMapper;

@Module
public class DataMapperModule {

    @Provides
    @Singleton
    public DayModelDataMapper provideDayModelDataMapper() {
        return new DayModelDataMapper();
    }

    @Provides
    @Singleton
    public HourModelDataMapper provideHourModelDataMapper() {
        return new HourModelDataMapper();
    }

    @Provides
    @Singleton
    public DailyDataMapper provideDailyDataMapper() {
        return new DailyDataMapper();
    }

    @Provides
    @Singleton
    public HourlyDataMapper provideHourlyDataMapper() {
        return new HourlyDataMapper();
    }
}
