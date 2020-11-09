package com.e4u.autoajuda.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;

import com.e4u.autoajuda.R;
import com.e4u.autoajuda.database.DiarioRepositorio;
import com.e4u.autoajuda.modelos.DiarioModel;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class SalvarDiarioActivity extends AppCompatActivity {

    DiarioModel diarioModel;
    DiarioRepositorio repositorio;

    RatingBar rating;
    EditText edtDiario;
    Button btnSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_salvar_diario);

        rating = findViewById(R.id.rating);
        edtDiario = findViewById(R.id.edtDiario);
        btnSave = findViewById(R.id.btnSalvar);

        btnSave.setOnClickListener(v -> {

            salvarDiario();
        });
    }

    private void salvarDiario(){

        String currentDate = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());

        diarioModel = new DiarioModel();
        repositorio = new DiarioRepositorio(this);

        diarioModel.setTextoDia(edtDiario.getText().toString());
        diarioModel.setAvaliacao(rating.getRating());
        diarioModel.setData(currentDate);

        repositorio.inserirDiario(diarioModel);

    }
}