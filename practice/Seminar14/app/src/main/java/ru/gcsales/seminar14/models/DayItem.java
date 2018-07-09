package ru.gcsales.seminar14.models;

import java.util.Date;

public class DayItem implements BaseItem {

    public static final int DAY_ITEM_TYPE = 1;

    private double mMaxTemp;
    private double mMinTemp;
    private Date mDate;
    private String mIconName;
    private String mSummary;

    public DayItem(double maxTemp, double minTemp, Date date, String iconName, String summary) {
        mMaxTemp = maxTemp;
        mMinTemp = minTemp;
        mDate = date;
        mIconName = iconName;
        mSummary = summary;
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

    public Date getDate() {
        return mDate;
    }

    public void setDate(Date date) {
        mDate = date;
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

    @Override
    public int getType() {
        return DAY_ITEM_TYPE;
    }
}
