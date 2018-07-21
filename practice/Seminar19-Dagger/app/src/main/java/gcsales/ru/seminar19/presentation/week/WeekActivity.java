package gcsales.ru.seminar19.presentation.week;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import java.util.List;

import javax.inject.Inject;

import gcsales.ru.seminar19.R;
import gcsales.ru.seminar19.WeatherApplication;
import gcsales.ru.seminar19.presentation.model.DayModel;

public class WeekActivity extends AppCompatActivity implements WeekMvpView {

    @Inject
    WeekPresenter mWeekPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_week);
        ((WeatherApplication) getApplication()).getApplicationComponent().inject(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mWeekPresenter.attachView(this);
        mWeekPresenter.getData();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mWeekPresenter.detachView();
    }

    @Override
    public void showData(List<DayModel> data) {
        DayModel dayModel = data.get(0);
        Toast.makeText(this, dayModel.getSummary(), Toast.LENGTH_LONG).show();
    }
}
