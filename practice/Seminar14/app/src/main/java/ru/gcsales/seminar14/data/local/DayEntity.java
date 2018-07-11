package ru.gcsales.seminar14.data.local;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "day")
public class DayEntity {

    @PrimaryKey
    @ColumnInfo(name = "time_stamp")
    private long mTimeStamp;
    @ColumnInfo(name = "high_temp")
    private double mHighTemp;
    @ColumnInfo(name = "low_temp")
    private double mLowTemp;
    @ColumnInfo(name = "icon_name")
    private String mIconName;
    @ColumnInfo(name = "summary")
    private String mSummary;

    public DayEntity() {
    }

    @Ignore
    public DayEntity(long timeStamp, double highTemp, double lowTemp, String iconName, String summary) {
        mTimeStamp = timeStamp;
        mHighTemp = highTemp;
        mLowTemp = lowTemp;
        mIconName = iconName;
        mSummary = summary;
    }

    public long getTimeStamp() {
        return mTimeStamp;
    }

    public void setTimeStamp(long timeStamp) {
        mTimeStamp = timeStamp;
    }

    public double getHighTemp() {
        return mHighTemp;
    }

    public void setHighTemp(double highTemp) {
        mHighTemp = highTemp;
    }

    public double getLowTemp() {
        return mLowTemp;
    }

    public void setLowTemp(double lowTemp) {
        mLowTemp = lowTemp;
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
