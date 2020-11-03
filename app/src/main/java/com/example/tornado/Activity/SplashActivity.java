package com.example.tornado.Activity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.tornado.R;

public class SplashActivity extends AppCompatActivity {

    ImageView iv_logo;
    TextView tv_logo;
    MediaPlayer mySong;

    private Handler mHandler;
    private Runnable mRunnable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        mySong = MediaPlayer.create(this, R.raw.wind1);
        // met le volume très bas
        mySong.setVolume(0.1f, 0.1f);

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
            mRunnable = new Runnable() {
                    @Override
                    public void run() {
                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
                    }
                };

            mHandler = new Handler();
            mHandler.postDelayed(mRunnable, 2500);


    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(mHandler != null && mRunnable != null)
            mHandler.removeCallbacks(mRunnable);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        this.setVisible(false);
    }
}

