package gcsales.ru.seminar20.domain.interactor;

public interface Callback<T> {
    void onDataLoaded(T data);
}
