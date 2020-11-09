package com.e4u.autoajuda.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import com.e4u.autoajuda.R;

import static com.e4u.autoajuda.HomeActivity.NOTIFICATION;
import static com.e4u.autoajuda.HomeActivity.NOTIFICATION_NOITE;

public class ExerciciosActivity extends AppCompatActivity {

    CardView card1;
    CardView card2;
    CardView cardExerciciodoDia;
    CardView card4;
    CardView card5;
    CardView cardDiario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercicios);

        linkarComponentsFromXML();

        if(NOTIFICATION == true){
            Intent i3 = new Intent(ExerciciosActivity.this, ExercicioDoDiaActivity.class);
            startActivity(i3);
        }

        if(NOTIFICATION_NOITE == true){
            Intent i3 = new Intent(ExerciciosActivity.this, MeditacaoExercicioActivity.class);
            startActivity(i3);
        }
    }
       // linkar card 2 e 3
    private void linkarComponentsFromXML(){

        card1 = findViewById(R.id.cardExercicio1);
        card2 = findViewById(R.id.cardExercicio2);
        cardExerciciodoDia = findViewById(R.id.cardExercicioDoDia);
        card4 = findViewById(R.id.cardExercicio4);
        card5 = findViewById(R.id.cardExercicio5);
        cardDiario = findViewById(R.id.cardDiario);

        clickCardViewEvents();
    }

    private void clickCardViewEvents(){

        card1.setOnClickListener(v -> {

            Intent i = new Intent(ExerciciosActivity.this, MeditacaoExercicioActivity.class);
            startActivity(i);
        });

        card2.setOnClickListener(v -> {

            Intent i2 = new Intent(ExerciciosActivity.this, TecnicasRespiracaoActivity.class);
            startActivity(i2);
        });

        //Call daily exercise screen
        cardExerciciodoDia.setOnClickListener(v -> {

            Intent i3 = new Intent(ExerciciosActivity.this, ExercicioDoDiaActivity.class);
            startActivity(i3);
        });

        card4.setOnClickListener(v -> {

            Intent i3 = new Intent(ExerciciosActivity.this, ReconhecerSeuValorActivity.class);
            startActivity(i3);
        });

        card5.setOnClickListener(v -> {

            Intent i3 = new Intent(ExerciciosActivity.this, DozeSimplesAtividadesExercicioActivity.class);
            startActivity(i3);
        });

        cardDiario.setOnClickListener(v -> {

            Intent i3 = new Intent(ExerciciosActivity.this, ListaDiarioActivity.class);
            startActivity(i3);
        });

    }
}