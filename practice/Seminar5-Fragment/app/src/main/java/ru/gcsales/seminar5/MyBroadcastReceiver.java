package ru.gcsales.seminar5;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class MyBroadcastReceiver extends BroadcastReceiver {

    private ViewCallback callback;

    public MyBroadcastReceiver(ViewCallback callback) {
        this.callback = callback;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        String text = intent.getStringExtra(Config.TEXT_EXTRA);
        callback.setText(text);
    }
}
