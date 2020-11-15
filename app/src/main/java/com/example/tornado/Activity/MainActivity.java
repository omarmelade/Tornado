package com.example.tornado.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.tornado.R;
import com.example.tornado.Util.SampleActivity;
import com.example.tornado.Util.ToastUtil;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

public class MainActivity extends AppCompatActivity {

    ImageView t;
    private int counter = 0;
    private int counterDot = 0;
    private int secret, secret2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loadData();
        secret = 5;
        secret2 = 3;
        t = findViewById(R.id.torna);
        t.setClickable(true);

        t.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // test et affiche dans la console (-_-)
                runSample();
            }
        });

        ImageView dot = findViewById(R.id.dot);
        dot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(counterDot  < secret2){
                    String s = "Que vas-t-il se passer dans " + (secret2 - counterDot) + ".";
                    ToastUtil.showToast(getApplicationContext(), s, Toast.LENGTH_SHORT);
                }
                counterDot++;
            }
        });

        findViewById(R.id.game_dice).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goStopClick(R.id.game_dice);
                startActivity(new Intent(MainActivity.this, RandomDiceActivity.class));
            }
        });

        findViewById(R.id.game_coinflip).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goStopClick(R.id.game_coinflip);
                startActivity(new Intent(MainActivity.this, CoinAnimateActivity.class));
            }
        });

        findViewById(R.id.game_jokes).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goStopClick(R.id.game_jokes);
                startActivity(new Intent(MainActivity.this, JokeAPIActivity.class));
            }
        });

        findViewById(R.id.game_random).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goStopClick(R.id.game_random);
                startActivity(new Intent(MainActivity.this, ScrollActivity.class));
            }
        });

    }

    private void runSample(){
        if(counter > 2 && counter < secret){
            ToastUtil.showToast(getApplicationContext(), "Appuyer encore " + (secret - counter) + " pour decouvrir un secret", Toast.LENGTH_SHORT);
        }
        if(counter >= secret){
            saveData();
            t.setClickable(false);
            // empeche l'utilisateur de spammer le boutton
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                public void run() {
                    t.setClickable(true);
                }
            }, 500);
            startActivity(new Intent(MainActivity.this, SampleActivity.class));
            if(counter > secret){
                ToastUtil.showToast(getApplicationContext(), "Vous avez déjà découvert ce secret, n'hésitez pas à en chercher d'autres.", Toast.LENGTH_SHORT);
            }
        }
        counter++;
    }

    public void goStopClick(final int btn){
        findViewById(btn).setClickable(false);
        // empeche l'utilisateur de spammer le boutton
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                findViewById(btn).setClickable(true);
            }
        }, 500);
    }

    //// on stocke le compteur si le joueur a decouvert le secret
    //// pour qu'il ne puisse pas le redecouvrir

    private void saveData() {
        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(counter);
        editor.putString("counter", json);
        editor.apply();
    }

    private void loadData(){
        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("counter", "0");
        Type type = new TypeToken<Integer>() {}.getType();
        counter = gson.fromJson(json, type);
    }


}