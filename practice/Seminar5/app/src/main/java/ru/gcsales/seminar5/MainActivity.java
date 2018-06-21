package ru.gcsales.seminar5;

import android.app.FragmentManager;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private MyBroadcastReceiver mReceiver;
    private IntentFilter mFilter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    @Override
    protected void onResume() {
        super.onResume();
        LocalBroadcastManager.getInstance(this).registerReceiver(mReceiver, mFilter);
        startService(MyIntentService.newIntent(this));
    }

    @Override
    protected void onPause() {
        super.onPause();
        LocalBroadcastManager.getInstance(this).unregisterReceiver(mReceiver);
        // TODO: на всякий случай
        stopService(MyIntentService.newIntent(this));
    }

    private void init() {
        mReceiver = new MyBroadcastReceiver(new ViewCallbackImpl());
        mFilter = new IntentFilter(Config.FILTER);
    }

    private class ViewCallbackImpl implements ViewCallback {

        @Override
        public void setText(String text) {
            FirstFragment fragment = (FirstFragment) getSupportFragmentManager()
                    .findFragmentById(R.id.fragment_first);
            fragment.setText(text);
        }
    }

    public String getText() {
        FirstFragment fragment = (FirstFragment) getSupportFragmentManager()
                .findFragmentById(R.id.fragment_first);
        return fragment.getText();
    }
}
