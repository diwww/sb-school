package ru.production.ssobolevsky.espressotest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.LinearLayout;

public class RecyclerActivity extends AppCompatActivity {


    private RecyclerView mRecyclerView;
    private NamesAdapter mNamesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler);
        mRecyclerView = findViewById(R.id.rv_names);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayout.VERTICAL, false));
        mNamesAdapter = new NamesAdapter(this);
        mRecyclerView.setAdapter(mNamesAdapter);
        mNamesAdapter.setData(SingletonData.getInstance());

    }
}
