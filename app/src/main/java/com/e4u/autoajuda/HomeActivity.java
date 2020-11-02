package com.e4u.autoajuda;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.PowerManager;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.e4u.autoajuda.activities.ExerciciosActivity;
import com.e4u.autoajuda.activities.FraseDiaActivity;
import com.e4u.autoajuda.fragmentos.HomeFragment;
import com.e4u.autoajuda.fragmentos.NoticiasFragment;
import com.e4u.autoajuda.fragmentos.VideoFragment;
import com.e4u.autoajuda.work.WorkNotifications;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class HomeActivity extends AppCompatActivity {

    Fragment videoFragment = new VideoFragment();
    Fragment noticiasFragment = new NoticiasFragment();
    Fragment homeFragment = new HomeFragment();
    public static boolean NOTIFICATION = false;
    public static boolean NOTIFICATION_NOITE = false;

    FragmentManager fm = getSupportFragmentManager();
    Fragment active = homeFragment;
    Context ctx;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ctx = this;

        clickMenu();
        fm.beginTransaction().add(R.id.main_container, noticiasFragment, "3").hide(noticiasFragment).commit();
        fm.beginTransaction().add(R.id.main_container, videoFragment, "2").hide(videoFragment).commit();
        fm.beginTransaction().add(R.id.main_container, homeFragment, "1").commit();
        active = homeFragment;

        //Notification:
        if (getIntent().hasExtra("notification")) {
            try {

                NOTIFICATION = true;

                Intent intentMeta = new Intent(ctx, ExerciciosActivity.class);
                startActivity(intentMeta);
            } catch (Exception e) {

            }
        }

        if (getIntent().hasExtra("notificationTarde")) {

            try {

                Intent intent = new Intent(ctx, FraseDiaActivity.class);
                startActivity(intent);
            } catch (Exception e) {

            }
        }

        if (getIntent().hasExtra("notificationNoite")) {

            try {

                NOTIFICATION_NOITE = true;

                Intent intentMeta = new Intent(ctx, ExerciciosActivity.class);
                startActivity(intentMeta);
            } catch (Exception e) {

            }
        }
    }

    private void clickMenu() {
        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.action_home:
                        clickHome();
                        break;
                    case R.id.action_noticia:
                        clickNoticias();
                        break;
                    case R.id.action_video:
                        clickVideos();
                        break;
                }
                return true;
            }
        });
    }

    private void clickHome() {
        fm.beginTransaction().hide(active).show(homeFragment).commit();
        active = homeFragment;
    }

    private void clickVideos() {
        fm.beginTransaction().hide(active).show(videoFragment).commit();
        active = videoFragment;
    }

    private void clickNoticias() {
        fm.beginTransaction().hide(active).show(noticiasFragment).commit();
        active = noticiasFragment;
    }
}