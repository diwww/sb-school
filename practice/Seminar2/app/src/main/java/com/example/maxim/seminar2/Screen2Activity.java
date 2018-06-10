package com.example.maxim.seminar2;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

public class Screen2Activity extends AppCompatActivity {

    private EditText mEditText;
    private static final String EXTRA_MESSAGE = "some";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen2);
        initViews();
        handleIntent();
    }

    private void initViews() {
        mEditText = findViewById(R.id.edit_text);
    }

    private void handleIntent() {
        Intent intent = getIntent();
        String message = intent.getStringExtra(EXTRA_MESSAGE);
        mEditText.setText(message);
    }

    public static final Intent newIntent(Context context, String message) {
        Intent intent = new Intent(context, Screen2Activity.class);
        intent.putExtra(EXTRA_MESSAGE, message);

        return intent;
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent();
        String modifiedMessage = mEditText.getText().toString();
        intent.putExtra(MainActivity.MODIFIED_MESSAGE, modifiedMessage);

        setResult(Activity.RESULT_OK, intent);
        finish();
    }
}
