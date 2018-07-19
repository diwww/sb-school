package ru.gcsales.seminar15.presentation.model;


public class DayModel {

    private double mDayTemperature;
    private double mNightTemperature;
    private String mIcon;
    private String mDate;
    private String mSummary;

    public DayModel() {
    }

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

    public String getIcon() {
        return mIcon;
    }

    public void setIcon(String icon) {
        mIcon = icon;
    }

    public String getDate() {
        return mDate;
    }

    public void setDate(String date) {
        mDate = date;
    }

    public String getSummary() {
        return mSummary;
    }

    public void setSummary(String summary) {
        mSummary = summary;
    }
}
