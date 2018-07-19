package ru.gcsales.seminar12.provider.db;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.database.Cursor;

import java.util.List;

public class NoteRepository {


    private NoteDatabase mNoteDatabase;
    private DBManager mDBManager;

    public NoteRepository(Context context) {
        mNoteDatabase = Room.databaseBuilder(context,
                NoteDatabase.class, "database.db")
                .build();
        mDBManager = new DBManager(context);
    }

    public Cursor getAllNotesCursor() {
        return mDBManager.getNotes();
    }

    public Cursor getNoteByIdCursor(long id) {
        return mDBManager.getNoteById(id);
    }

    public List<NoteEntity> getAllNotes() {
        return mNoteDatabase.getNoteDAO().getAllNotes();
    }

    public void addNote(NoteEntity noteEntity) {
        mNoteDatabase.getNoteDAO().addNote(noteEntity);
    }

    public NoteEntity getNoteById(long id) {
        return mNoteDatabase.getNoteDAO().getNoteById(id);
    }

    public void editNote(NoteEntity noteEntity) {
        mNoteDatabase.getNoteDAO().editNote(noteEntity);
    }
}
