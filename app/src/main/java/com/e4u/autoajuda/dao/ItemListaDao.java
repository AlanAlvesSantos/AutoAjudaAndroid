package com.e4u.autoajuda.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.e4u.autoajuda.modelos.ItemListaModel;

import java.util.List;

/**
 * Created by Felipe Carvalho on 29-04-2021
 */

@Dao
public interface ItemListaDao {

    @Query("SELECT * FROM tb_item_lista where itemType = (:itemType) order by itemID desc")
    LiveData<List<ItemListaModel>> listAll(String itemType);

    @Query("SELECT * FROM tb_item_lista WHERE itemID IN (:itemID)")
    ItemListaModel getItemByID(int itemID);

    @Query("SELECT * FROM tb_item_lista WHERE itemTitle = (:title)")
    ItemListaModel getItemByTitle(String title);

    @Query("SELECT * FROM tb_item_lista WHERE itemType = (:itemType)")
    LiveData<List<ItemListaModel>> getItemsByType(String itemType);

    @Insert
    void insertOne(ItemListaModel... meta);

    @Delete
    void deleteOne(ItemListaModel meta);

    @Update
    public void updateOne(ItemListaModel... meta);
}

