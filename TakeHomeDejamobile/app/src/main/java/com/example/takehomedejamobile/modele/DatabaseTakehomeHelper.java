package com.example.takehomedejamobile.modele;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.ArrayList;

// Database helper

/**
 * This Database helper is used to interact with the sqlite database in the phone
 */
public class DatabaseTakehomeHelper extends SQLiteOpenHelper {

    // DATABASE INFORMATIONS
    private static String DATABASE_NAME = "takehome_db";
    private static String TABLE_USER = "users";
    private static String TABLE_CARDS = "cards";
    private static String TABLE_OPERATION = "operations";

    //Constructor
    public DatabaseTakehomeHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null,1);
    }

    /**
     *
     * Create all tables in the DATABASE
     * @param db
     *      The SQLite database used
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        String create_users = "CREATE TABLE "+ TABLE_USER +" (ID INTEGER PRIMARY KEY AUTOINCREMENT,email TEXT,pass TEXT,name TEXT)";
        db.execSQL(create_users);

        String create_cards = "CREATE TABLE "+ TABLE_CARDS +" (ID INTEGER PRIMARY KEY AUTOINCREMENT,userID INTEGER,name TEXT,number TEXT)";
        db.execSQL(create_cards);

        String create_operations = "CREATE TABLE "+ TABLE_OPERATION +" (ID INTEGER PRIMARY KEY AUTOINCREMENT,cardID INTEGER,amount REAL)";
        db.execSQL(create_operations);
    }

    /**
     *
     * On upgrade drop all table and recreate them
     *
     * @param db
     *      The SQLite database used
     * @param oldVersion
     *      OldVersion of the dataBase
     * @param newVersion
     *      NewVersion of the dataBase
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS" + TABLE_USER);
        db.execSQL("DROP TABLE IF EXISTS" + TABLE_CARDS);
        db.execSQL("DROP TABLE IF EXISTS" + TABLE_OPERATION);
        onCreate(db);
    }

    /**
     *
     * Function used to add a user in the database. return true if insert successful
     *
     * @param email
     *      email for this user
     * @param pass
     *      password for this user
     * @param name
     *      name for this user
     * @return True if insert successful.
     */
    public boolean addUser(String email,String pass,String name){
        SQLiteDatabase db = this.getReadableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("email",email);
        contentValues.put("pass",pass);
        contentValues.put("name",name);
        long result = db.insert(TABLE_USER, null,contentValues);
        Log.d("DATABASE INSERT", "inserting in users : "+email+" | " + pass + " | "+ name);

        if (result == -1){
            return false;
        }
        return true;
    }

    /**
     *
     * Function used to add a card in the database. return true if insert successful
     *
     * @param userid
     *      ID of the user of the card
     * @param name
     *      name for this card
     * @param number
     *      number for this card
     * @return True if insert successful
     */
    public boolean addCard(Integer userid,String name,String number){
        SQLiteDatabase db = this.getReadableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("userID", userid);
        contentValues.put("name", name);
        contentValues.put("number", number);
        long result = db.insert(TABLE_CARDS, null,contentValues);

        Log.d("DATABASE INSERT", "inserting in cards : "+userid+" | " + name + " | "+ number);

        if (result == -1){
            return false;
        }
        return true;
    }

    // Function to add an operation in the database. return true if insert successful

    /**
     *
     * Function used to add an operation in the database. return true if insert successful
     *
     * @param card_id
     *      ID of the card used
     * @param amount
     *      amount of the operation
     * @return True if insert successful
     */
    public boolean addOperation(Integer card_id,Float amount){
        SQLiteDatabase db = this.getReadableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("cardID", card_id);
        contentValues.put("amount", amount);
        long result = db.insert(TABLE_OPERATION, null,contentValues);

        Log.d("DATABASE INSERT", "inserting in operations : "+card_id+" | " + amount );

        if (result == -1){
            return false;
        }
        return true;
    }

    /**
     *
     * Function used to delete a card in the database. return true if delete successful
     *
     * @param card_id
     *      ID of the card to delete
     * @return True if delete successful
     */
    public boolean deleteCard(Integer card_id){
        SQLiteDatabase db = this.getReadableDatabase();
        long result = db.delete(TABLE_CARDS, "ID = "+ card_id,null);
        if (result == -1){
            return false;
        }
        return true;
    }

    /**
     *
     * Function used to update a card in the database. return true if update successful
     *
     * @param name
     *      New name for this card
     * @param number
     *      New number for this card
     * @param card_id
     *      ID of the card to edit
     * @return  True if update successful
     */
    public boolean updateCard(String name,String number,Integer card_id){
        SQLiteDatabase db = this.getReadableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        contentValues.put("number", number);

        long result = db.update(TABLE_CARDS, contentValues, " ID = "+card_id, null);

        if (result == -1){
            return false;
        }
        return true;
    }

    /**
     *
     * This function return a cursor corresponding to all user with a specific email
     *
     * @param email
     *      Email of the user to retrive
     * @return A cursor with all users how use this email (only one)
     */
    public Cursor getUser(String email){
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM users WHERE email ='" +email+"'";
        Cursor data = db.rawQuery(query,null);
        return data;
    }

    /**
     *  Function return the card with a specific ID
     * @param card_id
     *      ID of the card to retrieve
     * @return The Card with a given ID
     */
    public Card getCard(Integer card_id){
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM "+ TABLE_CARDS + " WHERE ID = " + card_id;
        Cursor data = db.rawQuery(query,null);

        ArrayList<Card> lCards = new ArrayList<Card>();

        while(data.moveToNext()){
            Integer id = data.getInt(0);
            String name = data.getString(2);
            String number = data.getString(3);
            Card card = new Card(id, name, number);
            lCards.add(card);
        }

        return lCards.get(0);

    }


    /**
     *
     * Function return all cards for one user
     *
     * @param user_id
     *      ID of the user
     * @return A List of all cards for this user
     */
    public ArrayList<Card> getUserCard(Integer user_id){
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM "+ TABLE_CARDS + " WHERE userID=" + user_id;
        Cursor data = db.rawQuery(query,null);

        ArrayList<Card> lCards = new ArrayList<Card>();

        while(data.moveToNext()){
            Integer id = data.getInt(0);
            String name = data.getString(2);
            String number = data.getString(3);
            Card card = new Card(id, name, number);
            lCards.add(card);
        }

        return lCards;
    }


    /**
     *  Function return true if a specific user as at least one card
     * @param user_id
     *       ID of the user
     * @return True if this user as at least one card
     */
    public boolean userHaveCard(Integer user_id){

        ArrayList<Card> lCards = getUserCard(user_id);

        if (lCards.size()>0){
            return true;
        }
        return false;
    }


    /**
     * Function return all operation for one user
     * @param user_id
     *      ID of the user
     * @return A list of all operation for this user (ordered by ID)
     */
    public ArrayList<Operation> getUserOperations(Integer user_id){
        SQLiteDatabase db = this.getReadableDatabase();

        String query = "SELECT c.ID,c.name,c.number,c.date_val,o.amount FROM cards c INNER JOIN operations o ON c.ID = o.cardID WHERE c.userID=" + user_id +" ORDER BY o.ID";
        Cursor data = db.rawQuery(query,null);

        ArrayList<Operation> lOperations = new ArrayList<>();

        while(data.moveToNext()){
            Integer id = data.getInt(0);
            String name = data.getString(1);
            String number = data.getString(2);
            String expDate = data.getString(3);
            Float amount = data.getFloat(4);
            Card card = new Card(id, name, number);

            Operation op = new Operation(card, amount);

            lOperations.add(op);
        }

        return lOperations;

    }


}
