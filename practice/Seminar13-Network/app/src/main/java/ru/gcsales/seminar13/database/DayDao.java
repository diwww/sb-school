package ru.gcsales.seminar13.database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.database.Cursor;

import java.util.List;

@Dao
public interface DayDao {

    @Query("SELECT * FROM day WHERE time_stamp >= :timeStamp")
    List<DayEntity> getDays(long timeStamp);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insert(DayEntity dayEntity);
}
