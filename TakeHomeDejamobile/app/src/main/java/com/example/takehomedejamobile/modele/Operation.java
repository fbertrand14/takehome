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
     *       The Operation ID
     * @param card_id
     *      The ID of the card used to pay
     * @param amount
     *      The amount of this operation
     * @param year
     *      The year of this operation
     * @param month
     *      The month of this operation
     * @param day
     *      The day of this operation
     * @param hour
     *      The hour of this operation
     * @param minute
     *      The minute of this operation
     * @param tagID_used
     *      The tag used to make this operation
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
     * Getter for the operation id
     * @return the id of this operation
     */
    @NonNull
    public Integer getId() {
        return id;
    }

    /**
     * Getter for the card used in this operation
     * @return this id of the card
     */
    @NonNull
    public Integer getCard_id() {
        return card_id;
    }

    /**
     * Getter for the operation Year
     * @return the year of the operation
     */
    @NonNull
    public Integer getYear() {
        return year;
    }

    /**
     * Getter for the operation month
     * @return the month of the operation
     */
    @NonNull
    public Integer getMonth() {
        return month;
    }

    /**
     * Getter for the operation day
     * @return the day of the operation
     */
    @NonNull
    public Integer getDay() {
        return day;
    }

    /**
     * Getter for the operation hour
     * @return the hour of the operation
     */
    @NonNull
    public Integer getHour() {
        return hour;
    }

    /**
     * Getter for the operation minute
     * @return the minute of the operation
     */
    @NonNull
    public Integer getMinute() {
        return minute;
    }

    /**
     * Getter for the operation tag used
     * @return the tag used in the operation
     */
    public String getTagID_used() {
        return tagID_used;
    }

    /**
     * This function return a string with the full operation date
     * @return String for the full operation date
     */
    public String getOperationDate(){
        String operationDate = String.valueOf(getDay())+"/"+String.valueOf(getMonth()) +"/"+String.valueOf(getYear())+"  "+getHour()+":"+getMinute();
        return operationDate;
    }
}
