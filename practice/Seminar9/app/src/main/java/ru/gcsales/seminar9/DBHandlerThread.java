package ru.gcsales.seminar9;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;

import java.util.List;

/**
 * Синглтон-класс для потока, выполняющего запросы к БД
 */
public class DBHandlerThread extends HandlerThread {

    public static final int ADD_NOTE = 110;
    public static final int GET_NOTES = 111;
    public static final int GET_NOTE_BY_ID = 112;
    public static final int UPDATE_NOTE = 113;
    public static final String TITLE_EXTRA = "title";
    public static final String TEXT_EXTRA = "text";

    private static DBHandlerThread thread;

    private Handler mHandler;
    private NoteRepository mNoteRepository;

    private DBHandlerThread(Context context) {
        super("dbHandlerThread");
        mNoteRepository = new NoteRepository(context);
    }

    public static DBHandlerThread getInstance(Context context) {
        if (thread == null) {
            thread = new DBHandlerThread(context);
        }
        return thread;
    }

    public void addNote(Handler handler, Note note) {
        Message message = Message.obtain(mHandler, ADD_NOTE, handler);
        Bundle bundle = new Bundle();
        bundle.putString(TEXT_EXTRA, note.getText());
        bundle.putString(TITLE_EXTRA, note.getTitle());
        message.setData(bundle);
        mHandler.sendMessage(message);
    }

    /**
     * @param handler UI handler to send result to
     */
    public void getNotes(Handler handler) {
        // Pass UI handler as object to message
        Message message = Message.obtain(mHandler, GET_NOTES, handler);
        mHandler.sendMessage(message);
    }

    /**
     * @param handler UI handler to send result to
     */
    public void getNoteById(Handler handler, long id) {
        Message message = Message.obtain(mHandler, GET_NOTE_BY_ID, handler);
        // Без каста не обойтись :(
        // Хотя можно было положить всё в bundle
        message.arg1 = (int) id;
        mHandler.sendMessage(message);
    }

    public void editNote(Note note) {
        Message message = Message.obtain(mHandler, UPDATE_NOTE, null);
        message.arg1 = (int) note.getId();
        Bundle bundle = new Bundle();
        bundle.putString(TEXT_EXTRA, note.getText());
        bundle.putString(TITLE_EXTRA, note.getTitle());
        message.setData(bundle);
        mHandler.sendMessage(message);
    }

    public void prepareHandler() {
        if (mHandler == null) {
            mHandler = new Handler(getLooper()) {
                @Override
                public void handleMessage(Message msg) {
                    switch (msg.what) {
                        case ADD_NOTE:
                            addNote(msg);
                            break;
                        case GET_NOTES:
                            getNotes(msg);
                            break;
                        case GET_NOTE_BY_ID:
                            getNoteById(msg);
                            break;
                        case UPDATE_NOTE:
                            editNote(msg);
                            break;
                        default:
                            break;
                    }
                }
            };
        }
    }

    private void addNote(Message msg) {
        Bundle bundle = msg.getData();
        Note note = new Note(bundle.getString(TITLE_EXTRA), bundle.getString(TEXT_EXTRA), -1);
        mNoteRepository.addNote(note);
        getNotes(msg);
    }

    private void getNotes(Message msg) {
        // Get UI handler from message
        Handler handler = (Handler) msg.obj;

        List<Note> notes = mNoteRepository.getAllNotes();
        Message message = Message.obtain();
        message.obj = notes;
        handler.sendMessage(message);
    }

    private void getNoteById(Message msg) {
        Handler handler = (Handler) msg.obj;

        long id = msg.arg1;
        Note note = mNoteRepository.getNoteById(id);
        Message message = Message.obtain();
        message.obj = note;
        handler.sendMessage(message);
    }

    private void editNote(Message msg) {
        Bundle bundle = msg.getData();
        long id = msg.arg1;
        Note note = new Note(bundle.getString(TITLE_EXTRA), bundle.getString(TEXT_EXTRA), id);
        mNoteRepository.editNote(note);
    }

}
