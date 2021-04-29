package com.e4u.autoajuda.activities;

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
import com.e4u.autoajuda.adapter.ObjetivoAdapter;
import com.e4u.autoajuda.modelos.DiarioModel;
import com.e4u.autoajuda.modelos.ItemListaModel;
import com.e4u.autoajuda.repositorio.DiarioRepositorio;
import com.e4u.autoajuda.repositorio.ItemListaRepository;
import com.e4u.autoajuda.utilidades.Utils;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class ObjetivosListaActivity extends AppCompatActivity {

    RecyclerView rvObjetivos;
    ObjetivoAdapter objetivoAdapter;
    List<ItemListaModel> listaMetas = new ArrayList<>();
    ItemListaRepository itemListaRepository;
    FloatingActionButton fabAddObjetivo;
    TextView txtListaMetasVazia;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_objetivos_lista);

        itemListaRepository = new ItemListaRepository(this);
        rvObjetivos = findViewById(R.id.rvObjetivos);
        txtListaMetasVazia = findViewById(R.id.txtListaMetasVazia);

        fabAddObjetivo = findViewById(R.id.fabAddObjetivo);

        fabAddObjetivo.setOnClickListener(v -> {

            //Chama a tela Salvar meta
            Intent i = new Intent(ObjetivosListaActivity.this, ObjetivosSalvarActivity.class);
            startActivityForResult(i, 10200);
        });


        itemListaRepository.getItemsByType(Utils.METAS).observe(this, new Observer<List<ItemListaModel>>() {
            @Override
            public void onChanged(@Nullable List<ItemListaModel> list) {

                listaMetas = list;
                carregarLista();
            }
        });
    }

    private void carregarLista() {

        objetivoAdapter = new ObjetivoAdapter(this, listaMetas);

        if (listaMetas.size() > 0) {
            rvObjetivos.setVisibility(View.VISIBLE);
            RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this, 1);
            rvObjetivos.setLayoutManager(mLayoutManager);
            rvObjetivos.setItemAnimator(new DefaultItemAnimator());
            rvObjetivos.setAdapter(objetivoAdapter);
            txtListaMetasVazia.setVisibility(View.GONE);
        } else {
            rvObjetivos.setVisibility(View.GONE);
            txtListaMetasVazia.setVisibility(View.VISIBLE);
        }
    }

    //retorna o resultado da tela chamada
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 10200) {

            itemListaRepository.getItemsByType(Utils.METAS).observe(this, new Observer<List<ItemListaModel>>() {
                @Override
                public void onChanged(@Nullable List<ItemListaModel> list) {

                    listaMetas = list;
                    carregarLista();
                }
            });
        }
    }
}