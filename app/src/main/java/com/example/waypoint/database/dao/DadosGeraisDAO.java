package com.example.waypoint.database.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.example.waypoint.database.DBOpenHelper;
import com.example.waypoint.database.model.DadosGeraisModel;

import java.util.ArrayList;

public class DadosGeraisDAO extends AbstrataDAO {

    private final String[] colunas = {
            DadosGeraisModel.COLUNA_ID,
            DadosGeraisModel.COLUNA_NOME,
            DadosGeraisModel.COLUNA_VIAJANTES,
            DadosGeraisModel.COLUNA_DURACAO,
            DadosGeraisModel.COLUNA_DESTINO,
            DadosGeraisModel.COLUNA_ID_USUARIO
    };

    public DadosGeraisDAO(Context context) {
        db_helper = new DBOpenHelper(context);
    }

    public long Insert(DadosGeraisModel dadosGeraisModel) {

        long rowId = -1;

        try {
            Open();

            ContentValues values = new ContentValues();
            values.put(DadosGeraisModel.COLUNA_NOME, dadosGeraisModel.getNomeViagem());
            values.put(DadosGeraisModel.COLUNA_VIAJANTES, dadosGeraisModel.getViajantes());
            values.put(DadosGeraisModel.COLUNA_DURACAO, dadosGeraisModel.getDuracao());
            values.put(DadosGeraisModel.COLUNA_DESTINO, dadosGeraisModel.getDestino());
            values.put(DadosGeraisModel.COLUNA_ID_USUARIO, dadosGeraisModel.getIdUsuario());

            rowId = db.insert(DadosGeraisModel.TABELA_NOME, null, values);
        }
        finally {
            Close();
        }

        return rowId;
    }

    public long Update(DadosGeraisModel dadosGeraisModel) {

        long rowId = -1;

        try {
            Open();

            ContentValues values = new ContentValues();
            values.put(DadosGeraisModel.COLUNA_NOME, dadosGeraisModel.getNomeViagem());
            values.put(DadosGeraisModel.COLUNA_VIAJANTES, dadosGeraisModel.getViajantes());
            values.put(DadosGeraisModel.COLUNA_DURACAO, dadosGeraisModel.getDuracao());
            values.put(DadosGeraisModel.COLUNA_DESTINO, dadosGeraisModel.getDestino());

            rowId  = db.update(
                    DadosGeraisModel.TABELA_NOME,
                    values,
                    DadosGeraisModel.COLUNA_ID + " = ?",
                    new String[]{String.valueOf(dadosGeraisModel.getId())}
            );
        }
        finally {
            Close();
        }

        return rowId;
    }


    public long Delete(DadosGeraisModel dadosGeraisModel) {

        long rowId = -1;

        try {
            Open();

            rowId = db.delete(
                    DadosGeraisModel.TABELA_NOME,
                    DadosGeraisModel.COLUNA_ID + " = ?",
                    new String[]{String.valueOf(dadosGeraisModel.getId())}
            );
        }
        finally {
            Close();
        }

        return rowId;
    }

    public ArrayList<DadosGeraisModel> selectAll(long idUsuario) {

        Open();

        ArrayList<DadosGeraisModel> listaDadosGerais = new ArrayList<>();

        Cursor c = db.query(
                DadosGeraisModel.TABELA_NOME,
                colunas,
                DadosGeraisModel.COLUNA_ID_USUARIO + " = ?",
                new String[]{String.valueOf(idUsuario)},
                null,
                null,
                null
        );

        c.moveToFirst();
        while (!c.isAfterLast()) {
            DadosGeraisModel dadosGeraisModel = new DadosGeraisModel();
            dadosGeraisModel.setId(c.getInt(0));
            dadosGeraisModel.setNomeViagem(c.getString(1));
            dadosGeraisModel.setViajantes(c.getInt(2));
            dadosGeraisModel.setDuracao(c.getString(3));
            dadosGeraisModel.setDestino(c.getString(4));
            dadosGeraisModel.setIdUsuario(c.getInt(5));
            listaDadosGerais.add(dadosGeraisModel);
            c.moveToNext();
        }

        Close();

        return listaDadosGerais;
    }
}