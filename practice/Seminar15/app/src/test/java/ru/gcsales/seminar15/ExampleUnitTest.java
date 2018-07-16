package ru.gcsales.seminar15;

import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import ru.gcsales.seminar15.domain.executor.MainThread;
import ru.gcsales.seminar15.domain.executor.ThreadExecutor;
import ru.gcsales.seminar15.domain.interactor.Callback;
import ru.gcsales.seminar15.domain.interactor.GetWeeklyForecast;
import ru.gcsales.seminar15.domain.model.Day;
import ru.gcsales.seminar15.domain.model.Hour;
import ru.gcsales.seminar15.domain.repository.ForecastRepository;

import static org.junit.Assert.*;

public class ExampleUnitTest {

    public static ThreadExecutor sThreadExecutor;
    public static MainThread sMainThread;
    public static ForecastRepository sForecastRepository;
    public static Callback<List<Day>> sCallback;

    @BeforeClass
    public static void init() {
        sThreadExecutor = ThreadExecutor.getInstance();
        sMainThread = new MainThread() {
            @Override
            public void post(Runnable runnable) {
                runnable.run();
            }
        };
        sForecastRepository = new ForecastRepository() {
            @Override
            public List<Day> getWeekly() {
                List<Day> days = new ArrayList<>();
                Day day = new Day();
                day.setSummary("Summary");
                days.add(day);
                return days;
            }

            @Override
            public List<Hour> getDaily() {
                return null;
            }
        };
        sCallback = new Callback<List<Day>>() {
            @Override
            public void onSuccess(List<Day> data) {
                System.out.println(data);
                System.out.println(data.size());
                System.out.println(data.get(0).getSummary());
                assertEquals(data.get(0).getSummary(), "Yes");
            }
        };
    }

    @Test
    public void useCaseTest() {
        GetWeeklyForecast forecast = new GetWeeklyForecast(sThreadExecutor, sMainThread, sForecastRepository, sCallback);
        forecast.execute();
    }
}