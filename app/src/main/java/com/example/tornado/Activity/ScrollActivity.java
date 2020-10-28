package com.example.tornado.Activity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tornado.Adapter.DrawSortAdapter;
import com.example.tornado.Model.NameTag;
import com.example.tornado.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class ScrollActivity extends AppCompatActivity {

    ArrayList<NameTag> nameTags;
    FloatingActionButton fab, fab1, fab2, fab3;
    boolean isFABOpen = false;

    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_scroll);

            fab = findViewById(R.id.fab_main);

            fab1 = findViewById(R.id.fab1);

            fab2 = findViewById(R.id.fab2);
            fab3 = findViewById(R.id.fab3);
            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(!isFABOpen){
                        showFABMenu();
                    }else{
                        closeFABMenu();
                    }
                }
            });


            RecyclerView recyclerView = findViewById(R.id.rvNames);
            // la recyclerView aura une taille fixe
            recyclerView.setHasFixedSize(true);

            nameTags = NameTag.createNamesList(0);
            DrawSortAdapter adapter = new DrawSortAdapter(nameTags);
            recyclerView.setAdapter(adapter);
            // on cree un layout manager pour gerer les layouts
            recyclerView.setLayoutManager(new GridLayoutManager(this, 2));

/*            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    askingAlert();
                }
            });*/
    }

    private void closeFABMenu() {
        isFABOpen=false;
        fab1.animate().translationY(0);
        fab2.animate().translationY(0);
        fab3.animate().translationY(0);
    }

    private void showFABMenu() {
        isFABOpen = true;
        fab1.animate().translationY(-getResources().getDimension(R.dimen.standard_55));
        fab2.animate().translationY(-getResources().getDimension(R.dimen.standard_105));
        fab3.animate().translationY(-getResources().getDimension(R.dimen.standard_155));
    }


    public void askingAlert(){
        //Preparing views
        LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        View layout = inflater.inflate(R.layout.dialog_layout, (ViewGroup) findViewById(R.id.layout_root));
        //layout_root should be the name of the "top-level" layout node in the dialog_layout.xml file.
        final EditText nameBox = (EditText) layout.findViewById(R.id.name_box);

        //Building dialog
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(layout);
        builder.setPositiveButton("Save", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                NameTag n = new NameTag(nameBox.getText().toString());
                nameTags.add(n);
                dialog.dismiss();
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

}