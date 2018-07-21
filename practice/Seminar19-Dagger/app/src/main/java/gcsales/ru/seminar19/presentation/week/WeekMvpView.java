package gcsales.ru.seminar19.presentation.week;

import java.util.List;

import gcsales.ru.seminar19.presentation.base.MvpView;
import gcsales.ru.seminar19.presentation.model.DayModel;

public interface WeekMvpView extends MvpView {

    void showData(List<DayModel> data);
}
