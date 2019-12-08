package com.example.takehomedejamobile.modele;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

/**
 * This Singleton represente the room databse for this app
 */
@Database(entities = {Card.class,Operation.class,User.class},version = 1)
public abstract class TakehomeDataBase extends RoomDatabase {

    public abstract CardDao CardDao();
    public abstract OperationDao OperationDao();
    public abstract UserDao UserDao();

    private static volatile TakehomeDataBase takehomedbInstance;

    public static TakehomeDataBase getDatabase(final Context context){
        if (takehomedbInstance == null){
            synchronized (TakehomeDataBase.class){
                if (takehomedbInstance == null){
                    takehomedbInstance = Room.databaseBuilder(context.getApplicationContext(), TakehomeDataBase.class, "takehome_database").build();
                }
            }
        }
        return takehomedbInstance;
    }
}
