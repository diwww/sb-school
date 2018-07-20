package gcsales.ru.seminar19.domain.interactor;


import java.util.List;

import gcsales.ru.seminar19.domain.model.Hour;
import gcsales.ru.seminar19.domain.repository.Repository;

/**
 * Интерактор для получения погоды на день
 */
public class GetDailyForecast extends UseCase {

    private Repository mRepository;
    private Callback<List<Hour>> mCallback;

    public GetDailyForecast(Repository repository, Callback<List<Hour>> callback) {
        mRepository = repository;
        mCallback = callback;
    }

    @Override
    public void execute() {
        List<Hour> data = mRepository.getDaily();
        mCallback.onDataLoaded(data);
    }
}
