package com.example.takehomedejamobile.modele;

public class Operation {
    private Card card_used;
    private Float amount;

    public Operation(Card card_used, Float amount) {
        this.card_used = card_used;
        this.amount = amount;
    }

    public Float getAmount(){
        return amount;
    }

    public Card getCard_used(){
        return card_used;
    }
}
