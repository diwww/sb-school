package ru.gcsales.seminar14.ui.day;

import java.util.List;

import ru.gcsales.seminar14.data.model.HourlyData;
import ru.gcsales.seminar14.ui.base.MvpView;

public interface DayMvpView extends MvpView {

    void showData(List<HourlyData> data);

    void showProgress();

    void hideProgress();
}
