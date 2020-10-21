package com.example.tornado.Activity;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.tornado.R;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;

public class RandomDiceActivity extends AppCompatActivity {

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_random_dice);


        final TextView dice_res = (TextView) findViewById(R.id.dice_result);
        final Button btn = (Button) findViewById(R.id.randomize_dice);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dice_res.setText(randomize(6));
            }
        });
    }

    public String randomize(int n){
        Random r = new Random();
        int randomnumber = r.nextInt(6);
        return (randomnumber+1) + "";
    }
}