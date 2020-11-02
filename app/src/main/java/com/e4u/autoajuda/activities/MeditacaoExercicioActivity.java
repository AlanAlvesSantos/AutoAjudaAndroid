package com.e4u.autoajuda.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.e4u.autoajuda.R;
import com.e4u.autoajuda.adapter.VideoAdapter;
import com.e4u.autoajuda.modelos.VideoModelo;

import java.util.ArrayList;
import java.util.List;
import static com.e4u.autoajuda.HomeActivity.NOTIFICATION_NOITE;

public class MeditacaoExercicioActivity extends AppCompatActivity {

    RecyclerView rvVideos;
    Activity activity;
    List<VideoModelo> listaVideos = new ArrayList<>();
    VideoAdapter adapter;
    TextView txtTecnicas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meditacao_exercicio);
        activity = this;
        rvVideos = findViewById(R.id.rvVideosMeditacao);
        txtTecnicas = findViewById(R.id.txtTecnicasMeditacao);
        NOTIFICATION_NOITE = false;

        txtTecnicas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(MeditacaoExercicioActivity.this, TecnicasMeditacaoActivity.class);
                startActivity(i);
            }
        });

        carregarLista();
    }

    private void carregarLista() {

        VideoModelo videoModelo = null;
        videoModelo = new VideoModelo();
        videoModelo.setVideoTitle("Meditação Guiada para acalmar a mente");
        videoModelo.setVideoURL("iN7dyWI01_g");
        listaVideos.add(videoModelo);

        videoModelo = new VideoModelo();
        videoModelo.setVideoTitle("Meditação para limpeza emocional");
        videoModelo.setVideoURL("zGwuWzCyqgs");
        listaVideos.add(videoModelo);

        videoModelo = new VideoModelo();
        videoModelo.setVideoTitle("Para Dormir e Acordar bem - Meditação Guiada");
        videoModelo.setVideoURL("HMIO68zdv4o");
        listaVideos.add(videoModelo);

        videoModelo = new VideoModelo();
        videoModelo.setVideoTitle("Meditação para Ansiedade");
        videoModelo.setVideoURL("mzAqzL2XIQA");
        listaVideos.add(videoModelo);

        adapter = new VideoAdapter(activity, listaVideos);

        if (listaVideos.size() > 0) {
            rvVideos.setVisibility(View.VISIBLE);
            RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getBaseContext(), 1);
            rvVideos.setLayoutManager(mLayoutManager);
            rvVideos.setItemAnimator(new DefaultItemAnimator());
            rvVideos.setAdapter(adapter);
        } else {
            rvVideos.setVisibility(View.INVISIBLE);
        }
    }
}