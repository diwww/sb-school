package ru.gcsales.seminar15.data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import ru.gcsales.seminar15.domain.model.Day;
import ru.gcsales.seminar15.domain.model.Hour;
import ru.gcsales.seminar15.domain.repository.ForecastRepository;

public class ForecastDataRepository implements ForecastRepository {

    @Override
    public List<Day> getWeekly() {
        List<Day> days = new ArrayList<>();
        Day day = new Day();
        day.setSummary("Summary");
        day.setDate(new Date());
        day.setDayTemperature(44.0);
        day.setNightTemperature(39.2);
        days.add(day);
        return days;
    }

    @Override
    public List<Hour> getDaily() {
        return null;
    }

}
