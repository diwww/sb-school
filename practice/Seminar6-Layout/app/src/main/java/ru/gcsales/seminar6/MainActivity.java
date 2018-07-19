package ru.gcsales.seminar6;

import android.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.Timer;
import java.util.TimerTask;

import ru.gcsales.seminar6.fragments.SecondFragment;
import ru.gcsales.seminar6.fragments.ThirdFragment;

public class MainActivity extends AppCompatActivity {

    private Timer mTimer;
    private TimerTask mTimerTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onResume() {
        super.onResume();
        startTimer();
    }

    @Override
    protected void onPause() {
        super.onPause();
        stopTimer();
    }

    public void changeText() {
        android.support.v4.app.FragmentManager fm = getSupportFragmentManager();
        String text = ((ThirdFragment) fm.findFragmentById(R.id.fragment_third)).getText();
        ((SecondFragment) fm.findFragmentById(R.id.fragment_second)).setText(text);
    }

    private void startTimer() {
        mTimer = new Timer();
        mTimer.schedule(new MyTimerTask(), 1000, 1000);
    }

    private void stopTimer() {
        mTimer.cancel();
        mTimer = null;
    }

    private class MyTimerTask extends TimerTask {
        @Override
        public void run() {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    changeText();
                }
            });
        }
    }

    ;
}
