package gcsales.ru.seminar20.domain.repository;

import java.util.List;

import gcsales.ru.seminar20.domain.model.Day;
import gcsales.ru.seminar20.domain.model.Hour;
import io.reactivex.Observable;

/**
 * Интерфейс, который будет унаследован в слое data
 */
public interface Repository {

    /**
     * Получить прогноз на неделю
     *
     * @return список из дневных прогнозов
     */
    Observable<List<Day>> getWeekly();

    /**
     * Получить прогноз на день
     *
     * @return список почасовых прогнозов
     */
    Observable<List<Hour>> getDaily(long time);
}
