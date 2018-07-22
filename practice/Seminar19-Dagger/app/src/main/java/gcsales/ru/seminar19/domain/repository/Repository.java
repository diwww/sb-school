package gcsales.ru.seminar19.domain.repository;

import java.util.List;

import gcsales.ru.seminar19.domain.model.Day;
import gcsales.ru.seminar19.domain.model.Hour;

/**
 * Интерфейс, который будет унаследован в слое data
 */
public interface Repository {

    /**
     * Получить прогноз на неделю
     * @return список из дневных прогнозов
     */
    List<Day> getWeekly();

    /**
     * Получить прогноз на день
     * @return список почасовых прогнозов
     */
    List<Hour> getDaily(long time);
}
