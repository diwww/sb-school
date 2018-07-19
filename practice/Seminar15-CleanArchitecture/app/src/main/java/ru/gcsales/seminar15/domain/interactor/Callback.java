package ru.gcsales.seminar15.domain.interactor;

public interface Callback<T> {
    void onSuccess(T data);

    // TODO: onError
}
