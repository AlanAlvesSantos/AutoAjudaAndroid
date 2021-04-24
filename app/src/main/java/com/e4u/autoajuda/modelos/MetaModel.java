package com.e4u.autoajuda.modelos;

public class MetaModel {

    private int metaID;
    private String descricao;
    private String data;
    private String status;

    public int getMetaID() {
        return metaID;
    }

    public void setMetaID(int metaID) {
        this.metaID = metaID;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao)
    {
        this.descricao = descricao;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
