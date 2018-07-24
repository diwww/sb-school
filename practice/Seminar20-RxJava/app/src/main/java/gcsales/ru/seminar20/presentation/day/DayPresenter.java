package gcsales.ru.seminar20.presentation.day;


import java.util.List;
import java.util.concurrent.ExecutorService;

import javax.inject.Inject;
import javax.inject.Singleton;

import gcsales.ru.seminar20.domain.interactor.GetDailyForecast;
import gcsales.ru.seminar20.domain.repository.Repository;
import gcsales.ru.seminar20.presentation.UIThread;
import gcsales.ru.seminar20.presentation.base.BasePresenter;
import gcsales.ru.seminar20.presentation.mapper.HourModelDataMapper;
import gcsales.ru.seminar20.presentation.model.HourModel;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;

/**
 * Презентер для погоды на день
 */
@Singleton
public class DayPresenter extends BasePresenter<DayMvpView> {

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
        GetDailyForecast useCase = new GetDailyForecast(time, mRepository);
        useCase.getData()
                .subscribeOn(AndroidSchedulers.mainThread())
                .map(data -> mDataMapper.transform(data))
                .subscribe(new Observer<List<HourModel>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(List<HourModel> hourModels) {
                        getMvpView().showData(hourModels);
                        getMvpView().hideProgress();
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

//    @Override
//    public void onDataLoaded(List<Hour> data) {
//        final List<HourModel> hourModelList = mDataMapper.transform(data);
//        // Execute on the UI thread
//        mUIThread.post(new Runnable() {
//            @Override
//            public void run() {
//                getMvpView().hideProgress();
//                getMvpView().showData(hourModelList);
//            }
//        });
//    }
}
