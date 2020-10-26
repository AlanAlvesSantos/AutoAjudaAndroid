package com.e4u.autoajuda;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.e4u.autoajuda.fragmentos.HomeFragment;
import com.e4u.autoajuda.fragmentos.NoticiasFragment;
import com.e4u.autoajuda.fragmentos.VideoFragment;
import com.e4u.autoajuda.work.WorkRepo;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class HomeActivity extends AppCompatActivity {

    Fragment videoFragment = new VideoFragment();
    Fragment noticiasFragment = new NoticiasFragment();
    Fragment homeFragment = new HomeFragment();

    FragmentManager fm = getSupportFragmentManager();
    Fragment active = homeFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        clickMenu();
        fm.beginTransaction().add(R.id.main_container, noticiasFragment, "3").hide(noticiasFragment).commit();
        fm.beginTransaction().add(R.id.main_container, videoFragment, "2").hide(videoFragment).commit();
        fm.beginTransaction().add(R.id.main_container, homeFragment, "1").commit();
        active = homeFragment;

        WorkRepo workRepo = new WorkRepo();
        workRepo.startWorkNotifications();
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