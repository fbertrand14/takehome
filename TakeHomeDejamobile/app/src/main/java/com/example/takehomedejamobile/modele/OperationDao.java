package com.example.takehomedejamobile.modele;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface OperationDao {

    @Insert
    void insert(Operation operation);

    @Query("SELECT o.id,o.card_id,o.amount,o.year,o.month,o.day,o.hour,o.minute,o.tagID_used FROM operations as o JOIN cards as c ON o.card_id = c.id WHERE c.user_id = :user_id")
    LiveData<List<Operation>> getOperationByUser(Integer user_id);
}
