package gcsales.ru.seminar20.injection;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import gcsales.ru.seminar20.data.ForecastDataRepository;
import gcsales.ru.seminar20.data.ForecastService;
import gcsales.ru.seminar20.data.mapper.DailyDataMapper;
import gcsales.ru.seminar20.data.mapper.HourlyDataMapper;
import gcsales.ru.seminar20.domain.repository.Repository;

@Module
public class DataRepositoryModule {
    @Provides
    @Singleton
    public Repository provideRepository(ForecastService service, DailyDataMapper dailyDataMapper,
                                        HourlyDataMapper hourlyDataMapper) {
        return new ForecastDataRepository(service, dailyDataMapper, hourlyDataMapper);
    }
}
