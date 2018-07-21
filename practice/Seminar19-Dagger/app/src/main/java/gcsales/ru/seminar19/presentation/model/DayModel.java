package gcsales.ru.seminar19.presentation.model;

import java.util.Date;

/**
 * Класс представления погоды за день в presentation слое
 */
public class DayModel {

    private double mDayTemperature;
    private double mNightTemperature;
    private String mSummary;
    private String mIcon;
    private Date mDate;

    public double getDayTemperature() {
        return mDayTemperature;
    }

    public void setDayTemperature(double dayTemperature) {
        mDayTemperature = dayTemperature;
    }

    public double getNightTemperature() {
        return mNightTemperature;
    }

    public void setNightTemperature(double nightTemperature) {
        mNightTemperature = nightTemperature;
    }

    public String getSummary() {
        return mSummary;
    }

    public void setSummary(String summary) {
        mSummary = summary;
    }

    public String getIcon() {
        return mIcon;
    }

    public void setIcon(String icon) {
        mIcon = icon;
    }

    public Date getDate() {
        return mDate;
    }

    public void setDate(Date date) {

    }
}
