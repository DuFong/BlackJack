package com.example.fong.blackjack;

import java.util.ArrayList;

public class Player {
    private String name;
    private int coin;
    private int total_point;
    private int betting;
    private boolean is_go;
    ArrayList<Card> hand = new ArrayList<Card>();

    public Player(String name, int coin){
        this.name = name;
        this.coin = coin;
        total_point = 0;
        betting = 0;
        is_go = true;
    }

    public String GetName(){
        return name;
    }

    public int GetNumOfCoin(){
        return coin;
    }

    //추후에 지우자
    public void ShowHand() {
        for (int i = 0; i < this.hand.size(); i++)
            System.out.print(this.hand.get(i).getCard_shape() + this.hand.get(i).getCard_number() + " ");
    }

    public void AddPoint(){
        for(int i = 0; i < this.hand.size(); i++)
            this.total_point += this.hand.get(i).getCard_point();
    }

    public void Stop(){
        is_go = false;
    }

    public boolean Is_go() {
        return is_go;
    }

    public int GetTotalPoint(){
        return total_point;
    }

    public int getBetting() {
        return betting;
    }

    public void setBetting(int betting) {
        this.betting = betting;
    }

    public void Win(){
        coin += 2* betting;
        Reset();
    }

    public void Lose(){
        coin -= betting;
        Reset();
    }

    public void Draw(){
        Reset();
    }

    public void Reset(){
        hand.clear();
        betting = 0;
        total_point = 0;
        is_go = true;
    }
}
