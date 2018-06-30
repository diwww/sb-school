package ru.gcsales.seminar7;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.widget.RecyclerView;

import ru.gcsales.seminar7.model.BaseModel;
import ru.gcsales.seminar7.model.FirstItemModel;

public class MyBroadcastReceiver extends BroadcastReceiver {

    private ViewCallback callback;

    public MyBroadcastReceiver(ViewCallback callback) {
        this.callback = callback;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        Parcelable first = intent.getParcelableExtra(Config.Extras.FIRST.toString());
        Parcelable second = intent.getParcelableExtra(Config.Extras.SECOND.toString());
        Parcelable third = intent.getParcelableExtra(Config.Extras.THIRD.toString());

        if (first != null) {
            callback.addModel((BaseModel)first);
            return;
        }
        if (second != null) {
            callback.addModel(((BaseModel) second));
            return;
        }
        if (third != null) {
            callback.addModel(((BaseModel) third));
            return;
        }
    }
}
