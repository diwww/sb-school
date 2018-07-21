package gcsales.ru.seminar19.presentation.mapper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import gcsales.ru.seminar19.domain.model.Hour;
import gcsales.ru.seminar19.presentation.model.HourModel;

/**
 * Маппер для HourModel
 */
public class HourModelDataMapper implements DataMapper<Hour, HourModel> {

    @Override
    public HourModel transform(Hour model) {
        return null;
    }

    @Override
    public List<HourModel> transform(List<Hour> hourList) {
        List<HourModel> hourModelList;

        if (hourList != null && !hourList.isEmpty()) {
            hourModelList = new ArrayList<>();
            for (Hour hour : hourList) {
                hourModelList.add(transform(hour));
            }
        } else {
            hourModelList = Collections.emptyList();
        }

        return hourModelList;
    }
}
