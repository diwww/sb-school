package ru.gcsales.seminar14.util;

import android.app.Application;
import android.arch.persistence.room.Room;

import ru.gcsales.seminar14.data.DataManager;
import ru.gcsales.seminar14.data.local.ForecastDatabase;
import ru.gcsales.seminar14.data.remote.ForecastService;

public class MyApplication extends Application implements DataManagerProvider {

    private DataManager mDataManager;

    @Override
    public void onCreate() {
        super.onCreate();

        // This will be replaced with dependency injection in future
        ForecastService service = ForecastService.Creator.newForecastService();
        ForecastDatabase database = Room.databaseBuilder(this, ForecastDatabase.class, "forecastdb")
                .build();
        mDataManager = new DataManager(service, database);
    }

    @Override
    public DataManager getDataManager() {
        return mDataManager;
    }
}
