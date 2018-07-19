package ru.gcsales.seminar15.presentation.main;

import java.util.ArrayList;
import java.util.List;

import ru.gcsales.seminar15.data.ForecastDataRepository;
import ru.gcsales.seminar15.domain.executor.MainThread;
import ru.gcsales.seminar15.domain.executor.ThreadExecutor;
import ru.gcsales.seminar15.domain.interactor.Callback;
import ru.gcsales.seminar15.domain.interactor.GetWeeklyForecast;
import ru.gcsales.seminar15.domain.interactor.UseCase;
import ru.gcsales.seminar15.domain.model.Day;
import ru.gcsales.seminar15.domain.repository.ForecastRepository;
import ru.gcsales.seminar15.presentation.base.BasePresenter;
import ru.gcsales.seminar15.presentation.model.DayModel;
import ru.gcsales.seminar15.threading.MainThreadImpl;

public class MainPresenter extends BasePresenter<MainMvpView> implements Callback<List<Day>> {

    private ForecastRepository mRepository;
    private UseCase mUseCase;
    private MainThread mMainThread;

    public MainPresenter() {
        // TODO: make singleton
        mRepository = new ForecastDataRepository();
        mMainThread = MainThreadImpl.getInstance();
        mUseCase = new GetWeeklyForecast(ThreadExecutor.getInstance(), mMainThread, mRepository, this);
    }

    public void loadData() {
        getMvpView().showProgress();
        mUseCase.execute();
    }

    @Override
    public void onSuccess(final List<Day> data) {
        getMvpView().hideProgress();

        List<DayModel> dayModels = new ArrayList<>();

        for (Day day : data) {
            DayModel dayModel = new DayModel();
            dayModel.setDate(day.getDate().toString());
            dayModel.setDayTemperature(day.getDayTemperature());
            dayModel.setNightTemperature(day.getNightTemperature());
            dayModel.setSummary(day.getSummary());
            dayModel.setIcon(day.getIcon());
            dayModels.add(dayModel);
        }

        getMvpView().showData(dayModels);
    }
}
