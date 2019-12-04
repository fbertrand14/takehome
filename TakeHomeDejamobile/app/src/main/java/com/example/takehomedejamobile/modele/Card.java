package com.example.takehomedejamobile.modele;

public class Card {

    private Integer id;
    private String name;
    private String number;


    public Card(Integer id, String name, String number ) {
        this.id = id;
        this.name = name;
        this.number = number;
    }

    public String getName(){
        return name;
    }
    public String getNumber(){ return number; }
    public  Integer getId(){ return id;}
}
