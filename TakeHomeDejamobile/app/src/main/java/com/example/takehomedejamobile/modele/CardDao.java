package com.example.takehomedejamobile.modele;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface CardDao {

    @Insert
    void insert(Card card);

    @Query("SELECT * FROM cards")
    LiveData<List<Card>> getAllcards();

    @Query("SELECT * FROM cards WHERE user_id = :user_id")
    LiveData<List<Card>> getAllUsercards(Integer user_id);

}
