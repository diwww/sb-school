package gcsales.ru.seminar20.presentation.day;

import java.util.List;

import gcsales.ru.seminar20.presentation.base.MvpView;
import gcsales.ru.seminar20.presentation.model.HourModel;

public interface DayMvpView extends MvpView {

    void showData(List<HourModel> data);

}
