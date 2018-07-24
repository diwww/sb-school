package gcsales.ru.seminar20.presentation.mapper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import gcsales.ru.seminar20.domain.model.Hour;
import gcsales.ru.seminar20.presentation.model.HourModel;

/**
 * Маппер для HourModel
 */
public class HourModelDataMapper {

    public HourModel transform(Hour model) {
        HourModel hourModel = new HourModel();
        hourModel.setDate(model.getDate());
        hourModel.setHumidity(model.getHumidity());
        hourModel.setIcon(model.getIcon());
        hourModel.setPressure(model.getPressure());
        hourModel.setSummary(model.getSummary());
        hourModel.setTemperature(model.getTemperature());
        hourModel.setWindSpeed(model.getWindSpeed());
        return hourModel;
    }

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
