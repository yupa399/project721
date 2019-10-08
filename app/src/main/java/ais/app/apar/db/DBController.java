package ais.app.apar.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import ais.app.apar.entity.MainContent;


public class DBController {
    // Sql lite Database fields
    private DBHelper dbHelper;
    private SQLiteDatabase database;

    public DBController(Context context) {
        dbHelper = new DBHelper(context);
    }

    public void close() {
        dbHelper.close();
    }



    public MainContent getMainContent(int _id) {

        database = dbHelper.getReadableDatabase();

        Cursor cursor = database.query(DBHelper.TABLE_NAME, DBHelper.columns, DBHelper.COL_MAIN_ID + " =?",
                new String[]{String.valueOf(_id)}, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }


        MainContent MainContent = new MainContent(Integer.parseInt(cursor.getString(0)), cursor.getString(1),
                cursor.getString(2));

        return MainContent;
    }


    // Updating single stuff
    public int updateMainContent(MainContent mainContent) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(DBHelper.COL_TITLE, mainContent.get_title());
        values.put(DBHelper.COL_MAIN_CONTENT, mainContent.get_content());
        // updating row
        return db.update(DBHelper.TABLE_NAME, values, DBHelper.COL_MAIN_ID + " = ?",
                new String[]{String.valueOf(mainContent.get_id())});
    }




}
