package gcsales.ru.seminar19.presentation;


import android.os.Handler;
import android.os.Looper;

public class UIThread {

    private Handler mHandler;

    public UIThread() {
        mHandler = new Handler(Looper.getMainLooper());
    }

    public void post(Runnable runnable) {
        mHandler.post(runnable);
    }
}
