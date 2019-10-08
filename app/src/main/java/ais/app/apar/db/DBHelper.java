package ais.app.apar.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {
    //SqlLite
    //Table Name
    public static final String TABLE_NAME = "tbStuff";
    //Column Names
    public static final String COL_MAIN_ID = "_id";
    public static final String COL_TITLE = "_title";
    public static final String COL_MAIN_CONTENT = "_content";


    static final String[] columns = new String[]{DBHelper.COL_MAIN_ID,
            DBHelper.COL_TITLE, DBHelper.COL_MAIN_CONTENT};
    //Database Information
    private static final String DATABASE_NAME = "apar.db";
    private static final int DATABASE_VERSION = 1;

    // creation SQLite statement
    private static final String DATABASE_CREATE = "CREATE TABLE " + TABLE_NAME
            + "(" + COL_MAIN_ID + " INTEGER PRIMARY KEY, "
            + COL_TITLE + " TEXT NOT NULL, " + COL_MAIN_CONTENT + " TEXT NOT NUll);";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        System.out.println("DB Created");
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DATABASE_CREATE);
        System.out.println("Table Created");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
        System.out.println("DB Updated");
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }
}