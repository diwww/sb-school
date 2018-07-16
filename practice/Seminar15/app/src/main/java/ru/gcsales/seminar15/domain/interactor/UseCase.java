package ru.gcsales.seminar15.domain.interactor;

import ru.gcsales.seminar15.domain.executor.Executor;
import ru.gcsales.seminar15.domain.executor.MainThread;

/**
 * Base class for all use cases (interactors).
 */
// TODO: мб сделать implements Runnable?
public abstract class UseCase {

    private Executor mExecutor;
    private MainThread mMainThread;

    public UseCase(Executor executor, MainThread mainThread) {
        mExecutor = executor;
        mMainThread = mainThread;
    }

    /**
     * Main logic goes here.
     * This method should not be called, call execute() instead.
     */
    public abstract void run();

    /**
     * Call this method to run an use case in a separate thread.
     *
     */
    public void execute() {
        mExecutor.execute(this);
    }

    public MainThread getMainThread() {
        return mMainThread;
    }
}
