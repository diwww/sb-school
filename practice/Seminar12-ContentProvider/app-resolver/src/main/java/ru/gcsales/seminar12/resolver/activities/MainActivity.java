package ru.gcsales.seminar12.resolver.activities;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import ru.gcsales.seminar12.R;
import ru.gcsales.seminar12.resolver.Note;
import ru.gcsales.seminar12.resolver.NoteRepository;
import ru.gcsales.seminar12.resolver.recycler.MyAdapter;


public class MainActivity extends AppCompatActivity {

    private Toolbar mToolbar;
    private RecyclerView mRecyclerView;
    private EditText mTitleEditText;
    private EditText mTextEditText;
    private Button mAddButton;
    private MyAdapter mAdapter;
    private LinearLayoutManager mLayoutManager;
    private DividerItemDecoration mDividerItemDecoration;
    private NoteRepository mNoteRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mAdapter.setData(mNoteRepository.getNotes());
    }

    private void init() {
        mNoteRepository = new NoteRepository(this);

        mToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);

        mTextEditText = findViewById(R.id.edit_text_note);
        mTitleEditText = findViewById(R.id.edit_text_title);
        mAddButton = findViewById(R.id.button_add);
        mAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mNoteRepository.addNote(new Note(mTitleEditText.getText().toString(), mTextEditText.getText().toString()));
                mAdapter.setData(mNoteRepository.getNotes());
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
