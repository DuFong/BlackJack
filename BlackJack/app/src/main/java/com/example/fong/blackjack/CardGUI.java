package com.example.fong.blackjack;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

public class CardGUI extends LinearLayout {

    // GameBoardActivity 에서 플레이어의 패를 그래픽으로 보여주기위한 클래스

    public CardGUI(Context context, AttributeSet attrs){
        super(context,attrs);

        init(context);
    }

    public CardGUI(Context context){
        super(context);

        init(context);
    }

    private void init(Context context){
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.cardgui,this, true);
    }
}
