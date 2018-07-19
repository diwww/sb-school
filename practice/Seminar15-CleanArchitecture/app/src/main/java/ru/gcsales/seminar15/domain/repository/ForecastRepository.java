package ru.gcsales.seminar15.domain.repository;

import java.util.List;

import ru.gcsales.seminar15.domain.model.Day;
import ru.gcsales.seminar15.domain.model.Hour;

public interface ForecastRepository {

    List<Day> getWeekly();

    List<Hour> getDaily();
}
