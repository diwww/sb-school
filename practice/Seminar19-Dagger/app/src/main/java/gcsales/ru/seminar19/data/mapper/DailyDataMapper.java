package gcsales.ru.seminar19.data.mapper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import gcsales.ru.seminar19.data.model.DailyData;
import gcsales.ru.seminar19.domain.model.Day;

public class DailyDataMapper {

    public Day transform(DailyData dailyData) {
        Day day = new Day();
        day.setSummary(dailyData.getSummary());
        day.setNightTemperature(dailyData.getTemperatureLow());
        day.setDayTemperature(dailyData.getApparentTemperatureHigh());
        day.setIcon(dailyData.getIcon());
        day.setDate(new Date(TimeUnit.SECONDS.toMillis(dailyData.getTime())));
        return day;
    }

    public List<Day> transform(List<DailyData> dailyDataList) {
        List<Day> dayList;

        if (dailyDataList != null && !dailyDataList.isEmpty()) {
            dayList = new ArrayList<>();
            for (DailyData dailyData : dailyDataList) {
                dayList.add(transform(dailyData));
            }
        } else {
            dayList = Collections.emptyList();
        }

        return dayList;
    }
}
