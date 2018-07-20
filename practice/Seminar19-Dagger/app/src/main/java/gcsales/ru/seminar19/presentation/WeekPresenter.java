package gcsales.ru.seminar19.presentation;

import javax.inject.Inject;

import gcsales.ru.seminar19.domain.repository.Repository;

/**
 * Презентер для погоды на неделю
 */
public class WeekPresenter {

    private Repository mRepository;

    @Inject
    public WeekPresenter(Repository repository) {
        mRepository = repository;
    }
}
