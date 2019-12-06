package com.example.takehomedejamobile.modele;


import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * Object representing a payment operation
 */
@Entity(tableName = "operations")
public class Operation {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    private Integer id;
    // card scheme used
    @NonNull
    private Integer card_id;
    // amount of the operation
    @NonNull
    private Float amount;

    /**
     * Constructor
     * @param id
     *      The Operation ID
     * @param card_id
     *      The ID of the card used to pay
     * @param amount
     *      The amount of this operation
     */
    public Operation(@NonNull Integer id, @NonNull Integer card_id, @NonNull Float amount) {
        this.id = id;
        this.card_id = card_id;
        this.amount = amount;
    }

    /**
     * Getter for the operation amount
     * @return A Float value of the amount
     */
    @NonNull
    public Float getAmount(){
        return amount;
    }

    /**
     * Getter for the operation card
     * @return the Card used in this operation
     */
    @NonNull
    public Integer getId() {
        return id;
    }

    @NonNull
    public Integer getCard_id() {
        return card_id;
    }
}
