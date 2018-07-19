package ru.gcsales.seminar9;

import android.content.Context;

import java.util.List;

public class NoteRepository {

    private DBManager dbManager;


    public NoteRepository(Context context) {
        dbManager = new DBManager(context);
    }

    /**
     * Gets all notes from a database.
     * Should be run on worker thread.
     *
     * @return list of notes
     */
    public List<Note> getAllNotes() {
        return dbManager.getNotes();
    }

    /**
     * Gets a note by id.
     * Should be run on worker thread.
     *
     * @param id id of a note.
     * @return found note
     */
    public Note getNoteById(long id) {
        return dbManager.getNoteById(id);
    }


    /**
     * Add a new note to a database.
     * Should be run on worker thread.
     *
     * @param note note to add
     */
    public void addNote(Note note) {
        dbManager.addNote(note);
    }

    /**
     * Edit note in a database.
     * Should be run on worker thread.
     *
     * @param note note to edit
     */
    public void editNote(Note note) {
        dbManager.editNote(note);
    }
}
