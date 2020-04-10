package com.example.lab1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class DatabaseManager {
    private DatabaseHelper dbHelper;
    private Context context;
    private SQLiteDatabase database;

    public DatabaseManager(Context c) {
        context = c;
    }

    public DatabaseManager open() throws SQLException {
        dbHelper = new DatabaseHelper(context);
        database = dbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        dbHelper.close();
    }

    public void insert(String NAME, String BASICEDU, String NOTABLEWORKS, String HISTORY, String EARNINGS, byte[] img1, byte[] img2) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseHelper.COL_NAME, NAME);
        contentValues.put(DatabaseHelper.COL_BASICEDU, BASICEDU);
        contentValues.put(DatabaseHelper.COL_NOTABLEWORKS, NOTABLEWORKS);
        contentValues.put(DatabaseHelper.COL_HISTORY, HISTORY);
        contentValues.put(DatabaseHelper.COL_EARNINGS, EARNINGS);
        contentValues.put(DatabaseHelper.COL_PROFILEIMAGE, img1);
        contentValues.put(DatabaseHelper.COL_SIGNIMAGE, img2);
        database.insert(DatabaseHelper.TABLE_NAME, null, contentValues);
    }

    public Cursor fetch() {
        String[] cols = new String[]{ DatabaseHelper.COL_CID, DatabaseHelper.COL_NAME, DatabaseHelper.COL_BASICEDU, DatabaseHelper.COL_NOTABLEWORKS, DatabaseHelper.COL_HISTORY, DatabaseHelper.COL_EARNINGS};
        Cursor cursor = database.query(DatabaseHelper.TABLE_NAME, cols, null, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;
    }

    public byte[][] getImages(long cid) {

        byte[] img1 = null;
        byte[] img2 = null;
        Cursor cursor = database.rawQuery("SELECT " + DatabaseHelper.COL_PROFILEIMAGE + " , " + DatabaseHelper.COL_SIGNIMAGE + " FROM " + DatabaseHelper.TABLE_NAME + " where " + DatabaseHelper.COL_CID +"="+cid, null);
        if(cursor.moveToFirst()) {
            Log.e("Cursor Object", DatabaseUtils.dumpCursorToString(cursor));
            img1 = cursor.getBlob(cursor.getColumnIndex(DatabaseHelper.COL_PROFILEIMAGE));
            img2 = cursor.getBlob(cursor.getColumnIndex(DatabaseHelper.COL_SIGNIMAGE));
        }
        byte[][] images = new byte[][]{img1,img2};
        cursor.close();
        return  images;
    }

    public int update(long cid, String NAME, String BASICEDU, String NOTABLEWORKS, String HISTORY, String EARNINGS) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseHelper.COL_NAME, NAME);
        contentValues.put(DatabaseHelper.COL_BASICEDU, BASICEDU);
        contentValues.put(DatabaseHelper.COL_NOTABLEWORKS, NOTABLEWORKS);
        contentValues.put(DatabaseHelper.COL_HISTORY, HISTORY);
        contentValues.put(DatabaseHelper.COL_EARNINGS, EARNINGS);
        int i = database.update(DatabaseHelper.TABLE_NAME, contentValues, DatabaseHelper.COL_CID + " = " + cid, null);
        return i;
    }

    public void delete(long cid) {
        database.delete(DatabaseHelper.TABLE_NAME, DatabaseHelper.COL_CID + " = " + cid, null);
    }
}
