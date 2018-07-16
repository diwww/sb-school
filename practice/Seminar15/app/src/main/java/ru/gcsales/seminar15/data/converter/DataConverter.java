package ru.gcsales.seminar15.data.converter;

import ru.gcsales.seminar15.data.entity.DayEntity;
import ru.gcsales.seminar15.domain.model.Day;

public class DataConverter {

    private DataConverter() {
    }

    public static Day entityToModel(DayEntity entity) {
        Day day = new Day();

        return day;
    }
}
