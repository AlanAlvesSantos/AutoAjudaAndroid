package com.e4u.autoajuda.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.e4u.autoajuda.modelos.DiarioModel;

import java.util.List;

@Dao
public interface DiarioDAO {

    @Insert
    Long inserirDiario(DiarioModel diario);

    @Query("SELECT * FROM TB_DIARIO ORDER BY diarioID desc")
    LiveData<List<DiarioModel>> retornarDiario();

    @Query("SELECT * FROM TB_DIARIO WHERE diarioID=:id")
    LiveData<List<DiarioModel>> retornarDiarioAlterar(int id);

    @Update
    void alterarDiario(DiarioModel diarioModel);

    @Delete
    void excluirDiario(DiarioModel diarioModel);
}
