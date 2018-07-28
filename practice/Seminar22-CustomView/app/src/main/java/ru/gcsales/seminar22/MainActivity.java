package ru.gcsales.seminar22;

import android.annotation.SuppressLint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    TextView mTextView;
    MyCustomView mMyCustomView;


    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextView = findViewById(R.id.text_view_coords);
        mMyCustomView = findViewById(R.id.my_custom_view);

        mMyCustomView.setCallback(new Callback() {
            @Override
            public void onPositionChanged(float x, float y) {
                String coords = String.format(Locale.getDefault(), "X = %.2f; Y = %.2f", x, y);
                mTextView.setText(coords);
            }
        });

    }

    public interface Callback {
        void onPositionChanged(float x, float y);
    }
}
