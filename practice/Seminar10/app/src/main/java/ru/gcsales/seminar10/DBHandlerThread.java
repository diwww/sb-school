package ru.gcsales.seminar10;

import android.arch.persistence.room.Room;
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

    private NoteDatabase mNoteDatabase;
    private Handler mHandler;

    private DBHandlerThread(Context context) {
        super("dbHandlerThread");
        mNoteDatabase = Room.databaseBuilder(context,
                NoteDatabase.class, "database.db")
                .build();
    }

    public static DBHandlerThread getInstance(Context context) {
        if (thread == null) {
            thread = new DBHandlerThread(context);
        }
        return thread;
    }

    public void addNote(Handler handler, NoteEntity noteEntity) {
        Message message = Message.obtain(mHandler, ADD_NOTE, handler);
        Bundle bundle = new Bundle();
        bundle.putString(TEXT_EXTRA, noteEntity.getText());
        bundle.putString(TITLE_EXTRA, noteEntity.getTitle());
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

    public void editNote(NoteEntity noteEntity) {
        Message message = Message.obtain(mHandler, UPDATE_NOTE, null);
        message.arg1 = (int) noteEntity.getId();
        Bundle bundle = new Bundle();
        bundle.putString(TEXT_EXTRA, noteEntity.getText());
        bundle.putString(TITLE_EXTRA, noteEntity.getTitle());
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
        NoteEntity noteEntity = new NoteEntity(bundle.getString(TITLE_EXTRA), bundle.getString(TEXT_EXTRA));
        mNoteDatabase.getNoteDAO().addNote(noteEntity);
        getNotes(msg);
    }

    private void getNotes(Message msg) {
        // Get UI handler from message
        Handler handler = (Handler) msg.obj;

        List<NoteEntity> noteEntities = mNoteDatabase.getNoteDAO().getAllNotes();
        Message message = Message.obtain();
        message.obj = noteEntities;
        handler.sendMessage(message);
    }

    private void getNoteById(Message msg) {
        Handler handler = (Handler) msg.obj;

        long id = msg.arg1;
        NoteEntity noteEntity = mNoteDatabase.getNoteDAO().getNoteById(id);
        Message message = Message.obtain();
        message.obj = noteEntity;
        handler.sendMessage(message);
    }

    private void editNote(Message msg) {
        Bundle bundle = msg.getData();
        long id = msg.arg1;
        NoteEntity noteEntity = new NoteEntity(bundle.getString(TITLE_EXTRA), bundle.getString(TEXT_EXTRA), id);
        mNoteDatabase.getNoteDAO().editNote(noteEntity);
    }

}
