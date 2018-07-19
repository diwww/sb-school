package ru.gcsales.seminar9;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "database.db";
    private static final int DB_VERSION = 1;

    public DBHelper(Context context) {
        this(context, DB_NAME, null, DB_VERSION);
    }

    public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        createTables(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // The laziest way to upgrade
        dropTables(db);
        createTables(db);
    }

    private void createTables(SQLiteDatabase db) {
        String query = new StringBuilder()
                .append("CREATE TABLE `note` (")
                .append("`id` INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,")
                .append("`title` TEXT_EXTRA NOT NULL,")
                .append("`text`	TEXT_EXTRA NOT NULL,")
                .append("`creation_date` TEXT_EXTRA NOT NULL,")
                .append("`modification_date` TEXT_EXTRA);")
                .toString();
        db.execSQL(query);
    }

    private void dropTables(SQLiteDatabase db) {
        String query = "DROP TABLE IF EXISTS note";
    }
}
