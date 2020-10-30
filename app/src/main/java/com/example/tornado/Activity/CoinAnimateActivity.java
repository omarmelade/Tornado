package com.example.tornado.Activity;

import android.os.Build;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.example.tornado.R;

import java.util.Random;

public class CoinAnimateActivity extends AppCompatActivity {

    Random r = new Random();
    GestureDetector.SimpleOnGestureListener simpleOnGestureListener;
    ImageView iv_coin;
    ImageView back_btn;
    TextView tv_coin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coin_animate);

        iv_coin = findViewById(R.id.iv_coin);
        tv_coin = findViewById(R.id.tv_coin);
        back_btn = findViewById(R.id.back_btn);

        onCoinTap();
        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });



    }

    private void onCoinTap(){
        iv_coin.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View v) {

                int icon = r.nextFloat() < 0.50f ? 0 :1;
                if(icon == 1){
                    flipCoin(R.drawable.ic_heads, "face");
                }else{
                    flipCoin(R.drawable.ic_tails, "pile");
                }
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void flipCoin(final Integer icon, final String s){
        iv_coin.animate()
                .rotationXBy(1800f)
                .setDuration(1000)
                .withEndAction(new Runnable() {

            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void run() {
                iv_coin.setImageResource(icon);
                tv_coin.setText(s);
                tv_coin.animate().setDuration(200).translationZ(20);
                //tv_coin.setElevation(20);
                //Toast.makeText(getApplicationContext(), s, Toast.LENGTH_SHORT).show();
            }
        });

        tv_coin.animate().setDuration(100).translationZ(5);
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}