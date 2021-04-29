package com.e4u.autoajuda.repositorio;

import android.content.Context;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.e4u.autoajuda.dao.ItemListaDao;
import com.e4u.autoajuda.database.DatabaseAutoAjuda;
import com.e4u.autoajuda.modelos.ItemListaModel;

import java.util.List;

/**
 * Created by Felipe Carvalho on 29-04-2021
 */
public class ItemListaRepository {

    private ItemListaDao dao;

    public ItemListaRepository(Context context) {
        DatabaseAutoAjuda db = DatabaseAutoAjuda.getInstanceDB(context);
        dao = db.itemListaDao();
    }

    public LiveData<List<ItemListaModel>> listAll(String itemType) {
        return dao.listAll(itemType);
    }

    public ItemListaModel getItemByID(int id) {
        return dao.getItemByID(id);
    }

    public ItemListaModel getItemByTitle(String title) {
        return dao.getItemByTitle(title);
    }

    public LiveData<List<ItemListaModel>> getItemsByType(String itemType) {
        return dao.getItemsByType(itemType);
    }

    public void deleteOne(ItemListaModel item) {
        dao.deleteOne(item);
    }

    public void updateOne(ItemListaModel meta) {
        dao.updateOne(meta);
    }

    public void insertOne(ItemListaModel meta) {
        new ItemListaRepository.insertAsyncTask(dao).execute(meta);
    }

    private static class insertAsyncTask extends AsyncTask<ItemListaModel, Void, Void> {

        private ItemListaDao mAsyncTaskDao;

        insertAsyncTask(ItemListaDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final ItemListaModel... params) {
            mAsyncTaskDao.insertOne(params[0]);
            return null;
        }
    }
}
