package ru.gcsales.seminar12.resolver;


public class Note {

    private String title;
    private String text;
    private long id;

    String creationDate;
    String modificationDate;

    public Note(String title, String text) {
        this.title = title;
        this.text = text;
    }

    public Note(String title, String text, long id) {
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
