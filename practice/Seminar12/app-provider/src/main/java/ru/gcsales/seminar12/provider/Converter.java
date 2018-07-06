package ru.gcsales.seminar12.provider;

import android.content.ContentValues;

import ru.gcsales.seminar12.provider.db.NoteEntity;

public class Converter {

    public static ContentValues noteEntityToContentValues(NoteEntity noteEntity) {
        ContentValues values = new ContentValues();
        values.put("text", noteEntity.getText());
        values.put("title", noteEntity.getTitle());
        return values;
    }

    public static NoteEntity valuesToNoteEntity(ContentValues contentValues) {
        String text = contentValues.getAsString("text");
        String title = contentValues.getAsString("title");
        NoteEntity noteEntity = new NoteEntity(title, text);
        return noteEntity;
    }
}
