package ru.gcsales.seminar15.domain.interactor;

import ru.gcsales.seminar15.domain.executor.Executor;
import ru.gcsales.seminar15.domain.executor.MainThread;

public class GetDailyForecast extends UseCase {

    public GetDailyForecast(Executor executor, MainThread mainThread) {
        super(executor, mainThread);
    }

    @Override
    public void run() {

    }
}
