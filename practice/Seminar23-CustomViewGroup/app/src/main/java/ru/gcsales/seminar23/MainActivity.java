package ru.gcsales.seminar23;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    public static final int CHILD_COUNT = 50;
    private MyViewGroup mMyViewGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mMyViewGroup = findViewById(R.id.my_view_group);

        LayoutInflater layoutInflater = getLayoutInflater();
        for (int i = 0; i < CHILD_COUNT; i++) {
            View view = layoutInflater.inflate(R.layout.view, null, false);
            if (view instanceof TextView) {
                ((TextView) view).setText("Text #" + i);
            }
            mMyViewGroup.addView(view);
        }
    }
}
