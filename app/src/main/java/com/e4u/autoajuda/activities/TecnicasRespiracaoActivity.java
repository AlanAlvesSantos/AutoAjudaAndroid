package com.e4u.autoajuda.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.e4u.autoajuda.R;

import static com.e4u.autoajuda.MainActivity.mInterstitialAd;

public class TecnicasRespiracaoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tecnicas_respiracao);

        try {
            if (mInterstitialAd.isLoaded()) {
                mInterstitialAd.show();
            }
        } catch (Exception ex) {
        }
    }
}