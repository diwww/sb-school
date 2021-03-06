package gcsales.ru.seminar19.presentation.week;

import java.util.List;
import java.util.concurrent.ExecutorService;

import javax.inject.Inject;
import javax.inject.Singleton;

import gcsales.ru.seminar19.domain.interactor.Callback;
import gcsales.ru.seminar19.domain.interactor.GetWeeklyForecast;
import gcsales.ru.seminar19.domain.interactor.UseCase;
import gcsales.ru.seminar19.domain.model.Day;
import gcsales.ru.seminar19.domain.repository.Repository;
import gcsales.ru.seminar19.presentation.UIThread;
import gcsales.ru.seminar19.presentation.base.BasePresenter;
import gcsales.ru.seminar19.presentation.mapper.DayModelDataMapper;
import gcsales.ru.seminar19.presentation.model.DayModel;

/**
 * Презентер для погоды на неделю
 */
@Singleton
public class WeekPresenter extends BasePresenter<WeekMvpView> implements Callback<List<Day>> {

    private Repository mRepository;
    private ExecutorService mExecutorService;
    private UIThread mUIThread;
    private DayModelDataMapper mDataMapper;

    @Inject
    public WeekPresenter(Repository repository, ExecutorService executorService,
                         UIThread uiThread, DayModelDataMapper dataMapper) {
        mRepository = repository;
        mExecutorService = executorService;
        mUIThread = uiThread;
        mDataMapper = dataMapper;
    }

    public void getData() {
        checkViewAttached();
        getMvpView().showProgress();
        final UseCase useCase = new GetWeeklyForecast(mRepository, this);
        // Execute on a separate thread
        mExecutorService.execute(new Runnable() {
            @Override
            public void run() {
                useCase.execute();
            }
        });
    }

    @Override
    public void onDataLoaded(List<Day> data) {
        final List<DayModel> dayModelList = mDataMapper.transform(data);
        // Execute on the UI thread
        mUIThread.post(new Runnable() {
            @Override
            public void run() {
                getMvpView().hideProgress();
                getMvpView().showData(dayModelList);
            }
        });
    }
}
