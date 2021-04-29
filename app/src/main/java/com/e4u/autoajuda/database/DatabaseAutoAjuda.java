package com.e4u.autoajuda.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.e4u.autoajuda.dao.DiarioDAO;
import com.e4u.autoajuda.dao.ItemListaDao;
import com.e4u.autoajuda.modelos.DiarioModel;
import com.e4u.autoajuda.modelos.ItemListaModel;

@Database(entities = {DiarioModel.class, ItemListaModel.class}, version = 2, exportSchema = false)
public abstract class DatabaseAutoAjuda extends RoomDatabase {

    private static String DB_NAME = "db_auto_ajuda";
    private static DatabaseAutoAjuda INSTANCE;

    public static DatabaseAutoAjuda getInstanceDB(final Context context) {
        if (INSTANCE == null) {
            synchronized (DatabaseAutoAjuda.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            DatabaseAutoAjuda.class, DB_NAME)
                            .allowMainThreadQueries()
                            .addMigrations(ALL_MIGRATIONS)
                            .build();

                }
            }
        }

        return INSTANCE;
    }

    public abstract DiarioDAO diarioDAO();

    public abstract ItemListaDao itemListaDao();

    static final Migration MIGRATION_1_2 = new Migration(1, 2) {
        @Override
        public void migrate(SupportSQLiteDatabase database) {
            database.execSQL("CREATE TABLE tb_item_lista (" +
                    "itemID INTEGER PRIMARY KEY NOT NULL," +
                    "itemTitle TEXT," +
                    "itemData TEXT," +
                    "itemImagem TEXT," +
                    "itemType TEXT," +
                    "itemDescription TEXT," +
                    "itemURL TEXT )");
        }
    };

    private static final Migration[] ALL_MIGRATIONS = new Migration[]{
            MIGRATION_1_2};
}
