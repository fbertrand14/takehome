package com.example.takehomedejamobile.modele;

/**
 * object representing a card scheme
 */
public class Card {

    //card ID
    private Integer id;
    //card name
    private String name;
    // card number
    private String number;

    /**
     * Constructor
     * @param id
     *      The ID of the card
     * @param name
     *      The name of the card
     * @param number
     *      The number of the card
     */
    public Card(Integer id, String name, String number ) {
        this.id = id;
        this.name = name;
        this.number = number;
    }

    /**
     * Getter for the card's name
     * @return A String with the name
     */
    public String getName(){
        return name;
    }
    /**
     * Getter for the card's number
     * @return A String with the number
     */
    public String getNumber(){ return number; }
    /**
     * Getter for the card's ID
     * @return An Integer with the ID
     */
    public  Integer getId(){ return id;}
}
