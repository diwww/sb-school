package ru.gcsales.seminar10;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import ru.gcsales.seminar10.R;
import ru.gcsales.seminar10.fragments.EditNoteFragment;
import ru.gcsales.seminar10.fragments.ViewNoteFragment;

public class NoteActivity extends AppCompatActivity {

    public static final String NOTE_ID_EXTRA = "ru.gcsales.note_id";
    private Toolbar mToolbar;
    private Intent mIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);
        mToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        // Get intent, which started an activity
        mIntent = getIntent();

    }

    @Override
    protected void onResume() {
        super.onResume();

        Bundle bundle = new Bundle();
        bundle.putLong(NOTE_ID_EXTRA, mIntent.getLongExtra(NOTE_ID_EXTRA, 0));
        ViewNoteFragment viewNoteFragment = ViewNoteFragment.newInstance(bundle);
        getSupportFragmentManager().beginTransaction()
                .add(R.id.frame_layout, viewNoteFragment)
                .commit();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.menu_note, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);
        if (item.getItemId() == R.id.action_edit) {
            Bundle bundle = new Bundle();
            bundle.putLong(NOTE_ID_EXTRA, mIntent.getLongExtra(NOTE_ID_EXTRA, 0));
            EditNoteFragment editNoteFragment = EditNoteFragment.newInstance(bundle);
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.frame_layout, editNoteFragment)
                    .commit();
        }
        return true;
    }

    public static Intent newIntent(Context context, long id) {
        Intent intent = new Intent(context, NoteActivity.class);
        intent.putExtra(NOTE_ID_EXTRA, id);
        return intent;
    }
}
