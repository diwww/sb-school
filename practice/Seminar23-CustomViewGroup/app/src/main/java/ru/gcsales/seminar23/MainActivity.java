package ru.gcsales.seminar23;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    public static final int CHILD_COUNT = 13;
    private MyViewGroup mMyViewGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mMyViewGroup = findViewById(R.id.my_view_group);

        LayoutInflater layoutInflater = getLayoutInflater();
        for (int i = 0; i < CHILD_COUNT; i++) {
            View view = layoutInflater.inflate(R.layout.view, null, false);
            TextView textView = view.findViewById(R.id.text_view);
            textView.setText("Text #" + i);
            mMyViewGroup.addView(view);
        }
    }
}
