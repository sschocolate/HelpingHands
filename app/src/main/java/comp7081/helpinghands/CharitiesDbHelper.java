package comp7081.helpinghands;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;


public class CharitiesDbHelper extends SQLiteOpenHelper {
    public SQLiteDatabase db;

    // Database details
    public static final String DATABASE_NAME = "charitiesDB";
    public static final String TABLE_NAME = "charities";
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_PATH = "//data/data/comp7081.helpinghands/databases/charitiesDB";

    // Database columns
    public static final String COL_UID = "_id";
    public static final String COL_NAME = "Name";
    public static final String COL_IMAGE = "Image";
    public static final String COL_BIOGRAPHY = "Biography";
    public static final String COL_DONATED = "Donated";

    // SQL commands
    private static final String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " (" + COL_UID + " INTEGER, " + COL_NAME + " VARCHAR(255) PRIMARY KEY UNIQUE, " + COL_IMAGE + " VARCHAR(255), " + COL_BIOGRAPHY + " VARCHAR(255), " + COL_DONATED  + " INTEGER);";
    private static final String DROP_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;

    public CharitiesDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DROP_TABLE);
        onCreate(db);
    }

    public void addEntry(String name, String image, String bio, int donated) throws SQLiteException {
        db = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COL_NAME,    name);
        cv.put(COL_IMAGE,   image);
        cv.put(COL_BIOGRAPHY,   bio);
        cv.put(COL_DONATED,   donated);
        db.insertWithOnConflict( TABLE_NAME, null, cv , SQLiteDatabase.CONFLICT_REPLACE);
    }

    public void removeEvent(long id) {
        db = getWritableDatabase();

        db.delete(TABLE_NAME, COL_UID + "=" + id, null);
        db.execSQL("UPDATE SQLITE_SEQUENCE SET seq = 0 WHERE NAME = '" + TABLE_NAME + "'");
    }

    public Cursor getRowWithId(long id) {
        db = getWritableDatabase();
        String columns[] = {COL_UID};
        String selection[] = {String.valueOf(id)};
        Cursor cursor = db.query(TABLE_NAME, columns, "_id=?", selection, null, null, null);
        if(cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;
    }

    /**
     * Retrieves all the records in the database.
     * @return Records stored in a cursor
     */
    public Cursor getAllRows() {
        db = getReadableDatabase();
        String columns[] = {COL_UID, COL_NAME, COL_IMAGE, COL_BIOGRAPHY, COL_DONATED};
        Cursor cursor = db.query(TABLE_NAME, columns, null, null, null, null, null);
        if(cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;
    }
}
