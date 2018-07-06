package ru.gcsales.seminar12.resolver;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;

import java.util.List;

public class NoteRepository {

    public static final String AUTHORITY = "content://ru.gcsales.content";
    public static final String PATH = "notes";

    private Context mContext;

    public NoteRepository(Context context) {
        mContext = context;
    }

    public List<Note> getNotes() {
        Uri uri = Uri.parse(AUTHORITY + "/" + PATH);
        Cursor cursor = mContext.getContentResolver().query(uri, null, null, null, null);
        return CursorConverter.cursorToList(cursor);
    }

    public Note getNoteById(long id) {
        Uri uri = Uri.parse(AUTHORITY + "/" + PATH + "/" + id);
        Cursor cursor = mContext.getContentResolver().query(uri, null, null, null, null, null);
        return CursorConverter.cursorToNote(cursor);
    }

    public void addNote(Note note) {
        Uri uri = Uri.parse(AUTHORITY + "/" + PATH);
        mContext.getContentResolver().insert(uri, CursorConverter.noteEntityToContentValues(note));
    }

    public void editNote(Note note) {
        Uri uri = Uri.parse(AUTHORITY + "/" + PATH + "/" + note.getId());
        mContext.getContentResolver().update(uri, CursorConverter.noteEntityToContentValues(note), null, null);
    }
}

