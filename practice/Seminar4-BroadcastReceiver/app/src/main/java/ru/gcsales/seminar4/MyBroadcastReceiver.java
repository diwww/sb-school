package ru.gcsales.seminar4;

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
        callback.onStateChanged(intent.getStringExtra(Constants.STATE_EXTRA));
    }
}
