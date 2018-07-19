package ru.gcsales.seminar6.services;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import ru.gcsales.seminar6.Config;

public class FirstService extends Service {

    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            for (int i = 0; i < 100; i++) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    return;
                }
                Intent broadcastIntent = new Intent(Config.FILTER_1);
                broadcastIntent.putExtra(Config.TEXT_EXTRA, "Iteration: " + i);
                broadcastIntent.putExtra(Config.COLOR_EXTRA, Config.COLORS[i % Config.COLORS.length]);
                LocalBroadcastManager.getInstance(FirstService.this).sendBroadcast(broadcastIntent);
            }
            stopSelf();
        }
    };
    private Thread thread;

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        thread = new Thread(runnable);
        thread.start();

        return START_NOT_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        thread.interrupt();
        Log.e(Config.LOG_TAG, "onDestroy");
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    public static Intent newIntent(Context context) {
        return new Intent(context, FirstService.class);
    }
}
