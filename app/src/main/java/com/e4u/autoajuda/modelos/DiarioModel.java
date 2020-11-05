package com.e4u.autoajuda.modelos;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "TB_DIARIO")
public class DiarioModel {

    @PrimaryKey(autoGenerate = true)
    private int diarioID;

    private String data;

    private int avaliacao;

    private String textoDia;

    public int getDiarioID() {
        return diarioID;
    }

    public void setDiarioID(int diarioID) {
        this.diarioID = diarioID;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public int getAvaliacao() {
        return avaliacao;
    }

    public void setAvaliacao(int avaliacao) {
        this.avaliacao = avaliacao;
    }

    public String getTextoDia() {
        return textoDia;
    }

    public void setTextoDia(String textoDia) {
        this.textoDia = textoDia;
    }
}
