package com.example.tornado.Activity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tornado.Adapter.SoundAdapter;
import com.example.tornado.R;

import java.util.ArrayList;

public class SoundBoxActivity extends AppCompatActivity {



    private ArrayList<Integer> soundList;
    private MediaPlayer mPlayer = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sound_box);

        soundList = new ArrayList<>();
        soundList.add(R.raw.vous_avez_le_droit_de_parler);
        soundList.add(R.raw.vous_avez_le_droit_de_parler_strong);

        TextView tv1 = new TextView(this);
        TextView tv2 = new TextView(this);
        ImageView back = findViewById(R.id.back_btn_sound);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        CoordinatorLayout coordinatorLayout = findViewById(R.id.soundbox_view);
        final RecyclerView recyclerView = findViewById(R.id.rv_sound);

        SoundAdapter soundAdapter = new SoundAdapter(soundList, this);
        recyclerView.setAdapter(soundAdapter);

        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        coordinatorLayout.addView(tv1);
        tv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playSound(R.raw.coinlaunch);
            }
        });

        tv2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playSound(R.raw.wind1);
            }
        });


    }

    public void playSound(int resId) {
        if(mPlayer != null) {
            mPlayer.stop();
            mPlayer.release();
        }
        mPlayer = MediaPlayer.create(this, resId);
        mPlayer.start();
    }

    @Override
    public void onPause() {
        super.onPause();
        if(mPlayer != null) {
            mPlayer.stop();
            mPlayer.release();
        }
    }

}