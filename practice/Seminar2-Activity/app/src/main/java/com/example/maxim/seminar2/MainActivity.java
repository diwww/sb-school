package com.example.maxim.seminar2;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    // TODO: fully qualified name
    public static final String MODIFIED_MESSAGE = "modified";
    public static final int REQUEST_CODE = 1001;

    private Button mShowScreen1Button;
    private Button mShowScreen2Button;
    private EditText mMessageEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        setListeners();
    }

    private void initViews() {
        mShowScreen1Button = findViewById(R.id.screen1_button);
        mShowScreen2Button = findViewById(R.id.screen2_button);
        mMessageEditText = findViewById(R.id.message_edit_text);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_OK && data != null && requestCode == REQUEST_CODE) {
            String modifiedMessage = data.getStringExtra(MODIFIED_MESSAGE);
            mMessageEditText.setText(modifiedMessage);
        }
    }

    private void setListeners() {
        mShowScreen1Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String message = mMessageEditText.getText().toString();
                Intent intent = Screen1Activity.newIntent(MainActivity.this, message);
                startActivity(intent);
            }
        });

        mShowScreen2Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String message = mMessageEditText.getText().toString();
                Intent intent = Screen2Activity.newIntent(MainActivity.this, message);
                startActivityForResult(intent, REQUEST_CODE);
            }
        });
    }
}

