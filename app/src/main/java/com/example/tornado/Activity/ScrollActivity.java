package com.example.tornado.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.tornado.Adapter.DrawSortAdapter;
import com.example.tornado.Model.NameTag;
import com.example.tornado.R;

import java.util.ArrayList;

public class ScrollActivity extends AppCompatActivity {

    ArrayList<NameTag> nameTags;

    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_scroll);


            RecyclerView recyclerView = findViewById(R.id.rvNames);
            // la recyclerView aura une taille fixe
            recyclerView.setHasFixedSize(true);

            nameTags = NameTag.createNamesList(100);
            DrawSortAdapter adapter = new DrawSortAdapter(nameTags);
            recyclerView.setAdapter(adapter);
            // on cree un layout manager pour gerer les layouts
            recyclerView.setLayoutManager(new GridLayoutManager(this, 2));


            // un adapter va nous permettre de gerer le contenu de la liste

    }
}