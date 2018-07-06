package ru.gcsales.seminar12.provider.db;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteQueryBuilder;
import android.util.Log;

public class DBManager {

    private static final String TAG = "DBManager";

    private static final String NOTE_TABLE = "note";

    private DBHelper dbHelper;
    private SQLiteDatabase mSQLiteDatabase;

    public DBManager(Context context) {
        this.dbHelper = new DBHelper(context);
        mSQLiteDatabase = dbHelper.getReadableDatabase();
    }

    public Cursor getNotes() {
        try {
            Cursor cursor = mSQLiteDatabase.query(NOTE_TABLE,
                    null, null, null,
                    null, null, null);
            return cursor;
        } catch (SQLiteException e) {
            Log.e(TAG, "getNotes: ", e);
        }
        return null;
    }

    public Cursor getNoteById(long id) {
        try {
            Cursor cursor = mSQLiteDatabase.query(NOTE_TABLE, null, "id = " + id, null,
                    null, null, null);
            return cursor;
        } catch (SQLiteException e) {
            Log.e(TAG, "getNotes: ", e);
        }
        return null;
    }

//    public void addNote(NoteEntity note) {
//        try (SQLiteDatabase db = dbHelper.getWritableDatabase()) {
//            ContentValues values = new ContentValues();
//            values.put("title", note.getTitle());
//            values.put("text", note.getText());
//            values.put("creation_date", new Date(System.currentTimeMillis()).toString());
//            db.insert(NOTE_TABLE, null, values);
//        } catch (SQLiteException e) {
//            Log.e(TAG, "addNote: ", e);
//        }
//    }
//
//    public void editNote(NoteEntity note) {
//        try (SQLiteDatabase db = dbHelper.getWritableDatabase()) {
//            ContentValues values = new ContentValues();
//            values.put("title", note.getTitle());
//            values.put("text", note.getText());
////            values.put("modification_date", new Date());
//            db.update(NOTE_TABLE, values, "id=" + note.getId(), null);
//        } catch (SQLiteException e) {
//            Log.e(TAG, "editNote: ", e);
//        }
//    }
}
