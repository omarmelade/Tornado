package com.example.tornado.Activity;

import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
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
    MediaPlayer mySound;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coin_animate);
        mySound = MediaPlayer.create(this, R.raw.coinlaunch);
        // tentative d'augmentation du volume mais ça a pas l'air de marcher de ouf
        float test = 1;
        mySound.setVolume(test, test);
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
        final Animation animUpDown;
        // load the animation
        animUpDown = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.up_down);
        iv_coin.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View v) {
                // fixe le spam clique sur la piece.
                iv_coin.setRotationX(0f);
                int icon = r.nextFloat() < 0.50f ? 0 :1;
                mySound.start();
                iv_coin.startAnimation(animUpDown);
                if(icon == 1){
                    flipCoin(R.drawable.head, "C'est Face");
                }else{
                    flipCoin(R.drawable.tail, "C'est Pile");
                }
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void flipCoin(final Integer icon, final String s){
        iv_coin.animate()
                .rotationXBy(900f)
                .setDuration(1000)
                .withEndAction(new Runnable() {

            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void run() {
                iv_coin.setImageResource(icon);
                tv_coin.setText(s);
                iv_coin.animate()
                        .rotationXBy(900f)
                        .setDuration(1200);
                tv_coin.animate().setDuration(200).translationZ(20);
                //tv_coin.setElevation(20);
                //Toast.makeText(getApplicationContext(), s, Toast.LENGTH_SHORT).show();
            }
        });

        tv_coin.animate().setDuration(100).translationZ(5);
    }

}