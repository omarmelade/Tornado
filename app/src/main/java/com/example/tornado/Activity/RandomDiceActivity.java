package com.example.tornado.Activity;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tornado.R;

import java.util.Random;

public class RandomDiceActivity extends AppCompatActivity {

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_random_dice);


        final TextView dice_res = (TextView) findViewById(R.id.dice_result);
        final Button btn = (Button) findViewById(R.id.randomize_dice);
        final ImageView dice_face = (ImageView) findViewById(R.id.imageFace);
        final Animation animShake = AnimationUtils.loadAnimation(this, R.anim.shake);


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            // au clique ça lance l'anim et ca attend 2s avant de changer le texte et l'image en fonction de r.
            public void onClick(View v) {

                dice_face.startAnimation(animShake);
                dice_face.postDelayed(new Runnable() {

                    @Override
                    public void run() {
                        int r = randomize(6);
                        dice_res.setText("C'est un "+String.valueOf(r)+" !");
                        dice_face.setImageResource(r == 1 ? R.drawable.dice_f1 : r == 2 ? R.drawable.dice_f2 : r == 3 ? R.drawable.dice_f3 : r == 4 ? R.drawable.dice_f4 : r == 5 ? R.drawable.dice_f5 : R.drawable.dice_f6 );

                    }

                }, 2000); //  delay

            }
        });
    }

    public int randomize(int n){
        Random r = new Random();
        int randomnumber = r.nextInt(6);
        return (randomnumber+1);
    }
}