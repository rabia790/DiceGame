package com.example.dicegame;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatTextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Button button;
    TextView textView;
     ImageView img1, img2;
     int count1=0;
    int count2=0;
     TextView score1, score2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final int dice[] = {R.drawable.dice1, R.drawable.dice2, R.drawable.dice3,
                R.drawable.dice4, R.drawable.dice5, R.drawable.dice6};

        button = findViewById(R.id.roll);
        textView = findViewById(R.id.winnerText);

        img1 = findViewById(R.id.player1_img);
        img2 = findViewById(R.id.player2_img);
        score1 = findViewById(R.id.scores1);
        score2 = findViewById(R.id.scores2);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Random random = new Random();
                int num1 = random.nextInt(6);
                Random random1 = new Random();
                int num2 = random.nextInt(6);

                if (num1 > num2){
                   count1+= num1-num2;

                    score1.setText("Score:"+String.valueOf(count1));
              }
               else if (num2 > num1) {
                    count2+= num2-num1;

                    score2.setText("Score:"+String.valueOf(count2));
               }else{
                   score1.setText(count1);
                   score2.setText(count2);
               }

                if (count1>10) {

                    textView.setText("WINNER : Player 1");
                    textView.setVisibility(textView.VISIBLE);
                    button.setClickable(false);
                    openLogoutDialog();
                }
                else if(count2>10){
                        textView.setText("WINNER: Player 2");
                    textView.setVisibility(textView.VISIBLE);
                    button.setClickable(false);
                    openLogoutDialog();

               }

                img1.setImageResource(dice[num1]);
                img2.setImageResource(dice[num2]);
            }
        });
    }

    void openLogoutDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater layoutInflaterAndroid = LayoutInflater.from(this);
        View view = layoutInflaterAndroid.inflate(R.layout.alert_dialog, null);
        builder.setView(view);
        builder.setCancelable(true);
        final AlertDialog alertDialog = builder.create();
        alertDialog.setCanceledOnTouchOutside(true);
        Window window = alertDialog.getWindow();
        window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        WindowManager.LayoutParams lp = alertDialog.getWindow().getAttributes();
        alertDialog.getWindow().setGravity(Gravity.CENTER);
        alertDialog.getWindow().setAttributes(lp);
        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        AppCompatTextView yes = view.findViewById(R.id.btnYes);
        AppCompatTextView no = view.findViewById(R.id.btnNo);
        yes.setOnClickListener(view1 -> {
            alertDialog.dismiss();


        });
        no.setOnClickListener(view1 -> {
            alertDialog.dismiss();
        });
        alertDialog.show();
    }
}

