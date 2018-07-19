package ru.gcsales.seminar12.resolver;

import android.content.ContentValues;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

public class CursorConverter {

    public static Note cursorToNote(Cursor cursor) {
        if (cursor.moveToFirst()) {
            String title = cursor.getString(cursor.getColumnIndex("title"));
            String text = cursor.getString(cursor.getColumnIndex("text"));
            Long id = cursor.getLong(cursor.getColumnIndex("id"));
            return new Note(title, text, id);
        }
        return null;
    }

    public static List<Note> cursorToList(Cursor cursor) {
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
    }


    public static ContentValues noteEntityToContentValues(Note noteEntity) {
        ContentValues values = new ContentValues();
        values.put("text", noteEntity.getText());
        values.put("title", noteEntity.getTitle());
        return values;
    }

    public static Note valuesToNoteEntity(ContentValues contentValues) {
        String text = contentValues.getAsString("text");
        String title = contentValues.getAsString("title");
        Note noteEntity = new Note(title, text);
        return noteEntity;
    }
}
