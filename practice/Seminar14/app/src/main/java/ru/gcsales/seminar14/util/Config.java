package ru.gcsales.seminar14.util;

import java.util.HashMap;
import java.util.Map;

import ru.gcsales.seminar14.R;

public class Config {
    public static final Map<String, Integer> ICON_DRAWABLE_MAP = new HashMap<>();

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
        // TODO: "sleet" icon
    }
}
