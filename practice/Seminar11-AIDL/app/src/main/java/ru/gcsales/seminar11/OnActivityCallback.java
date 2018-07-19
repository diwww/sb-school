package ru.gcsales.seminar11;

import android.os.RemoteException;

public interface OnActivityCallback {
    String getData() throws RemoteException;

    void setData(String data) throws RemoteException;
}

