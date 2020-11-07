package com.e4u.autoajuda.database;

import android.content.Context;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;
import androidx.room.Room;

import com.e4u.autoajuda.modelos.DiarioModel;

import java.util.List;

public class DiarioRepositorio {

    private String DB_NAME = "db_auto_ajuda";

    private DatabaseAutoAjuda bancodeDadosAutoAjuda;

    public DiarioRepositorio(Context context) {
        bancodeDadosAutoAjuda = Room.databaseBuilder(context, DatabaseAutoAjuda.class, DB_NAME).build();
    }

    //Salvar diario
    public void inserirDiario(DiarioModel diario) {

        salvarDiario(diario);
    }

    private void salvarDiario(DiarioModel diario) {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void...
                                                  voids) {

                bancodeDadosAutoAjuda.diarioDAO().inserirDiario(diario);
                return null;
            }
        }.execute();
    }


    //Alterar dados do diario
    public void alterarDiario(DiarioModel diarioModel) {

        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void...
                                                  voids) {

                bancodeDadosAutoAjuda.diarioDAO().alterarDiario(diarioModel);
                return null;
            }
        }.execute();
    }

    //Excluir diario

    public void excluirDiario(DiarioModel diarioModel) {

        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void...
                                                  voids) {

                bancodeDadosAutoAjuda.diarioDAO().excluirDiario(diarioModel);
                return null;
            }
        }.execute();
    }

    //Retornar Lista dos dias do diario
    public LiveData<List<DiarioModel>> retornarListaDiario() {

        return bancodeDadosAutoAjuda.diarioDAO().retornarDiario();
    }
}
