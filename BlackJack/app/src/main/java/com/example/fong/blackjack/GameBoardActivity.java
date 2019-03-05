package com.example.fong.blackjack;

import android.graphics.Color;
import android.graphics.ColorSpace;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.style.ImageSpan;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class GameBoardActivity extends AppCompatActivity {

    // 플레이어 생성
    Player player1 = new Player("One",20);
    Player player2 = new Player("Two",20);

    // 게임화면 설정
    LinearLayout player1_ready_layout;
    LinearLayout player2_ready_layout;
    LinearLayout player1_layout;
    LinearLayout player2_layout;
    LinearLayout result_layout;

    // 게임정의
    PlayingBlackJack game;
    // 검정색 상수
    String black_color = "#000000";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_board);
        // 게임 초기화
        game = new PlayingBlackJack(player1,player2);

        player1_ready_layout = findViewById(R.id.player1_ready_screen);
        player2_ready_layout = findViewById(R.id.player2_ready_screen);
        player1_layout = findViewById(R.id.player1_screen);
        player2_layout = findViewById(R.id.player2_screen);
        result_layout = findViewById(R.id.result_screen);
        RoundStart();
    }


    // GUI 구현을 위한 함수
    public void GUIPlayerHand(Player player, int i){
        LinearLayout player_screen;
        if(player.GetName().equals("One"))
            player_screen = findViewById(R.id.player1_screen);
        else player_screen = findViewById(R.id.player2_screen);
        ImageView shape = new ImageView(getApplicationContext());
        TextView number = new TextView(getApplicationContext());

        if (player.hand.get(i).getCard_shape() == "Heart")
            shape.setImageResource(R.drawable.shape_heart);
        else if (player.hand.get(i).getCard_shape() == "Diamond")
            shape.setImageResource(R.drawable.shape_diamond);
        else if (player.hand.get(i).getCard_shape() == "Spade")
            shape.setImageResource(R.drawable.shape_spade);
        else if (player.hand.get(i).getCard_shape() == "Clover")
            shape.setImageResource(R.drawable.shape_clover);

        number.setText(String.valueOf(player.hand.get(i).getCard_number()));

        player_screen.addView(shape);
        player_screen.addView(number);
    }

    public void RoundStart(){

        Button first_start_button = findViewById(R.id.fist_start_button);
        final EditText player1_betting = findViewById(R.id.player1_betting);
        player1_betting.setText("");
        final EditText player2_betting = findViewById(R.id.player2_betting);
        player2_betting.setText("");

        first_start_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // EditText에 배팅할 금액 입력받은후 버튼 클릭시 정당성 검사
                if(!IsEmpty(player1_betting)) {
                    if (0 < Integer.parseInt(player1_betting.getText().toString()) && Integer.parseInt(player1_betting.getText().toString()) <= player1.GetNumOfCoin()) {
                        player1_layout.setVisibility(View.VISIBLE);
                        player1_ready_layout.setVisibility(View.GONE);
                    } else
                        Toast.makeText(GameBoardActivity.this, "유효한 배팅 금액을 입력하세요. 최소배팅 금액은 1이며 최대는 보유한 코인갯수입니다.", Toast.LENGTH_SHORT).show();
                }
                else Toast.makeText(GameBoardActivity.this, "유효한 배팅 금액을 입력하세요. 최소배팅 금액은 1이며 최대는 보유한 코인갯수입니다.", Toast.LENGTH_SHORT).show();

            }
        });

        // player1_layout 에 플레이어1 카드패 추가해서 보여주기
        for(int i = 0; i < player1.hand.size();i++){
            TextView number = new TextView(this);
            number.setText(player1.hand.get(i).getCard_shape()+String.valueOf(player1.hand.get(i).getCard_number()));
            player1_layout.addView(number);
        }
        GUIPlayerHand(player1, 0);
        GUIPlayerHand(player1, 1);
        Button player1_go_button = findViewById(R.id.player1_go_button);
        player1_go_button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                        game.Go(player1);
                        GUIPlayerHand(player1,player1.hand.size()-1);
                }
            });
        Button player1_stop_button = findViewById(R.id.player1_stop_button);
        player1_stop_button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    game.Stop(player1);
                    player1_layout.setVisibility(View.GONE);
                    player2_ready_layout.setVisibility(View.VISIBLE);
                }
            });


            Button player2_start_button = findViewById(R.id.player2_start_button);
            player2_start_button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // EditText에 배팅할 금액 입력받은후 버튼 클릭시 정당성 검사
                    if (!IsEmpty(player2_betting)) {
                        if (0 < Integer.parseInt(player2_betting.getText().toString()) && Integer.parseInt(player2_betting.getText().toString()) <= player1.GetNumOfCoin() && player2_betting.getText().toString().length() > 0) {
                            player2_ready_layout.setVisibility(View.GONE);
                            player2_layout.setVisibility(View.VISIBLE);
                        } else
                            Toast.makeText(GameBoardActivity.this, "유효한 배팅 금액을 입력하세요. 최소배팅 금액은 1이며 최대는 보유한 코인갯수입니다.", Toast.LENGTH_SHORT).show();
                    }
                    else Toast.makeText(GameBoardActivity.this, "유효한 배팅 금액을 입력하세요. 최소배팅 금액은 1이며 최대는 보유한 코인갯수입니다.", Toast.LENGTH_SHORT).show();

                }
            });


            // 플레이어 2차례
        GUIPlayerHand(player2, 0);
        GUIPlayerHand(player2, 1);
        Button player2_go_button = findViewById(R.id.player2_go_button);
        player2_go_button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    game.Go(player2);
                    GUIPlayerHand(player2,player2.hand.size()-1);

                }
            });
            Button player2_stop_button = findViewById(R.id.player2_stop_button);
            player2_stop_button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    game.Stop(player2);
                    player2_layout.setVisibility(View.GONE);
                    result_layout.setVisibility(View.VISIBLE);
                    RoundEnd();
                }
            });
    }

    public void RoundEnd(){
        player1.AddPoint();
        player2.AddPoint();
        TextView player1_point = findViewById(R.id.player1_point);
        TextView player2_point = findViewById(R.id.player2_point);

        player1_point.setText("플레이어1의 합산은 : "+player1.GetTotalPoint());
        player1_point.setTextColor(Color.parseColor(black_color));
        player2_point.setText("플레이어2의 합산은 : "+player2.GetTotalPoint());
        player2_point.setTextColor(Color.parseColor(black_color));

        WhoIsWinner();

        Button reround = findViewById(R.id.reround_button);
        reround.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                result_layout.setVisibility(View.GONE);
                player1_ready_layout.setVisibility(View.VISIBLE);
                RoundStart();
            }
        });

    }

    public boolean IsEmpty(EditText editText){
        if(editText.getText().toString().trim().length() > 0)
            return false;
        else return true;
    }

    public void WhoIsWinner(){
        // 플레이어2명다 21을 넘었을 경우
        if((player1.GetTotalPoint()> 21 && player2.GetTotalPoint() > 21) ||( player1.GetTotalPoint() == player2.GetTotalPoint())){
            player1.Draw();
            player2.Draw();
            Toast.makeText(this, "무승부 입니다.", Toast.LENGTH_SHORT).show();
        }
        else if(player1.GetTotalPoint()> 21 && player2.GetTotalPoint() <= 21){
            player1.Lose();
            player2.Win();
            Toast.makeText(this, "플레이어2 승리. 축하합니다", Toast.LENGTH_SHORT).show();
        }
        else if(player1.GetTotalPoint()<=21 && player2.GetTotalPoint() > 21){
            player1.Win();
            player2.Lose();
            Toast.makeText(this, "플레이어1 승리. 축하합니다", Toast.LENGTH_SHORT).show();
        }
        else if(player1.GetTotalPoint() < player2.GetTotalPoint()) {
            player1.Lose();
            player2.Win();
            Toast.makeText(this, "플레이어2 승리. 축하합니다", Toast.LENGTH_SHORT).show();
        }
        else if(player1.GetTotalPoint() > player2.GetTotalPoint()) {
            player1.Win();
            player2.Lose();
            Toast.makeText(this, "플레이어1 승리. 축하합니다", Toast.LENGTH_SHORT).show();
        }

    }

}
