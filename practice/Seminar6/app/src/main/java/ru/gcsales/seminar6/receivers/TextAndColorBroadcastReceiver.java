package ru.gcsales.seminar6.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;

import ru.gcsales.seminar6.Config;
import ru.gcsales.seminar6.ViewCallback;

public class TextAndColorBroadcastReceiver extends BroadcastReceiver {

    private ViewCallback callback;

    public TextAndColorBroadcastReceiver(ViewCallback callback) {
        this.callback = callback;
    }

    public ViewCallback getCallback() {
        return callback;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        getCallback().setText(intent.getStringExtra(Config.TEXT_EXTRA));
        getCallback().setColor(intent.getIntExtra(Config.COLOR_EXTRA, Color.BLACK));
    }
}
