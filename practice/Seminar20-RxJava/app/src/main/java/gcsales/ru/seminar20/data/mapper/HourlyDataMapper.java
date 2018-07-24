package gcsales.ru.seminar20.data.mapper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import gcsales.ru.seminar20.data.model.HourlyData;
import gcsales.ru.seminar20.domain.model.Hour;

public class HourlyDataMapper {

    public Hour transform(HourlyData hourlyData) {
        Hour hour = new Hour();
        hour.setTemperature(hourlyData.getTemperature());
        hour.setWindSpeed(hourlyData.getWindSpeed());
        hour.setSummary(hourlyData.getSummary());
        hour.setPressure(hourlyData.getPressure());
        hour.setHumidity(hourlyData.getHumidity());
        hour.setDate(new Date(TimeUnit.SECONDS.toMillis(hourlyData.getTime())));
        hour.setIcon(hourlyData.getIcon());
        return hour;
    }

    public List<Hour> transform(List<HourlyData> hourlyDataList) {
        List<Hour> hourList;

        if (hourlyDataList != null && !hourlyDataList.isEmpty()) {
            hourList = new ArrayList<>();
            for (HourlyData hourlyData : hourlyDataList) {
                hourList.add(transform(hourlyData));
            }
        } else {
            hourList = Collections.emptyList();
        }

        return hourList;
    }
}
