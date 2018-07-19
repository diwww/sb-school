package ru.gcsales.seminar14.data.local;

import ru.gcsales.seminar14.data.model.DailyData;
import ru.gcsales.seminar14.data.model.HourlyData;

public class DbUtils {

    private DbUtils() {
    }

    public static DayEntity modelToEntity(DailyData d) {
        DayEntity dayEntity = new DayEntity();
        dayEntity.setTimeStamp(d.getTime());
        dayEntity.setHighTemp(d.getTemperatureHigh());
        dayEntity.setLowTemp(d.getTemperatureLow());
        dayEntity.setIconName(d.getIcon());
        dayEntity.setSummary(d.getSummary());
        return dayEntity;
    }

    public static HourEntity modelToEntity(HourlyData d) {
        HourEntity hourEntity = new HourEntity();
        hourEntity.setHumidity(d.getHumidity());
        hourEntity.setPressure(d.getPressure());
        hourEntity.setIcon(d.getIcon());
        hourEntity.setSummary(d.getSummary());
        hourEntity.setTimeStamp(d.getTime());
        hourEntity.setWindSpeed(d.getWindSpeed());
        hourEntity.setTemp(d.getTemperature());
        return hourEntity;
    }

    public static DailyData entityToModel(DayEntity d) {
        DailyData data = new DailyData();
        data.setTemperatureHigh(d.getHighTemp());
        data.setTemperatureLow(d.getLowTemp());
        data.setTime(d.getTimeStamp());
        data.setIcon(d.getIconName());
        data.setSummary(d.getSummary());
        return data;
    }

    public static HourlyData entityToModel(HourEntity d) {
        HourlyData data = new HourlyData();
        data.setTime(d.getTimeStamp());
        data.setHumidity(d.getHumidity());
        data.setIcon(d.getIcon());
        data.setPressure(d.getPressure());
        data.setSummary(d.getSummary());
        data.setTemperature(d.getTemp());
        data.setWindSpeed(d.getWindSpeed());
        return data;
    }
}
