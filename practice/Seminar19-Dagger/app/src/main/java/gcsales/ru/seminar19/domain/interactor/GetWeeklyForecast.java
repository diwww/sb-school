package gcsales.ru.seminar19.domain.interactor;


import java.util.List;

import gcsales.ru.seminar19.domain.model.Day;
import gcsales.ru.seminar19.domain.repository.Repository;

/**
 * Интерактор для получения погоды на неделю
 */
public class GetWeeklyForecast extends UseCase {

    private Repository mRepository;
    private Callback<List<Day>> mCallback;

    public GetWeeklyForecast(Repository repository, Callback<List<Day>> callback) {
        mRepository = repository;
        mCallback = callback;
    }

    @Override
    public void execute() {
        List<Day> data = mRepository.getWeekly();
        // FIXME: remove
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        mCallback.onDataLoaded(data);
    }
}
