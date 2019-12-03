package com.example.takehomedejamobile.modele;

public class Card {

    private Integer id;
    private String name;
    private String number;
    private String date;

    public Card(Integer id, String name, String number, String date) {
        this.id = id;
        this.name = name;
        this.number = number;
        this.date = date;
    }

    public String getName(){
        return name;
    }
    public String getNumber(){ return number; }
}
