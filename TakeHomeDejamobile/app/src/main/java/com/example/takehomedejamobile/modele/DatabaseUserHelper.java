package com.example.takehomedejamobile.modele;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.ArrayList;

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
        String create_users = "CREATE TABLE "+ TABLE_USER +" (ID INTEGER PRIMARY KEY AUTOINCREMENT,email TEXT,pass TEXT,name TEXT)";
        db.execSQL(create_users);

        String create_cards = "CREATE TABLE "+ TABLE_CARDS +" (ID INTEGER PRIMARY KEY AUTOINCREMENT,userID INTEGER,name TEXT,number TEXT,date_val DATE)";
        db.execSQL(create_cards);

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
        Log.d("DATABASE INSERT", "inserting in users : "+email+" | " + pass + " | "+ name);

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

        Log.d("DATABASE INSERT", "inserting in cards : "+userid+" | " + name + " | "+ number+ " | "+ date);

        if (result == -1){
            return false;
        }
        return true;
    }

    public Cursor getUser(String email){
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM users WHERE email ='" +email+"'";
        Cursor data = db.rawQuery(query,null);
        return data;
    }

    public ArrayList<Card> getUserCard(Integer user_id){
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM "+ TABLE_CARDS + " WHERE userID=" + user_id;
        Cursor data = db.rawQuery(query,null);

        ArrayList<Card> lCards = new ArrayList<Card>();

        while(data.moveToNext()){
            Integer id = data.getInt(0);
            String name = data.getString(2);
            String number = data.getString(3);
            String expDate = data.getString(4);
            Card card = new Card(id, name, number, expDate);
            lCards.add(card);
        }

        return lCards;
    }
}
