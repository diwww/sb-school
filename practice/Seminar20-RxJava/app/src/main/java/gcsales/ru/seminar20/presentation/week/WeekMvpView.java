package gcsales.ru.seminar20.presentation.week;

import java.util.List;

import gcsales.ru.seminar20.presentation.base.MvpView;
import gcsales.ru.seminar20.presentation.model.DayModel;

public interface WeekMvpView extends MvpView {

    void showData(List<DayModel> data);

}
