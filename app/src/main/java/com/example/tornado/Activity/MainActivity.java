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
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

public class MainActivity extends AppCompatActivity {

    ImageView logo;
    private int counter = 0;
    private int secret;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loadData();
        secret = 5;
        logo = findViewById(R.id.torna);
        logo.setClickable(true);

        logo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(counter > 2 && counter < secret){
                    Toast.makeText(MainActivity.this, "Appuyer encore " + (secret - counter) + " pour decouvrir un secret", Toast.LENGTH_SHORT).show();
                }
                if(counter >= secret){
                    saveData();
                    logo.setClickable(false);
                    // empeche l'utilisateur de spammer le boutton
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        public void run() {
                            logo.setClickable(true);
                        }
                    }, 500);
                    startActivity(new Intent(MainActivity.this, SampleActivity.class));
                    if(counter > secret){
                        Toast.makeText(MainActivity.this, "Vous avez déjà découvert ce secret, n'hésitez pas à en chercher d'autres.", Toast.LENGTH_SHORT).show();
                    }
                }
                counter++;
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