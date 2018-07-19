package ru.gcsales.seminar10;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.List;


public class MainActivity extends AppCompatActivity {

    private Toolbar mToolbar;
    private RecyclerView mRecyclerView;
    private EditText mTitleEditText;
    private EditText mTextEditText;
    private Button mAddButton;
    private MyAdapter mAdapter;
    private LinearLayoutManager mLayoutManager;
    private DividerItemDecoration mDividerItemDecoration;
    // By default handler is connected with
    // a thread in which it was created
    private Handler mUIHandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            // В интенте нельзя передавать тяжелые объекты,
            // а в месседже можно?
            mAdapter.setData((List<NoteEntity>) msg.obj);
            return true;
        }
    });
    // Handler thread for db querying
    private DBHandlerThread mHandlerThread;

    public MainActivity() {
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        mHandlerThread.start();
        mHandlerThread.prepareHandler();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mHandlerThread.getNotes(mUIHandler);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Не уверен, что нужно здесь закрывать поток,
        // потому что активити может уничтожиться, когда она
        // в stopped state
        mHandlerThread.quit();
    }

    private void init() {
        mHandlerThread = DBHandlerThread.getInstance(this);

        mToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);

        mTextEditText = findViewById(R.id.edit_text_note);
        mTitleEditText = findViewById(R.id.edit_text_title);
        mAddButton = findViewById(R.id.button_add);
        mAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mHandlerThread.addNote(mUIHandler, new NoteEntity(mTitleEditText.getText().toString(),
                        mTextEditText.getText().toString()));
            }
        });

        mRecyclerView = findViewById(R.id.recycler_view);

        mAdapter = new MyAdapter(this, mRecyclerView);
        mRecyclerView.setAdapter(mAdapter);

        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        mDividerItemDecoration = new DividerItemDecoration(mRecyclerView.getContext(),
                mLayoutManager.getOrientation());
        mRecyclerView.addItemDecoration(mDividerItemDecoration);
    }
}
