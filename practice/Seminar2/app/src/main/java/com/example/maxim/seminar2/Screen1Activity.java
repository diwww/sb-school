package com.example.maxim.seminar2;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Screen1Activity extends AppCompatActivity {

    private static final String EXTRA_MESSAGE = "some";
    private TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen1); initViews();
        handleIntent();
    }


    private void initViews() {
        mTextView = findViewById(R.id.text_view);
    }

    private void handleIntent() {
        Intent intent = getIntent();
        String message = intent.getStringExtra(EXTRA_MESSAGE);
        mTextView.setText(message);
    }

    // Declare static method as final to optimize bytecode
    public static final Intent newIntent(Context context, String message) {
        Intent intent = new Intent(context, Screen1Activity.class);
        intent.putExtra(EXTRA_MESSAGE, message);

        return intent;
    }
}
