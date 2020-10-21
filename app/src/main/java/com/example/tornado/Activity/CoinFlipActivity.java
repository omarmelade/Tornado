package com.example.tornado.Activity;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.ImageView;
import com.example.tornado.R;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;

public class CoinFlipActivity extends AppCompatActivity {
    public static final Random RANDOM = new Random();
    private ImageView coin;
    private Button btn;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coin_flip);

        coin = (ImageView) findViewById(R.id.imagepiece);
        btn = (Button) findViewById(R.id.buttonFlip);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flipCoin();
            }
        });


    }

    private void flipCoin() {
        Animation fadeOut = new AlphaAnimation(1, 0);
        fadeOut.setInterpolator(new AccelerateInterpolator());
        fadeOut.setDuration(2800);
        fadeOut.setFillAfter(true);
        final TextView flip_res = (TextView) findViewById(R.id.flip_answer);
        fadeOut.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                flip_res.setText(" ");

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                Random randomno = new Random();
                float alea = randomno.nextFloat();
                flip_res.setText(alea > 0.5f ? "C'est Pile !" : "C'est Face !" );
                coin.setImageResource(alea > 0.5f ? R.drawable.pile : R.drawable.face);

                Animation fadeIn = new AlphaAnimation(0, 1);
                fadeIn.setInterpolator(new DecelerateInterpolator());
                fadeIn.setDuration(3000);
                fadeIn.setFillAfter(true);

                coin.startAnimation(fadeIn);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        coin.startAnimation(fadeOut);
    }

}
