package gcsales.ru.seminar19.presentation.mapper;

import java.util.List;

/**
 * Базовый интерфейс для трансформации моделей из domain слоя в модели presentation слоя
 *
 * @param <D> модель domain
 * @param <P> модель presentation
 */
public interface DataMapper<D, P> {

    /**
     * Трансформация моделей
     *
     * @param model domain
     * @return presentation
     */
    P transform(D model);

    /**
     * Трансформация списков моделей
     *
     * @param models domain
     * @return presentation
     */
    List<P> transform(List<D> models);
}
