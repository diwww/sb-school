package ru.gcsales.seminar14.data.local;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "day")
public class DayEntity {

    @PrimaryKey
    @ColumnInfo(name = "time_stamp")
    private long mTimeStamp;
    @ColumnInfo(name = "max_temp")
    private double mMaxTemp;
    @ColumnInfo(name = "min_temp")
    private double mMinTemp;
    @ColumnInfo(name = "icon_name")
    private String mIconName;
    @ColumnInfo(name = "summary")
    private String mSummary;

    public DayEntity(long timeStamp, double maxTemp, double minTemp, String iconName, String summary) {
        mTimeStamp = timeStamp;
        mMaxTemp = maxTemp;
        mMinTemp = minTemp;
        mIconName = iconName;
        mSummary = summary;
    }

    public long getTimeStamp() {
        return mTimeStamp;
    }

    public void setTimeStamp(long timeStamp) {
        mTimeStamp = timeStamp;
    }

    public double getMaxTemp() {
        return mMaxTemp;
    }

    public void setMaxTemp(double maxTemp) {
        mMaxTemp = maxTemp;
    }

    public double getMinTemp() {
        return mMinTemp;
    }

    public void setMinTemp(double minTemp) {
        mMinTemp = minTemp;
    }

    public String getIconName() {
        return mIconName;
    }

    public void setIconName(String iconName) {
        mIconName = iconName;
    }

    public String getSummary() {
        return mSummary;
    }

    public void setSummary(String summary) {
        mSummary = summary;
    }
}
