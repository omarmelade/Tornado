package com.example.tornado.Activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tornado.Adapter.DrawSortAdapter;
import com.example.tornado.Behavior.RevealAnimation;
import com.example.tornado.Behavior.ViewAnimation;
import com.example.tornado.Model.NameTag;
import com.example.tornado.R;
import com.example.tornado.Util.ToastUtil;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Random;

public class ScrollActivity extends AppCompatActivity {

    ArrayList<NameTag> nameTags;
    FloatingActionButton fab, fabEdit, fabSort, fabSave, fabDelete;
    ImageView back_btn;
    boolean isFABOpen = false;
    boolean isRotate = false;


    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_scroll);

        loadData();


            fab = findViewById(R.id.fab_main);
            // on defini les petits bouttons du FAB
            fabEdit = findViewById(R.id.fabEdit);
            fabSort = findViewById(R.id.fabSort);
            fabSave = findViewById(R.id.fabSave);
            fabDelete = findViewById(R.id.fabDelete);

            // appel de la sauvegarde des données
            fabSave.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    saveData();
                }
            });
            // appel du tri des données
            fabSort.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    System.out.println(nameTags.size());
                    if(nameTags.size() == 0) {
                       // Toast s = new Toast(getApplicationContext());
                        //t.setText("Vous n'avez pas inscrit de nom.");
                       // t.show();
                        String s = " Il faut d'abord rajouter des noms ! ";
                        ToastUtil.showToast(getApplicationContext(), s, Toast.LENGTH_SHORT);
                    }else{
                        startRevealActivity(v);
                    }
                }
            });

            findViewById(R.id.mode).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });

            // affiche le FAB Menu
            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    isRotate = ViewAnimation.rotateFab(v, !isRotate);
                    if(!isFABOpen){
                        // on affiche le menu
                        showFABMenu();
                    }else{
                        // on le cache
                        closeFABMenu();
                    }}});


            final RecyclerView recyclerView = findViewById(R.id.rvNames);

            // la recyclerView aura une taille fixe
            recyclerView.setHasFixedSize(true);


            DrawSortAdapter adapter = new DrawSortAdapter(nameTags);
            recyclerView.setAdapter(adapter);
            // on cree un layout manager pour gerer les layouts
            recyclerView.setLayoutManager(new GridLayoutManager(this, 1));



            fabDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    deleteData(recyclerView);
                }
            });

        fabEdit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    askingAlert();
                }
            });

        back_btn = findViewById(R.id.back_btn_sound);
        back_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onBackPressed();
                }
            });

    }


    private void deleteData(RecyclerView rv) {
        nameTags = new ArrayList<>();
        rv.setAdapter(new DrawSortAdapter(nameTags));

        clearSharedPref();
    }

    public void clearSharedPref(){
        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
    }
    
    // methode qui sauvegarde au moment ou l'on appuie sur save
    private void saveData() {
        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(nameTags);
        editor.putString("name list", json);
        editor.apply();
    }

    // methode qui charge les données a chaque lancement de l'activité
    private void loadData(){
        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("name list", null);
        Type type = new TypeToken<ArrayList<NameTag>>() {}.getType();
        nameTags = gson.fromJson(json, type);

        if (nameTags == null) {
            nameTags = new ArrayList<>();
        }
    }

    Random r = new Random();

    private String winner(){
        int nb = r.nextInt(nameTags.size());
        NameTag n = nameTags.get(nb);
        return  n.getmName();
    }

    private void startRevealActivity(View v) {

        //calculates the center of the View v you are passing
        int revealX = (int) (v.getX() + v.getWidth() / 2);
        int revealY = (int) (v.getY() + v.getHeight() / 2);

        //create an intent, that launches the second activity and pass the x and y coordinates
        Intent intent = new Intent(this, DisplayResultActivity.class);
        intent.putExtra("winner", winner());
        intent.putExtra(RevealAnimation.EXTRA_CIRCULAR_REVEAL_X, revealX);
        intent.putExtra(RevealAnimation.EXTRA_CIRCULAR_REVEAL_Y, revealY);
        //System.out.println(winner());
        //just start the activity as an shared transition, but set the options bundle to null
        ActivityCompat.startActivity(this, intent, null);

        //to prevent strange behaviours override the pending transitions
        overridePendingTransition(0, 0);
    }


    private void closeFABMenu() {
        isFABOpen=false;
        fabEdit.animate().translationY(0);
        fabSort.animate().translationY(0);
        fabSave.animate().translationY(0);
        fabDelete.animate().translationX(0);
    }

    private void showFABMenu() {
        isFABOpen = true;
        fabEdit.setVisibility(View.VISIBLE);
        fabSort.setVisibility(View.VISIBLE);
        fabSave.setVisibility(View.VISIBLE);
        fabDelete.setVisibility(View.VISIBLE);
        fabEdit.animate().translationY(-getResources().getDimension(R.dimen.standard_55));
        fabSave.animate().translationY(-getResources().getDimension(R.dimen.standard_105));
        fabSort.animate().translationY(-getResources().getDimension(R.dimen.standard_155));
        fabDelete.animate().translationX(-getResources().getDimension(R.dimen.standard_55));
    }


    public void askingAlert(){
        //Preparing views
        LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        View layout = inflater.inflate(R.layout.dialog_layout, (ViewGroup) findViewById(R.id.layout_root));
        //layout_root should be the name of the "top-level" layout node in the dialog_layout.xml file.
        final EditText nameBox = layout.findViewById(R.id.name_box);

        //Building dialog
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(layout);
        builder.setPositiveButton("Save", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                NameTag n = new NameTag(nameBox.getText().toString().toUpperCase().charAt(0) + nameBox.getText().toString().substring(1));
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