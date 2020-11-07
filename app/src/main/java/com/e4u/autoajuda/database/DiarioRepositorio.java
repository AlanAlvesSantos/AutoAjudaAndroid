package com.e4u.autoajuda.database;

import android.content.Context;
import android.os.AsyncTask;

import androidx.room.Room;

import com.e4u.autoajuda.modelos.DiarioModel;

public class DiarioRepositorio {

    private String DB_NAME = "db_auto_ajuda";

    private DatabaseAutoAjuda noteDatabase;
    public DiarioRepositorio (Context context) {
        noteDatabase = Room.databaseBuilder (context, DatabaseAutoAjuda.class, DB_NAME) .build ();
    }

    public void inserirDiario (DiarioModel diario) {

        salvarDiario (diario);
    }

    private void salvarDiario (DiarioModel diario) {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground (Void ...
                                                   voids ) { noteDatabase.daoAccess (). insertTask (note);
                return null;
            }
        } .execute ();
    }

    public void updateTask (nota final) {
        note.setModifiedAt (AppUtils.getCurrentDateTime ());

        new AsyncTask <Void, Void, Void> () {
            @Override
            protected Void doInBackground (Void ...
                                                   voids ) { noteDatabase.daoAccess (). updateTask (nota);
                return null;
            }
        } .execute ();
    }

    public void deleteTask (final int id) {
        final LiveData <Note> task = getTask (id);
        if (task! = null) {
            new AsyncTask <Void, Void, Void> () {
                @Override
                protected Void doInBackground (Void ... voids) {
                    noteDatabase.daoAccess (). deleteTask (task.getValue ());
                    return null;
                }
            } .execute ();
        }
    }

    public void deleteTask (nota final) {
        new AsyncTask <Void, Void, Void> () {
            @Override
            protected Void doInBackground (Void ...
                                                   voids ) { noteDatabase.daoAccess (). deleteTask (note);
                return null;
            }
        } .execute ();
    }

    public LiveData <Nota> getTask (int id) {
        return noteDatabase.daoAccess (). getTask (id);
    }

    public LiveData <List <Note>> getTasks () {
        return noteDatabase.daoAccess (). fetchAllTasks ();
    }
}
