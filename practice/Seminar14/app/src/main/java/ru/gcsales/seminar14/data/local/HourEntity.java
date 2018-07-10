package ru.gcsales.seminar14.data.local;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "hour")
public class HourEntity {


    @PrimaryKey
    @ColumnInfo(name = "time_stamp")
    private long mTimeStamp;
    @ColumnInfo(name = "temp")
    private double mTemp;
    @ColumnInfo(name = "summary")
    private String mSummary;
    @ColumnInfo(name = "humidity")
    private double mHumidity;
    @ColumnInfo(name = "wind_speed")
    private double mWindSpeed;
    @ColumnInfo(name = "pressure")
    private double mPressure;
    @ColumnInfo(name = "icon_name")
    private String mIcon;

    public HourEntity() {
    }

    public HourEntity(long timeStamp, double temp, String summary, double humidity, double windSpeed, double pressure, String icon) {
        mTimeStamp = timeStamp;
        mTemp = temp;
        mSummary = summary;
        mHumidity = humidity;
        mWindSpeed = windSpeed;
        mPressure = pressure;
        mIcon = icon;
    }

    public long getTimeStamp() {
        return mTimeStamp;
    }

    public void setTimeStamp(long timeStamp) {
        mTimeStamp = timeStamp;
    }

    public double getTemp() {
        return mTemp;
    }

    public void setTemp(double temp) {
        mTemp = temp;
    }

    public String getSummary() {
        return mSummary;
    }

    public void setSummary(String summary) {
        mSummary = summary;
    }

    public double getHumidity() {
        return mHumidity;
    }

    public void setHumidity(double humidity) {
        mHumidity = humidity;
    }

    public double getWindSpeed() {
        return mWindSpeed;
    }

    public void setWindSpeed(double windSpeed) {
        mWindSpeed = windSpeed;
    }

    public double getPressure() {
        return mPressure;
    }

    public void setPressure(double pressure) {
        mPressure = pressure;
    }

    public String getIcon() {
        return mIcon;
    }

    public void setIcon(String icon) {
        mIcon = icon;
    }
}
