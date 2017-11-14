package com.example.asus_pc.locationalarm2;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by asus-pc on 11/14/2017.
 */

public class dbhelper extends SQLiteOpenHelper{

    final static private  String dbname ="db1.db";
    final static private  int dbv=1;
    final static private   String stmt ="CREATE TABLE alarmloc (alarm_id integer)";

    public dbhelper(Context context) {
        super(context, dbname, null, dbv);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(stmt);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
