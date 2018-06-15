package ru.gcsales.seminar4;

import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private TextView mCurrentStateTextView;
    private Button mChangeStateButton;
    private MyBroadcastReceiver mReceiver;
    private IntentFilter mFilter;
    private Random random = new Random();

    private class ViewCallbackImpl implements ViewCallback {
        @Override
        public void onStateChanged(String newState) {
            mCurrentStateTextView.setText(newState);
        }
    }

    private void init() {
        mReceiver = new MyBroadcastReceiver(new ViewCallbackImpl());
        mFilter = new IntentFilter(Constants.FILTER);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();

        mCurrentStateTextView = findViewById(R.id.current_state_text_view);
        mChangeStateButton = findViewById(R.id.change_state_button);
        mChangeStateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startService(MyIntentService.newIntent(MainActivity.this, random.nextInt(Constants.RANDOM_BOUND)));
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        registerReceiver(mReceiver, mFilter);
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(mReceiver);
    }
}
