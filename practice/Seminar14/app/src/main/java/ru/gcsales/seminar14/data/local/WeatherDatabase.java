package ru.gcsales.seminar14.data.local;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

@Database(entities = {DayEntity.class, HourEntity.class}, version = 1)
public abstract class WeatherDatabase extends RoomDatabase {

    public abstract DayDao getDayDao();

    public abstract HourDao getHourDao();
}
