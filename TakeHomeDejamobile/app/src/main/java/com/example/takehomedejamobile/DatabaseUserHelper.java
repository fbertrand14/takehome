package com.example.takehomedejamobile;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseUserHelper extends SQLiteOpenHelper {

    private static String DATABASE_NAME = "takehome_db";

    private static String TABLE_USER = "users";
    private static String user_col1 = "ID";
    private static String user_col2 = "email";
    private static String user_col3 = "pass";
    private static String user_col4 = "name";

    private static String TABLE_CARDS = "cards";

    private static String TABLE_OPERATION = "operations";


    public DatabaseUserHelper(@Nullable Context context) {

        super(context, DATABASE_NAME, null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE "+ TABLE_USER +" (ID INTEGER PRIMARY KEY AUTOINCREMENT,email TEXT,pass TEXT,name TEXT)";
        db.execSQL(query);

        query = "CREATE TABLE "+ TABLE_USER +" (ID INTEGER PRIMARY KEY AUTOINCREMENT,userID INTEGER,name TEXT,number TEXT,date_val DATE)";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS" + TABLE_USER);
        db.execSQL("DROP TABLE IF EXISTS" + TABLE_CARDS);
        db.execSQL("DROP TABLE IF EXISTS" + TABLE_OPERATION);
        onCreate(db);
    }

    public boolean addUser(String email,String pass,String name){
        SQLiteDatabase db = this.getReadableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(user_col2,email);
        contentValues.put(user_col3,pass);
        contentValues.put(user_col4,name);
        long result = db.insert(TABLE_USER, null,contentValues);

        if (result == -1){
            return false;
        }
        return true;
    }

    public boolean addCard(Integer userid,String name,String number,String date){
        SQLiteDatabase db = this.getReadableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("userID", userid);
        contentValues.put("name", name);
        contentValues.put("number", number);
        contentValues.put("date_val", date);
        long result = db.insert(TABLE_CARDS, null,contentValues);

        if (result == -1){
            return false;
        }
        return true;
    }

    public Cursor getUser(String email){
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM user WHERE email ='" +email+"'";
        Cursor data = db.rawQuery(query,null);
        return data;
    }
}
