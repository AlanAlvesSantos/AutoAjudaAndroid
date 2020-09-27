package com.e4u.autoajuda;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;

import com.airbnb.lottie.LottieAnimationView;

public class MainActivity extends AppCompatActivity {

    LottieAnimationView lottieAnimationOB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lottieAnimationOB = findViewById(R.id.lottieAnimationOB);
        lottieAnimationOB.setAnimation(R.raw.butterfly_loading);

        Handler hd = new Handler();
        hd.postDelayed(new Runnable() {
            @Override
            public void run() {

                // faz a navegação da main activity para a home activity
                Intent i = new Intent(MainActivity.this, HomeActivity.class);

                // inicia (start) a home
                startActivity(i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK));

                // finaliza a main activity
                finish();
            }
        }, 4000);
    }
}