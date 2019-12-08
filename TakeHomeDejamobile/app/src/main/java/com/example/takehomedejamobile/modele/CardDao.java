package com.example.takehomedejamobile.modele;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

/**
 * This interface is used by room to make it's query
 */
@Dao
public interface CardDao {

    @Insert
    void insert(Card card);

    @Query("SELECT * FROM cards")
    LiveData<List<Card>> getAllcards();

    @Query("SELECT * FROM cards WHERE user_id = :user_id")
    LiveData<List<Card>> getAllUsercards(Integer user_id);

    @Query("SELECT * FROM cards WHERE id = :card_id")
    LiveData<Card> getCardByID(Integer card_id);

    @Update
    void update(Card card);

    @Delete
    int delete(Card card);
}
