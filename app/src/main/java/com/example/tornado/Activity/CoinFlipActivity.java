package com.example.tornado.Activity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.tornado.R;

import java.util.Random;

public class CoinFlipActivity extends AppCompatActivity {
    public static final Random RANDOM = new Random();
    private ImageView coin;
    private Button btn;
    MediaPlayer mySong;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coin_flip);

        coin = (ImageView) findViewById(R.id.imagepiece);
        btn = (Button) findViewById(R.id.buttonFlip);
        mySong = MediaPlayer.create(this, R.raw.coinlaunch);
        // tentative d'augmentation du volume mais ça a pas l'air de marcher de ouf
        float test = 1;
        mySong.setVolume(test, test);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // on desactive le btn juste après le clic

                btn.setEnabled(false);
                mySong.start();
                Thread timer = new Thread() {
                    public void run() {
                        try {
                            sleep(3000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();

                        }

                        mySong.pause();
                    }
                };
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
                coin.setImageResource(alea > 0.5f ? R.drawable.tail : R.drawable.head);

                Animation fadeIn = new AlphaAnimation(0, 1);
                fadeIn.setInterpolator(new DecelerateInterpolator());
                fadeIn.setDuration(3000);
                fadeIn.setFillAfter(true);

                coin.startAnimation(fadeIn);
                // reactive le btn a la fin de l'animation
                btn.setEnabled(true);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        coin.startAnimation(fadeOut);

    }

}
