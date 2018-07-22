package gcsales.ru.seminar19.presentation.mapper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import gcsales.ru.seminar19.domain.model.Day;
import gcsales.ru.seminar19.presentation.model.DayModel;

/**
 * Маппер для DayModel
 */
public class DayModelDataMapper {

    public DayModel transform(Day day) {
        DayModel dayModel = new DayModel();
        dayModel.setDate(day.getDate());
        dayModel.setDayTemperature(day.getDayTemperature());
        dayModel.setNightTemperature(day.getNightTemperature());
        dayModel.setIcon(day.getIcon());
        dayModel.setSummary(day.getSummary());

        return dayModel;
    }

    public List<DayModel> transform(List<Day> dayList) {
        List<DayModel> dayModelList;

        if (dayList != null && !dayList.isEmpty()) {
            dayModelList = new ArrayList<>();
            for (Day day : dayList) {
                dayModelList.add(transform(day));
            }
        } else {
            dayModelList = Collections.emptyList();
        }

        return dayModelList;
    }
}
