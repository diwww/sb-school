package ru.gcsales.seminar12.provider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;

import ru.gcsales.seminar12.provider.db.NoteEntity;
import ru.gcsales.seminar12.provider.db.NoteRepository;

public class NoteContentProvider extends ContentProvider {

    private static UriMatcher sUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

    public static final String AUTHORITY = "ru.gcsales.content";
    public static final String PATH = "notes";
    public static final int NOTES_URI = 1;
    public static final int NOTE_BY_ID_URI = 2;

    static {
        sUriMatcher.addURI(AUTHORITY, PATH, NOTES_URI);
        sUriMatcher.addURI(AUTHORITY, PATH + "/#", NOTE_BY_ID_URI);
    }

    private NoteRepository mNoteRepository;

    @Override
    public boolean onCreate() {
        mNoteRepository = new NoteRepository(getContext());
        return true;
    }


    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {
        int uriType = sUriMatcher.match(uri);
        switch (uriType) {
            case NOTES_URI:
                return mNoteRepository.getAllNotesCursor();
            case NOTE_BY_ID_URI:
                return mNoteRepository.getNoteByIdCursor(Long.parseLong(uri.getLastPathSegment()));
            default:
                throw new IllegalArgumentException("Wrong URI.");
        }
    }


    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        // Implement this to handle requests to delete one or more rows.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public String getType(Uri uri) {
        // TODO: Implement this to handle requests for the MIME type of the data
        // at the given URI.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        int uriType = sUriMatcher.match(uri);
        if (uriType == NOTES_URI) {
            mNoteRepository.addNote(Converter.valuesToNoteEntity(values));
            getContext().getContentResolver().notifyChange(uri, null);
            return Uri.EMPTY;
        }
        throw new IllegalArgumentException("Wrong URI.");
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        int uriType = sUriMatcher.match(uri);
        if (uriType == NOTE_BY_ID_URI) {
            NoteEntity noteEntity = Converter.valuesToNoteEntity(values);
            noteEntity.setId(Long.parseLong(uri.getLastPathSegment()));
            mNoteRepository.editNote(noteEntity);
            getContext().getContentResolver().notifyChange(uri, null);
            return 1;
        }
        throw new IllegalArgumentException("Wrong URI.");
    }
}
