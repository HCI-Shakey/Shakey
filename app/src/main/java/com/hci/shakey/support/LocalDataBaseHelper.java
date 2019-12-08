package com.hci.shakey.support;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class LocalDataBaseHelper extends SQLiteOpenHelper {

    private  static final String DB_NAME = "ShakeyAction";
    private static LocalDataBaseHelper instance = null;
    private static final int DB_VERSION = 2;
    private static final String CREATE_TABLE = "create table if not exists " +
            "localactionstable(" +
            "environment text," +
            "actiontodo text,"+
            "times text)";

    private LocalDataBaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public void onCreate(SQLiteDatabase db) {
        db.execSQL( CREATE_TABLE );
    }

    public void onUpgrade(SQLiteDatabase db, int a, int b) {
    }

    public static synchronized LocalDataBaseHelper instance(Context context) {
        if (instance == null) {
            instance = new LocalDataBaseHelper(context, DB_NAME, null, DB_VERSION);
        }
        return instance;
    }
}
