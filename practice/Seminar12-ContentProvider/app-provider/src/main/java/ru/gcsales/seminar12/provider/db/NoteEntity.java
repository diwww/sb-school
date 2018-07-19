package ru.gcsales.seminar12.provider.db;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "note")
public class NoteEntity {

    private String title;
    private String text;
    @PrimaryKey(autoGenerate = true)
    private long id;

    @ColumnInfo(name = "creation_date")
    String creationDate;
    @ColumnInfo(name = "modification_date")
    String modificationDate;

    public NoteEntity(String title, String text) {
        this.title = title;
        this.text = text;
    }

    @Ignore
    public NoteEntity(String title, String text, long id) {
        this(title, text);
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
