package gcsales.ru.seminar19.injection;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import gcsales.ru.seminar19.domain.model.Day;
import gcsales.ru.seminar19.domain.model.Hour;
import gcsales.ru.seminar19.domain.repository.Repository;

@Module
public class RepositoryMoudle {

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
                list.add(day);
                list.add(day);
                list.add(day);
                list.add(day);
                list.add(day);
                list.add(day);
                list.add(day);
                list.add(day);
                list.add(day);
                return list;
            }

            @Override
            public List<Hour> getDaily(long time) {
                List<Hour> list = new ArrayList<>();

                Hour hour = new Hour();
                hour.setDate(new Date());
                hour.setHumidity(24);
                hour.setPressure(25);
                hour.setSummary("Test");
                hour.setWindSpeed(1000);
                hour.setTemperature(33.33);
                list.add(hour);
                return list;
            }
        };
    }
}
