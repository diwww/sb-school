package ru.gcsales.seminar11;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.IBinder;
import android.os.RemoteException;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class MyService extends Service {

    private static final String PREF_NAME = "pref";
    public static final String TEXT = "text";

    @Override
    public IBinder onBind(Intent intent) {
        return new IMyAidlInterface.Stub() {
            @Override
            public void setText(String text) throws RemoteException {
                SharedPreferences preferences =
                        getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                editor.putString(TEXT, text);
                editor.commit();
            }

            @Override
            public String getText() throws RemoteException {
                SharedPreferences preferences =
                        getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
                return preferences.getString(TEXT, "undefined");
            }
        };
    }

    public static Intent newIntent(Context context) {
        return new Intent(context, MyService.class);
    }
}
