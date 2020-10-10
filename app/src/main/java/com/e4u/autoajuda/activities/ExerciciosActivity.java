package com.e4u.autoajuda.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;

import com.e4u.autoajuda.HomeActivity;
import com.e4u.autoajuda.MainActivity;
import com.e4u.autoajuda.R;

public class ExerciciosActivity extends AppCompatActivity {

    CardView card1;
    CardView card2;
    CardView card3;

    //criar variavel Cardview p card 2 e 3


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercicios);

        linkarComponentsFromXML();
    }
       // linkar card 2 e 3
    private void linkarComponentsFromXML(){

        card1 = findViewById(R.id.cardExercicio1);
        card2 = findViewById(R.id.cardExercicio2);
        card3 = findViewById(R.id.cardExercicio3);

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
        card3.setOnClickListener(v -> {

            Intent i3 = new Intent(ExerciciosActivity.this, ExercicioDoDiaActivity.class);
            startActivity(i3);
        });

    }
}