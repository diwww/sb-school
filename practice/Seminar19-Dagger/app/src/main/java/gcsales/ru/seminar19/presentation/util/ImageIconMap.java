package gcsales.ru.seminar19.presentation.util;

import java.util.HashMap;
import java.util.Map;

import gcsales.ru.seminar19.R;


/**
 * Отображение названий иконок на ресурсы иконок в приложении
 */
public class ImageIconMap {
    private static final Map<String, Integer> ICON_DRAWABLE_MAP = new HashMap<>();

    static {
        ICON_DRAWABLE_MAP.put("clear-day", R.drawable.ic_clear_day);
        ICON_DRAWABLE_MAP.put("clear-night", R.drawable.ic_clear_night);
        ICON_DRAWABLE_MAP.put("rain", R.drawable.ic_rain);
        ICON_DRAWABLE_MAP.put("snow", R.drawable.ic_snow);
        ICON_DRAWABLE_MAP.put("wind", R.drawable.ic_wind);
        ICON_DRAWABLE_MAP.put("fog", R.drawable.ic_fog);
        ICON_DRAWABLE_MAP.put("cloudy", R.drawable.ic_cloudy);
        ICON_DRAWABLE_MAP.put("partly-cloudy-day", R.drawable.ic_partly_cloudy_day);
        ICON_DRAWABLE_MAP.put("partly-cloudy-night", R.drawable.ic_partly_cloudy_night);
    }

    /**
     * Конвертирует имя иконки в ресурс, если таковой имеется
     * @param iconName название иконки
     * @return ресурс иконки или null, если не найден
     */
    public static int getIconResource(String iconName) {
        return ICON_DRAWABLE_MAP.get(iconName);
    }
}

