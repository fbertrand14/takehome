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
    @NonNull
    private Integer year;
    @NonNull
    private Integer month;
    @NonNull
    private Integer day;
    @NonNull
    private Integer hour;
    @NonNull
    private Integer minute;
    private String tagID_used;

    /**
     * Constructor
     * @param id
     *      The Operation ID
     * @param card_id
     *      The ID of the card used to pay
     * @param amount
     *      The amount of this operation
     */
    public Operation(@NonNull Integer id, @NonNull Integer card_id, @NonNull Float amount, @NonNull Integer year, @NonNull Integer month, @NonNull Integer day, @NonNull Integer hour, @NonNull Integer minute,String tagID_used) {
        this.id = id;
        this.card_id = card_id;
        this.amount = amount;
        this.year = year;
        this.month = month;
        this.day = day;
        this.hour = hour;
        this.minute = minute;
        this.tagID_used = tagID_used;
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

    @NonNull
    public Integer getYear() {
        return year;
    }

    @NonNull
    public Integer getMonth() {
        return month;
    }

    @NonNull
    public Integer getDay() {
        return day;
    }

    @NonNull
    public Integer getHour() {
        return hour;
    }

    @NonNull
    public Integer getMinute() {
        return minute;
    }

    public String getTagID_used() {
        return tagID_used;
    }

    public String getOperationDate(){
        String operationDate = String.valueOf(getDay())+"/"+String.valueOf(getMonth()) +"/"+String.valueOf(getYear())+"  "+getHour()+":"+getMinute();
        return operationDate;
    }
}
