package ru.production.ssobolevsky.espressotest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {

    private TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        mTextView = findViewById(R.id.tv_fio_detail);
        mTextView.setText(getIntent().getStringExtra("DATA"));
    }
}
