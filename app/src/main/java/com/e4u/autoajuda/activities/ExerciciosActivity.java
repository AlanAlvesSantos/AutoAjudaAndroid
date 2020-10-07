package com.e4u.autoajuda.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.os.Bundle;

import com.e4u.autoajuda.R;

public class ExerciciosActivity extends AppCompatActivity {

    CardView card1;
    //criar variavel carview p card 2 e 3


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercicios);

        linkarComponentsFromXML();
    }

    private void linkarComponentsFromXML(){

        card1 = findViewById(R.id.cardExercicio1);

        // linkar card 2 e 3
    }

    private void clickCardViewEvents(){

        card1.setOnClickListener(v -> {

            //Call another activity
        });

        // criar eventos p card2 e 3


    }
}