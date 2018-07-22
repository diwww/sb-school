package gcsales.ru.seminar19.injection;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import gcsales.ru.seminar19.presentation.UIThread;

@Module
public class ThreadingModule {

    @Provides
    @Singleton
    public UIThread provideUIThread() {
        return new UIThread();
    }

    @Provides
    @Singleton
    public ExecutorService provideExecutorService() {
        return Executors.newFixedThreadPool(2);
    }
}
