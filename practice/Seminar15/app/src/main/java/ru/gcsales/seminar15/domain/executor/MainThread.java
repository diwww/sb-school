package ru.gcsales.seminar15.domain.executor;

public interface MainThread {

    void post(Runnable runnable);
}
