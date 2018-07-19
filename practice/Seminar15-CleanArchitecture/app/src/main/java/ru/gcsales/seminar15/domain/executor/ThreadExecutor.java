package ru.gcsales.seminar15.domain.executor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import ru.gcsales.seminar15.domain.interactor.UseCase;

public class ThreadExecutor implements Executor {

    private static final int NUM_THREADS = 2;
    private static ThreadExecutor sThreadExecutor;

    private ExecutorService mThreadPoolExecutor;


    private ThreadExecutor() {
        mThreadPoolExecutor = Executors.newFixedThreadPool(NUM_THREADS);
    }

    public static ThreadExecutor getInstance() {
        if (sThreadExecutor == null) {
            sThreadExecutor = new ThreadExecutor();
        }
        return sThreadExecutor;
    }

    @Override
    public void execute(final UseCase useCase) {
        mThreadPoolExecutor.submit(new Runnable() {
            @Override
            public void run() {
                useCase.run();
            }
        });
    }
}
