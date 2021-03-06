package com.e4u.autoajuda.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.airbnb.lottie.LottieAnimationView;
import com.e4u.autoajuda.R;

public class RelacionamentoActivity extends AppCompatActivity {

    LottieAnimationView lottieAnimationRelacionamento;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_relacionamento);
        initComponent();
    }

    private void initComponent(){


        lottieAnimationRelacionamento = findViewById(R.id.lottieAnimationRelacionamento);
        lottieAnimationRelacionamento.setAnimation(R.raw.amor_lottie);
    }
}