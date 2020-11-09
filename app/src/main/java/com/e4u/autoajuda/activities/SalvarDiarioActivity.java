package com.e4u.autoajuda.activities;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

import com.e4u.autoajuda.R;
import com.e4u.autoajuda.database.DiarioRepositorio;
import com.e4u.autoajuda.modelos.DiarioModel;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class SalvarDiarioActivity extends AppCompatActivity {

    DiarioModel diarioModel;
    DiarioRepositorio repositorio;

    RatingBar rating;
    EditText edtDiario;
    Button btnSave;
    Context context;

    int idDiario = 0;
    float ratingValue;
    String textoDiario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_salvar_diario);

        context = this;
        rating = findViewById(R.id.rating);
        edtDiario = findViewById(R.id.edtDiario);
        btnSave = findViewById(R.id.btnSalvar);

        Intent intent = getIntent();
        idDiario = intent.getIntExtra("id", 0);
        textoDiario = intent.getStringExtra("texto");
        ratingValue = intent.getFloatExtra("rating", 0);

        if (textoDiario != null)
            edtDiario.setText(textoDiario);

        if (ratingValue > 0)
            rating.setRating(ratingValue);

        btnSave.setOnClickListener(v -> {

            if (edtDiario.getText().toString().equals(""))
                Toast.makeText(context, "Por favor, informe o texto do dia", Toast.LENGTH_LONG).show();
            else if (rating.getRating() == 0)
                Toast.makeText(context, "Por favor, informe a sua avaliação para o seu dia", Toast.LENGTH_LONG).show();
            else {

                if (idDiario == 0)
                    salvarDiario();
                else
                    alterarDiario();

                Toast.makeText(context, "Diário foi salvo com sucesso!", Toast.LENGTH_LONG).show();
            }
        });
    }

    private void salvarDiario() {

        String currentDate = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());

        diarioModel = new DiarioModel();
        repositorio = new DiarioRepositorio(this);

        diarioModel.setTextoDia(edtDiario.getText().toString());
        diarioModel.setAvaliacao(rating.getRating());
        diarioModel.setData(currentDate);

        repositorio.inserirDiario(diarioModel);

        //Fechar a tela
        finish();
    }

    private void alterarDiario() {

        diarioModel = new DiarioModel();
        repositorio = new DiarioRepositorio(this);

        repositorio.retornarDiario(idDiario).observe(this, new Observer<List<DiarioModel>>() {
            @Override
            public void onChanged(@Nullable List<DiarioModel> list) {

                if (list.size() > 0)
                    diarioModel = list.get(0);

                repositorio.retornarListaDiario();
                diarioModel.setDiarioID(idDiario);
                diarioModel.setTextoDia(edtDiario.getText().toString());
                diarioModel.setAvaliacao(rating.getRating());

                repositorio.alterarDiario(diarioModel);

                //Fechar a tela
                finish();
            }
        });
    }
}