package gcsales.ru.seminar19.presentation.day;

import java.util.List;

import gcsales.ru.seminar19.presentation.base.MvpView;
import gcsales.ru.seminar19.presentation.model.HourModel;

public interface DayMvpView extends MvpView {

    void showData(List<HourModel> data);

}
