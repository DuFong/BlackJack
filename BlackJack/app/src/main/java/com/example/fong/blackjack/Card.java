package com.example.fong.blackjack;

public class Card {

    private String card_shape;
    private int card_number;
    private int card_point;

    public Card(String shape, int number){
        card_shape = shape;
        card_number = number;
        if(number > 10)
            card_point = 10;
        else card_point = number;
    }

    public int getCard_number() {
        return card_number;
    }

    public int getCard_point() {
        return card_point;
    }

    public String getCard_shape() {
        return card_shape;
    }
}
