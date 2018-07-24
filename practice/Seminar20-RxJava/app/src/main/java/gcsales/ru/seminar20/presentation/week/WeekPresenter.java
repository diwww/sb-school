package gcsales.ru.seminar20.presentation.week;

import java.util.List;
import java.util.concurrent.ExecutorService;

import javax.inject.Inject;
import javax.inject.Singleton;

import gcsales.ru.seminar20.domain.interactor.GetWeeklyForecast;
import gcsales.ru.seminar20.domain.repository.Repository;
import gcsales.ru.seminar20.presentation.UIThread;
import gcsales.ru.seminar20.presentation.base.BasePresenter;
import gcsales.ru.seminar20.presentation.mapper.DayModelDataMapper;
import gcsales.ru.seminar20.presentation.model.DayModel;
import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Презентер для погоды на неделю
 */
@Singleton
public class WeekPresenter extends BasePresenter<WeekMvpView> {

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
        GetWeeklyForecast useCase = new GetWeeklyForecast(mRepository);
        useCase.getData()
                .observeOn(AndroidSchedulers.mainThread())
                .map(data -> mDataMapper.transform(data))
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<List<DayModel>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(List<DayModel> dayModels) {
                        getMvpView().showData(dayModels);
                        getMvpView().hideProgress();
                    }

                    @Override
                    public void onError(Throwable e) {
                        System.out.println();
                    }

                    @Override
                    public void onComplete() {
                        System.out.println();
                    }
                });
    }

//    @Override
//    public void onDataLoaded(List<Day> data) {
//        final List<DayModel> dayModelList = mDataMapper.transform(data);
//        // Execute on the UI thread
//        mUIThread.post(new Runnable() {
//            @Override
//            public void run() {
//                getMvpView().hideProgress();
//                getMvpView().showData(dayModelList);
//            }
//        });
//    }
}
