package ru.gcsales.seminar13.database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.database.Cursor;

import java.util.List;

@Dao
public interface HourDao {

    @Query("SELECT * FROM hour where time_stamp >= :timeLower AND time_stamp < :timeUpper")
    List<HourEntity> getHours(long timeLower, long timeUpper);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insert(HourEntity hourEntity);
}
