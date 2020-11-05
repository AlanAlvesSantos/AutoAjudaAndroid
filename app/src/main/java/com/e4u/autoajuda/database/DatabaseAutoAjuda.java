package com.e4u.autoajuda.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import com.e4u.autoajuda.modelos.DiarioModel;

@Database(entities = {DiarioModel.class}, version = 1, exportSchema = false)
public abstract class DatabaseAutoAjuda extends RoomDatabase {

    public abstract DiarioDAO diarioDAO();
}
