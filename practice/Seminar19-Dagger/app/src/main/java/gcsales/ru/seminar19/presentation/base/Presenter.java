package gcsales.ru.seminar19.presentation.base;

/**
 * Базовый класс интерфейс для всех презентеров
 * @param <T> MvpView
 */
public interface Presenter<T extends MvpView> {
    /**
     * Прицепить mvp view к презентеру
     * @param mvpView вью для присоединения
     */
    void attachView(T mvpView);

    /**
     * Отсоединить mvp view
     */
    void detachView();
}
