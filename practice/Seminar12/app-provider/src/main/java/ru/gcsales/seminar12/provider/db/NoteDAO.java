package ru.gcsales.seminar12.provider.db;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface NoteDAO {

    @Query("select * from note")
    List<NoteEntity> getAllNotes();

    /**
     * Gets a note by id.
     * Should be run on worker thread.
     *
     * @param id id of a note.
     * @return found note
     */
    @Query("select * from note where id = :id")
    NoteEntity getNoteById(long id);


    /**
     * Add a new noteEntity to a database.
     * Should be run on worker thread.
     *
     * @param noteEntity noteEntity to add
     */
    @Insert
    void addNote(NoteEntity noteEntity);

    /**
     * Edit noteEntity in a database.
     * Should be run on worker thread.
     *
     * @param noteEntity noteEntity to edit
     */
    @Update
    void editNote(NoteEntity noteEntity);
}
