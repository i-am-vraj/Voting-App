package com.example.lab1;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {


    public static final String DATABASE_NAME = "lab8";
    public static final int version = 1;

    public static final String TABLE_NAME = "Candidate";
    public static final String COL_CID = "_id";
    public static final String COL_NAME = "name";
    public static final String COL_BASICEDU = "basicEdu";
    public static final String COL_NOTABLEWORKS = "notableWorks";
    public static final String COL_HISTORY = "history";
    public static final String COL_EARNINGS = "earnings";
    public static final String COL_PROFILEIMAGE = "profileImage";
    public static final String COL_SIGNIMAGE = "signImage";


    public static String dbQuery = "CREATE TABLE "+
            TABLE_NAME + " ( " + COL_CID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                                COL_NAME + " TEXT, " +
                                COL_BASICEDU + " TEXT, " +
                                COL_NOTABLEWORKS + " TEXT, " +
                                COL_HISTORY + " TEXT, " +
                                COL_EARNINGS + " TEXT, " +
                                COL_PROFILEIMAGE + " BLOB, " +
                                COL_SIGNIMAGE + " BLOB)";
    //CREATE TABLE Candidate (cid INTEGER PRIMARY KEY AUTOINCREMENT,name TEXT, basicEdu TEXT, notableWorks TEXT, history TEXT, earnings INTEGER)
    public DatabaseHelper(Context context)
    {
        super(context,DATABASE_NAME, null, version);
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(dbQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        sqLiteDatabase.execSQL(dbQuery);
    }
}
