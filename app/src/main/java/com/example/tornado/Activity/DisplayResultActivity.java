package com.example.tornado.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.tornado.Behavior.RevealAnimation;
import com.example.tornado.R;

public class DisplayResultActivity extends AppCompatActivity {

    RevealAnimation mRevealAnimation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_display_result);

        Intent intent = this.getIntent();   //get the intent to recieve the x and y coords, that you passed before

        LinearLayout rootLayout = findViewById(R.id.root_layout); //there you have to get the root layout of your second activity
        TextView tv = findViewById(R.id.tv_result);
        String str;

        if (intent.hasExtra("winner")){ // vérifie qu'une valeur est associée à la clé winner
            str = intent.getStringExtra("winner"); // on récupère la valeur associée à la clé
            tv.setText(str);    // on l'affiche dans le nouvelle ecran
        }
        mRevealAnimation = new RevealAnimation(rootLayout, intent, this);
    }

    @Override
    public void onBackPressed()
    {
        mRevealAnimation.unRevealActivity();
    }
}