package com.e4u.autoajuda;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import com.airbnb.lottie.LottieAnimationView;
import com.e4u.autoajuda.work.WorkNotifications;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;

public class MainActivity extends AppCompatActivity {

    LottieAnimationView lottieAnimationOB;
    public static InterstitialAd mInterstitialAd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        WorkNotifications.enqueueSelf();

        MobileAds.initialize(this, "ca-app-pub-9313963887713254~9104853343");

        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId(getString(R.string.ad_inter_key_prod));

        mInterstitialAd.loadAd(new AdRequest.Builder().build());

        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                // Code to be executed when an ad finishes loading.

                try {
                } catch (Exception ex) {

                }
            }

            @Override
            public void onAdFailedToLoad(int errorCode) {
                try {

                } catch (Exception ex) {

                }
            }

            @Override
            public void onAdOpened() {
                // Code to be executed when the ad is displayed.
            }

            @Override
            public void onAdLeftApplication() {
                // Code to be executed when the user has left the app.
            }

            @Override
            public void onAdClosed() {

            }
        });

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