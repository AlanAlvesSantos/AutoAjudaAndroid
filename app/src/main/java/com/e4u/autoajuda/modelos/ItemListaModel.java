package com.e4u.autoajuda.modelos;


import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * Created by Felipe Carvalho on 29-04-2021
 */


@Entity(tableName = "tb_item_lista")
public class ItemListaModel implements Comparable<ItemListaModel> {

    @PrimaryKey(autoGenerate = true)
    private int itemID;

    private String itemTitle;
    private String itemData;
    private String itemImagem;
    private String itemType;
    private String itemDescription;
    private String itemURL;

    public int getItemID() {
        return itemID;
    }

    public void setItemID(int itemID) {
        this.itemID = itemID;
    }

    public String getItemTitle() {
        return itemTitle;
    }

    public void setItemTitle(String itemTitle) {
        this.itemTitle = itemTitle;
    }

    public String getItemData() {
        return itemData;
    }

    public void setItemData(String itemData) {
        this.itemData = itemData;
    }

    public String getItemImagem() {
        return itemImagem;
    }

    public void setItemImagem(String itemImagem) {
        this.itemImagem = itemImagem;
    }

    public String getItemType() {
        return itemType;
    }

    public void setItemType(String itemType) {
        this.itemType = itemType;
    }

    public String getItemDescription() {
        return itemDescription;
    }

    public void setItemDescription(String itemDescription) {
        this.itemDescription = itemDescription;
    }

    public String getItemURL() {
        return itemURL;
    }

    public void setItemURL(String itemURL) {
        this.itemURL = itemURL;
    }

    @Override
    public int compareTo(ItemListaModel u) {
        if (getItemTitle() == null || u.getItemTitle() == null) {
            return 0;
        }
        return getItemTitle().compareTo(u.getItemTitle());
    }
}