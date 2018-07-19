package ru.gcsales.seminar15.domain.interactor;

import java.util.List;

import ru.gcsales.seminar15.domain.model.Day;
import ru.gcsales.seminar15.domain.executor.Executor;
import ru.gcsales.seminar15.domain.executor.MainThread;
import ru.gcsales.seminar15.domain.repository.ForecastRepository;

public class GetWeeklyForecast extends UseCase {

    private Callback<List<Day>> mCallBack;
    private ForecastRepository mRepository;

    public GetWeeklyForecast(Executor executor, MainThread mainThread,
                             ForecastRepository repository, Callback<List<Day>> callBack) {
        super(executor, mainThread);
        mCallBack = callBack;
        mRepository = repository;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        final List<Day> data = mRepository.getWeekly();
        getMainThread().post(new Runnable() {
            @Override
            public void run() {
                mCallBack.onSuccess(data);
            }
        });
    }
}
