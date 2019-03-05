package com.example.fong.blackjack;

import android.util.Log;

import java.util.ArrayList;

public class PlayingBlackJack {

    private Player player1;
    private Player player2;

    ArrayList<Card> deck = new ArrayList<Card>();

    public PlayingBlackJack(Player p1, Player p2){
        player1 = p1;
        player2 = p2;

        for(int i = 1; i <5; i++) {
            String shape = "none";
            switch (i) {
                case 1:
                    shape = "Spade";
                    break;
                case 2:
                    shape = "Clover";
                    break;
                case 3:
                    shape = "Diamond";
                    break;
                case 4:
                    shape = "Heart";
                    break;
            }
            for (int j = 1; j < 14; j++) {
                deck.add(new Card(shape,j));
            }
        }
        DivideCard();
    }

    public void DivideCard(){
        int location;
        location = (int)(deck.size()*Math.random());
        player1.hand.add(deck.get(location));
        deck.remove(location);

        location = (int)(deck.size()*Math.random());
        player1.hand.add(deck.get(location));
        deck.remove(location);

        location = (int)(deck.size()*Math.random());
        player2.hand.add(deck.get(location));
        deck.remove(location);

        location = (int)(deck.size()*Math.random());
        player2.hand.add(deck.get(location));
        deck.remove(location);
    }

    public void Go(Player player) {
        int location;
        location = (int)(deck.size()*Math.random());
        player.hand.add(deck.get(location));
        deck.remove(location);
    }

    public void Stop(Player player){
        player.Stop();
    }
}
