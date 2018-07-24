package gcsales.ru.seminar20.domain.interactor;


import java.util.List;

import gcsales.ru.seminar20.domain.model.Hour;
import gcsales.ru.seminar20.domain.repository.Repository;
import io.reactivex.Observable;

/**
 * Интерактор для получения погоды на день
 */
public class GetDailyForecast extends UseCase {

    private long mTime;
    private Repository mRepository;

    public GetDailyForecast(long time, Repository repository) {
        mTime = time;
        mRepository = repository;
    }

    @Override
    public void execute() {
        // stub
    }

    public Observable<List<Hour>> getData() {
        return mRepository.getDaily(mTime);
    }
}
