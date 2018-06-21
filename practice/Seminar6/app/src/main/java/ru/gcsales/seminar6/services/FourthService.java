package ru.gcsales.seminar6.services;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

public class FourthService extends Service {

    private IBinder binder = new LocalBinder();
    private int angle = 0;

    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }

    public class LocalBinder extends Binder {
        public FourthService getService() {
            return FourthService.this;
        }
    }

    public static Intent newIntent(Context context) {
        return new Intent(context, FourthService.class);
    }

    public int getAngle() {
        angle = (angle + 10) % 360;
        return angle;
    }
}
