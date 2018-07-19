package ru.gcsales.seminar15.presentation.main;

import java.util.List;

import ru.gcsales.seminar15.presentation.base.MvpView;
import ru.gcsales.seminar15.presentation.model.DayModel;

public interface MainMvpView extends MvpView {

    void showProgress();

    void hideProgress();

    void showData(List<DayModel> data);
}
