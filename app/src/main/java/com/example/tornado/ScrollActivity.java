package com.example.tornado;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

public class ScrollActivity extends AppCompatActivity {

    LinearLayout containerLayout;
    static int totalEditTexts = 0;


    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_scroll);
        containerLayout = findViewById(R.id.mlayout);
        Button newBtn = findViewById(R.id.newbtn);
        newBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                newEditText();
            }
        });
    }

    public void newEditText(){
        totalEditTexts++;
        if (totalEditTexts > 100)
            return;
        EditText editText = new EditText(this);
        containerLayout.addView(editText);
        editText.setGravity(Gravity.END);
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) editText.getLayoutParams();
        layoutParams.width = LinearLayout.LayoutParams.MATCH_PARENT;
        editText.setLayoutParams(layoutParams);
        editText.setTag("EditText" + totalEditTexts);
    }
}