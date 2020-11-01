package com.example.tornado.Activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.tornado.R;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class JokeActivity extends AppCompatActivity {


    private List<String> liste;
    private Button btn;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jokes);


        ImageView back = findViewById(R.id.back_btn_joke);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        btn = (Button) findViewById(R.id.btn_random_joke);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final TextView joke_res = (TextView) findViewById(R.id.text_jokes);
                liste = Arrays.asList("Pourquoi les pêcheurs ne sont pas gros ?\n" +
                        "Parce qu’ils surveillent leur ligne."," Ceci est une blague "," 'AH'\n              -Denis Brognart","Quelle est la mamie qui fait peur aux voleurs ?\n" +
                        "Mamie Traillette.","Qu'est-ce qui est vert, se déplace sous l'eau, et fait buzzzzz ?\n" +
                        "Un chou marin ruche","Deux œufs discutent :\n" +
                        "– pourquoi t’es tout vert et aussi poilu ?– parce que j’suis un kiwi, connard","Quoi ?\n" +
                        "Feur. C’est gratuit pour les chauves.","Pourquoi n'y a-t-il pas de ballons sur le plateau de Questions Pour Un Champion ?\n" +
                        "Car Julien Les perse.","C'est l'histoire d'un zoophile qui prend son élan.","Que fait un poussin de 200kg ?\n" +
                        "PIOUUUU! PIOUUUU!","Que prend un éléphant dans un bar ?\n" +
                        "Beaucoup de place.","Pourquoi les oiseaux volent-ils vers le sud ?\n" +
                        "\n" +
                        "Car à pied, c’est beaucoup trop long","J’ai acheté des pilules de Viagra mais je me suis fait avoir,\n" +
                        "c’était des pilules de contre-bande.","Thomas Chin","Qu'est ce qui est pire qu'un bébé dans une poubelle ?\n" +
                        "Un bébé dans deux poubelles.","C’est l’histoire de Toto va voir sa maman en chialant comme une grosse merde \n « – Maman, Maman… je me suis fait mal ! – Où ça ? – Là-bas ! »",
                        "Pourquoi un chasseur emmène-t-il son fusil aux toilettes ?\n" +
                                "\n" +
                                "Pour tirer la chasse.","Quel est la date de la fête des fumeurs ?\n" +
                                "\n" +
                                "Le 1er juin.","Quelle est la différence entre Rocco Siffredi et Karl Lagerfeld ?\n" +
                                "\n" +
                                "Aucune : ils ont tous les deux une queue de cheval !","Il y a 10 types de gens dans le monde.\n" +
                                "Ceux qui parlent binaire, et les autres.","Toc Toc\n" +
                                "Qui est là ?\n" +
                                "*Très longue pause*\n" +
                                "C'est Java (marche aussi avec Internet Explorer)","C'est l'histoire d'un administrateur qui configure ses variables d'environnement,\n et là......... PATH le chemin !",
                        "Je te raconterai cette blague TCP jusqu'à ce que tu la captes.\n" +
                                "Je te raconterai cette blague TCP jusqu'à ce que tu la captes.\n" +
                                "Je te raconterai cette blague TCP jusqu'à ce que tu la captes.\n" +
                                "Je te raconterai cette blague TCP jusqu'à ce que tu la captes.\n" +
                                "Je te raconterai cette blague TCP jusqu'à ce que tu la captes.\n" +
                                "Je te raconterai cette blague TCP jusqu'à ce que tu la captes.\n" +
                                "Je te raconterai cette blague TCP jusqu'à ce que tu la captes.","C'est une requête SQL qui entre dans un bar, et le serveur répond : « Il n'y a plus de tables ! ».",
                        "Que dit un oignon quand il se cogne ?\n" +
                                "\n" +
                                "Ail\n" +
                                "\n","Est-ce que vous savez qu’elle est le fruit préféré de Donald Trump?\n" +
                                "\n" +
                                "C’est les mûrs","Un combat entre Daniel Balavoine et Marc Lavoine qui gagne?\n" +
                                "\n" +
                                "Danielle car il bat l’avoine","C’est un gars qui pose des mouchoirs sur une table.\n" +
                                "Un autre arrive et lui demande :\n" +
                                "– Qu’est-ce que tu fais ?\n" +
                                "– Je mets des mouchoirs pour éloigner les girafes !\n" +
                                "– Mais il n’y a pas de girafe !\n" +
                                "– Ba ! c’est normal puisque j’ai mis des mouchoirs !","Maman steak et ses deux enfants steak se promène dans un bois.\n" +
                                "Maman steak se retourne et malheureusement, il ne reste qu’un de ses deux petits steak:\n" +
                                "– Steak 2 ! steak 2 ! où es tu ?!\n" +
                                "Et le petit sort en disant:\n" +
                                "– Pff… ch’ tais caché !","Un pilote veut atterrir sur la piste, la tour de contrôle lui demande :\n" +
                                "– Donnez-moi votre position et votre hauteur.\n" +
                                "Le pilot répond :\n" +
                                "– Je suis assis et je mesure 1,80m !","Statistiquement, 1 personne sur 3 dans une relation n’est pas fidèle.\n" +
                                "\n" +
                                "Je vais devoir enquêter pour savoir si c’est ma femme ou ma maîtresse.","Qu’est ce qui est transparent et qui court dans un champ ?\n" +
                                "Un troupeau de vitres !");
                Random rand = new Random();
                String randomElement = liste.get(rand.nextInt(liste.size()));
               joke_res.setText(randomElement);
            }
        });
    }
}
