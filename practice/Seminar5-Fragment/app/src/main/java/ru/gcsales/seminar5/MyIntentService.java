package ru.gcsales.seminar5;

import android.app.IntentService;
import android.content.Intent;
import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;

public class MyIntentService extends IntentService {

    public MyIntentService() {
        super("MyIntentService");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        for (int i = 0; i < 1000; i++) {
            try {
                Thread.sleep(300);
                Intent broadcastIntent = new Intent(Config.FILTER);
                // Add new state extra to intent
                broadcastIntent.putExtra(Config.TEXT_EXTRA, i + "");
                LocalBroadcastManager.getInstance(this).sendBroadcast(broadcastIntent);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static Intent newIntent(Context context) {
        return new Intent(context, MyIntentService.class);
    }
}
