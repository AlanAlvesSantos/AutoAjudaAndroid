package com.e4u.autoajuda.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.e4u.autoajuda.R;
import com.e4u.autoajuda.adapter.DiarioAdapter;
import com.e4u.autoajuda.adapter.NewsAdapter;
import com.e4u.autoajuda.database.DiarioRepositorio;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        diarioRepositorio = new DiarioRepositorio(this);
        setContentView(R.layout.activity_lista_diario);
        rvDiario = findViewById(R.id.rvDiario);

        fabAddDiario = findViewById(R.id.fabAddDiario);

        fabAddDiario.setOnClickListener(v -> {

            Intent i = new Intent(ListaDiarioActivity.this, SalvarDiarioActivity.class);
            startActivity(i);
        });

        carregarLista();
    }


    private void carregarLista() {

        diarioAdapter = new DiarioAdapter(this, listaDiario);

        if (listaDiario.size() > 0) {
            rvDiario.setVisibility(View.VISIBLE);
            RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this, 1);
            rvDiario.setLayoutManager(mLayoutManager);
            rvDiario.setItemAnimator(new DefaultItemAnimator());
            rvDiario.setAdapter(diarioAdapter);
        } else {
            rvDiario.setVisibility(View.INVISIBLE);
        }
    }
}