package com.example.takehomedejamobile.modele;


/**
 * Object representing a payment operation
 */
public class Operation {
    // card scheme used
    private Card card_used;
    // amount of the operation
    private Float amount;

    /**
     * Constructor
     * @param card_used
     *      The Card object of the card used to pay
     * @param amount
     *      The amount of this operation
     */
    public Operation(Card card_used, Float amount) {
        this.card_used = card_used;
        this.amount = amount;
    }

    /**
     * Getter for the operation amount
     * @return A Float value of the amount
     */
    public Float getAmount(){
        return amount;
    }

    /**
     * Getter for the operation card
     * @return the Card used in this operation
     */
    public Card getCard_used(){
        return card_used;
    }
}
