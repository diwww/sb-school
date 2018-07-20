package gcsales.ru.seminar19.domain.interactor;

public interface Callback<T> {
    void onDataLoaded(T data);
}
