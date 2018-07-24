package gcsales.ru.seminar20.domain.interactor;


import java.util.List;

import gcsales.ru.seminar20.domain.model.Day;
import gcsales.ru.seminar20.domain.repository.Repository;
import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.schedulers.Schedulers;

/**
 * Интерактор для получения погоды на неделю
 */
public class GetWeeklyForecast extends UseCase {

    private Repository mRepository;

    public GetWeeklyForecast(Repository repository) {
        mRepository = repository;
    }

    @Override
    public void execute() {
        // stub
    }

    public Observable<List<Day>> getData() {
        return mRepository.getWeekly()
                .observeOn(Schedulers.computation());
    }
}
