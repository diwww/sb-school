package ru.gcsales.seminar14.models;

import java.util.Date;

public class HourItem implements BaseItem {

    public static final int HOUR_ITEM_TYPE = 2;

    private double temp;
    private Date time;
    private String summary;
    private double humidity;
    private double windSpeed;
    private double pressure;
    private String icon;

    public HourItem() {
    }


    public HourItem setTemp(double temp) {
        this.temp = temp;
        return this;
    }

    public HourItem setTime(Date time) {
        this.time = time;
        return this;
    }

    public HourItem setSummary(String summary) {
        this.summary = summary;
        return this;
    }

    public HourItem setHumidity(double humidity) {
        this.humidity = humidity;
        return this;
    }

    public HourItem setWindSpeed(double windSpeed) {
        this.windSpeed = windSpeed;
        return this;
    }

    public HourItem setPressure(double pressure) {
        this.pressure = pressure;
        return this;
    }

    public HourItem setIcon(String icon) {
        this.icon = icon;
        return this;
    }

    public double getTemp() {
        return temp;
    }

    public Date getTime() {
        return time;
    }

    public String getSummary() {
        return summary;
    }

    public double getHumidity() {
        return humidity;
    }

    public double getWindSpeed() {
        return windSpeed;
    }

    public double getPressure() {
        return pressure;
    }

    public String getIcon() {
        return icon;
    }

    @Override
    public int getType() {
        return HOUR_ITEM_TYPE;
    }
}
