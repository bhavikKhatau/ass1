package com.example.assignment1;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DbManager extends SQLiteOpenHelper {

    private static final String dbname = "Registration.db";

    public DbManager(Context context) {
        super(context, dbname, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String qry = "create table user_info(Name text, Date_Of_Birth text, Email text)";
        db.execSQL(qry);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS user_info");
        onCreate(db);
    }

    public boolean addRecord(String name, String dob, String email) {
        SQLiteDatabase db = this.getReadableDatabase();

        ContentValues cv = new ContentValues();

        cv.put("Name", name);
        cv.put("Date_Of_Birth", dob);
        cv.put("Email", email);

        long res = db.insert("user_info", null, cv);

        if (res == -1) return false;
        else return true;
    }
}
