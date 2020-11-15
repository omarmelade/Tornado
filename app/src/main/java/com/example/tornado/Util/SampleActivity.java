package com.example.tornado.Util;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tornado.Adapter.SoundAdapter;
import com.example.tornado.R;

import java.util.ArrayList;

public class SampleActivity extends AppCompatActivity {



    private ArrayList<Integer> soundList;
    private ArrayList<String> soundName;

    private MediaPlayer mPlayer = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sample);

        soundList = new ArrayList<>();
        soundName = new ArrayList<>();
        soundList.add(R.raw.vous_avez_le_droit_de_parler);
        soundList.add(R.raw.agriculture);
        soundList.add(R.raw.instable);
        soundList.add(R.raw.perdu);
        soundList.add(R.raw.internet);
        soundName.add("DROIT DE PARLER");
        soundName.add("AGRICULTURE");
        soundName.add("INSTABLE");
        soundName.add("PERDU");
        soundName.add("INTERNET");


        findViewById(R.id.back_btn_sound).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        CoordinatorLayout coordinatorLayout = findViewById(R.id.soundbox_view);
        final RecyclerView recyclerView = findViewById(R.id.rv_sound);

        SoundAdapter soundAdapter = new SoundAdapter(soundList, soundName, this);
        recyclerView.setAdapter(soundAdapter);

        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));

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

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPlayer != null) {
            mPlayer.release();
        }
    }
}