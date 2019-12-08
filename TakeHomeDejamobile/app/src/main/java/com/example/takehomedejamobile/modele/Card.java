package com.example.takehomedejamobile.modele;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.List;

/**
 * object representing a card scheme
 */
@Entity(tableName = "cards")
public class Card {

    //card ID
    @PrimaryKey(autoGenerate = true)
    @NonNull
    private Integer id;
    @NonNull
    private Integer user_id;
    //card name
    @NonNull
    private String name;
    // card number
    @NonNull
    private String number;
    @NonNull
    private Integer expMonth;
    @NonNull
    private Integer expYear;

    /**
     * Constructor
     * @param id
     *      The ID of the card
     * @param name
     *      The name of the card
     * @param number
     *      The number of the card
     * @param user_id
     *      The ID of the user of this card
     */
    public Card(@NonNull Integer id, @NonNull Integer user_id, @NonNull String name, @NonNull String number,@NonNull Integer expMonth,@NonNull Integer expYear) {
        this.id = id;
        this.user_id = user_id;
        this.name = name;
        this.number = number;
        this.expMonth = expMonth;
        this.expYear = expYear;
    }

    public Card findCardWithID(List<Card> cards, Integer id){
        for (Card c : cards){
            if (c.getId().equals(id)){
                return c;
            }
        }
        return null;
    }

    /**
     * Getter for the card's name
     * @return A String with the name
     */
    @NonNull
    public String getName(){
        return name;
    }
    /**
     * Getter for the card's number
     * @return A String with the number
     */
    @NonNull
    public String getNumber(){ return number; }
    /**
     * Getter for the card's ID
     * @return An Integer with the ID
     */
    @NonNull
    public  Integer getId(){ return id;}

    /**
     * Getter for the card user_id
     * @return the user id
     */
    @NonNull
    public Integer getUser_id() {
        return user_id;
    }

    /**
     * Getter for the card expiration month
     * @return the expiration month
     */
    @NonNull
    public Integer getExpMonth() {
        return expMonth;
    }

    /**
     * Getter for the card expiration year
     * @return
     */
    @NonNull
    public Integer getExpYear() {
        return expYear;
    }
}
