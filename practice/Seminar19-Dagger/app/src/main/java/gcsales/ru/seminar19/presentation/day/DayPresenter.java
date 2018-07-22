package gcsales.ru.seminar19.presentation.day;


import java.util.List;
import java.util.concurrent.ExecutorService;

import javax.inject.Inject;
import javax.inject.Singleton;

import gcsales.ru.seminar19.domain.interactor.Callback;
import gcsales.ru.seminar19.domain.interactor.GetDailyForecast;
import gcsales.ru.seminar19.domain.interactor.UseCase;
import gcsales.ru.seminar19.domain.model.Hour;
import gcsales.ru.seminar19.domain.repository.Repository;
import gcsales.ru.seminar19.presentation.UIThread;
import gcsales.ru.seminar19.presentation.base.BasePresenter;
import gcsales.ru.seminar19.presentation.mapper.HourModelDataMapper;
import gcsales.ru.seminar19.presentation.model.HourModel;

/**
 * Презентер для погоды на день
 */
@Singleton
public class DayPresenter extends BasePresenter<DayMvpView> implements Callback<List<Hour>> {

    private Repository mRepository;
    private ExecutorService mExecutorService;
    private UIThread mUIThread;
    private HourModelDataMapper mDataMapper;

    @Inject
    public DayPresenter(Repository repository, ExecutorService executorService,
                        UIThread UIThread, HourModelDataMapper dataMapper) {
        mRepository = repository;
        mExecutorService = executorService;
        mUIThread = UIThread;
        mDataMapper = dataMapper;
    }

    public void getData(long time) {
        checkViewAttached();
        getMvpView().showProgress();
        final UseCase useCase = new GetDailyForecast(time, mRepository, this);
        // Execute on a separate thread
        mExecutorService.execute(new Runnable() {
            @Override
            public void run() {
                useCase.execute();
            }
        });
    }

    @Override
    public void onDataLoaded(List<Hour> data) {
        final List<HourModel> hourModelList = mDataMapper.transform(data);
        // Execute on the UI thread
        mUIThread.post(new Runnable() {
            @Override
            public void run() {
                getMvpView().hideProgress();
                getMvpView().showData(hourModelList);
            }
        });
    }
}
