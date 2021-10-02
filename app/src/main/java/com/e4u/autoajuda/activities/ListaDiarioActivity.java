package com.e4u.autoajuda.activities;

import static com.e4u.autoajuda.MainActivity.mInterstitialAd;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.e4u.autoajuda.R;
import com.e4u.autoajuda.adapter.DiarioAdapter;
import com.e4u.autoajuda.repositorio.DiarioRepositorio;
import com.e4u.autoajuda.modelos.DiarioModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class ListaDiarioActivity extends AppCompatActivity {

    RecyclerView rvDiario;
    DiarioAdapter diarioAdapter;
    List<DiarioModel> listaDiario = new ArrayList<>();
    DiarioRepositorio diarioRepositorio;
    FloatingActionButton fabAddDiario;
    TextView txtListaVazia;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        diarioRepositorio = new DiarioRepositorio(this);
        setContentView(R.layout.activity_lista_diario);
        rvDiario = findViewById(R.id.rvDiario);
        txtListaVazia = findViewById(R.id.txtListaVazia);

        fabAddDiario = findViewById(R.id.fabAddDiario);

        fabAddDiario.setOnClickListener(v -> {

            //Chama a tela Salvar diario
            Intent i = new Intent(ListaDiarioActivity.this, SalvarDiarioActivity.class);
            startActivityForResult(i, 10200);
        });


        diarioRepositorio.retornarListaDiario().observe(this, new Observer<List<DiarioModel>>() {
            @Override
            public void onChanged(@Nullable List<DiarioModel> list) {

                listaDiario = list;
                carregarLista();
            }
        });

        try {
            if (mInterstitialAd.isLoaded()) {
                mInterstitialAd.show();
            }
        } catch (Exception ex) {
        }
    }

    private void carregarLista() {

        diarioAdapter = new DiarioAdapter(this, listaDiario);

        if (listaDiario.size() > 0) {
            rvDiario.setVisibility(View.VISIBLE);
            RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this, 1);
            rvDiario.setLayoutManager(mLayoutManager);
            rvDiario.setItemAnimator(new DefaultItemAnimator());
            rvDiario.setAdapter(diarioAdapter);
            txtListaVazia.setVisibility(View.GONE);
        } else {
            rvDiario.setVisibility(View.GONE);
            txtListaVazia.setVisibility(View.VISIBLE);
        }
    }

    //retorna o resultado da tela chamada
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 10200) {

            diarioRepositorio.retornarListaDiario().observe(this, new Observer<List<DiarioModel>>() {
                @Override
                public void onChanged(@Nullable List<DiarioModel> list) {

                    listaDiario = list;
                    carregarLista();
                }
            });
        }
    }
}