package ru.gcsales.seminar9;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.util.Log;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class DBManager {

    private static final String TAG = "DBManager";

    private static final String NOTE_TABLE = "note";

    private DBHelper dbHelper;

    public DBManager(Context context) {
        this.dbHelper = new DBHelper(context);
    }

    public List<Note> getNotes() {
        try (SQLiteDatabase db = dbHelper.getReadableDatabase()) {
            Cursor cursor = db.query(NOTE_TABLE,
                    null, null, null,
                    null, null, null);
            List<Note> notes = new ArrayList<>();
            if (cursor.moveToFirst()) {
                do {
                    String title = cursor.getString(cursor.getColumnIndex("title"));
                    String text = cursor.getString(cursor.getColumnIndex("text"));
                    Long id = cursor.getLong(cursor.getColumnIndex("id"));
                    notes.add(new Note(title, text, id));
                } while (cursor.moveToNext());
            }
            return notes;
        } catch (SQLiteException e) {
            Log.e(TAG, "getNotes: ", e);
        }
        return null;
    }

    public void addNote(Note note) {
        try (SQLiteDatabase db = dbHelper.getWritableDatabase()) {
            ContentValues values = new ContentValues();
            values.put("title", note.getTitle());
            values.put("text", note.getText());
            values.put("creation_date", new Date(System.currentTimeMillis()).toString());
            db.insert(NOTE_TABLE, null, values);
        } catch (SQLiteException e) {
            Log.e(TAG, "addNote: ", e);
        }
    }

    public Note getNoteById(long id) {
        try (SQLiteDatabase db = dbHelper.getReadableDatabase()) {
            Cursor cursor = db.query(NOTE_TABLE, null, "id = " + id, null,
                    null, null, null);
            if (cursor.moveToFirst()) {
                String title = cursor.getString(cursor.getColumnIndex("title"));
                String text = cursor.getString(cursor.getColumnIndex("text"));
                return new Note(title, text, id);
            }
        } catch (SQLiteException e) {
            Log.e(TAG, "getNoteById: ", e);
        }
        return null;
    }

    public void editNote(Note note) {
        try (SQLiteDatabase db = dbHelper.getWritableDatabase()) {
            ContentValues values = new ContentValues();
            values.put("title", note.getTitle());
            values.put("text", note.getText());
//            values.put("modification_date", new Date());
            db.update(NOTE_TABLE, values, "id=" + note.getId(), null);
        } catch (SQLiteException e) {
            Log.e(TAG, "editNote: ", e);
        }
    }
}
