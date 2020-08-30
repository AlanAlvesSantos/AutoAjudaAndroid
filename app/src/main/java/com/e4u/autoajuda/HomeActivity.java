package com.e4u.autoajuda;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.view.MenuItem;

import com.e4u.autoajuda.fragmentos.ExerciciosFragment;
import com.e4u.autoajuda.fragmentos.NoticiasFragment;
import com.e4u.autoajuda.fragmentos.VideoFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class HomeActivity extends AppCompatActivity {

    Fragment videoFragment = new VideoFragment();
    Fragment noticiasFragment = new NoticiasFragment();
    Fragment exerciciosFragment = new ExerciciosFragment();


    FragmentManager fm = getSupportFragmentManager();
    Fragment active = videoFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        fm.beginTransaction().add(R.id.main_container, noticiasFragment, "3").hide(noticiasFragment).commit();
        fm.beginTransaction().add(R.id.main_container, exerciciosFragment, "2").hide(exerciciosFragment).commit();
        fm.beginTransaction().add(R.id.main_container,videoFragment, "1").commit();


    }

    private void clickMenu() {
        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.action_exercicios:
                        clickExercicio();
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

    private void clickExercicio() {

        fm.beginTransaction().hide(active).show(exerciciosFragment).commit();
        active = exerciciosFragment;
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