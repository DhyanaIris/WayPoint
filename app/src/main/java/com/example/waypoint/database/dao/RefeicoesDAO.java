package com.example.waypoint.database.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.example.waypoint.database.DBOpenHelper;
import com.example.waypoint.database.model.RefeicoesModel;

import java.util.ArrayList;

public class RefeicoesDAO extends AbstrataDAO {
    private final String[] colunas = {
            RefeicoesModel.COLUNA_ID,
            RefeicoesModel.COLUNA_CUSTO_REFEICAO,
            RefeicoesModel.COLUNA_REFEICOES_DIA,
            RefeicoesModel.COLUNA_TOTAL,
            RefeicoesModel.COLUNA_ID_USUARIO,
            RefeicoesModel.COLUNA_ID_VIAGEM,
    };

    public RefeicoesDAO(Context context) { db_helper = new DBOpenHelper(context);
    }

    public long Insert(RefeicoesModel refeicoesModel) {

        long rowId = -1;

        try {
            Open();

            ContentValues values = new ContentValues();
            values.put(RefeicoesModel.COLUNA_CUSTO_REFEICAO, refeicoesModel.getCustoRefeicao());
            values.put(RefeicoesModel.COLUNA_REFEICOES_DIA, refeicoesModel.getRefeicoesDia());
            values.put(RefeicoesModel.COLUNA_TOTAL, refeicoesModel.getTotal());
            values.put(RefeicoesModel.COLUNA_ID_USUARIO, refeicoesModel.getIdUsuario());
            values.put(RefeicoesModel.COLUNA_ID_VIAGEM, refeicoesModel.getIdViagem());

            rowId = db.insert(RefeicoesModel.TABELA_NOME, null, values);
        }
        finally {
            Close();
        }

        return rowId;
    }

    public long Update(RefeicoesModel refeicoesModel) {

        long rowId = -1;

        try {
            Open();

            ContentValues values = new ContentValues();
            values.put(RefeicoesModel.COLUNA_CUSTO_REFEICAO, refeicoesModel.getCustoRefeicao());
            values.put(RefeicoesModel.COLUNA_REFEICOES_DIA, refeicoesModel.getRefeicoesDia());
            values.put(RefeicoesModel.COLUNA_TOTAL, refeicoesModel.getTotal());

            rowId  = db.update(
                    RefeicoesModel.TABELA_NOME,
                    values,
                    RefeicoesModel.COLUNA_ID + " = ?",
                    new String[]{String.valueOf(refeicoesModel.getId())}
            );
        }
        finally {
            Close();
        }

        return rowId;
    }


    public long Delete(RefeicoesModel refeicoesModel) {

        long rowId = -1;

        try {
            Open();

            rowId = db.delete(
                    RefeicoesModel.TABELA_NOME,
                    RefeicoesModel.COLUNA_ID + " = ?",
                    new String[]{String.valueOf(refeicoesModel.getId())}
            );
        }
        finally {
            Close();
        }

        return rowId;
    }

    public ArrayList<RefeicoesModel> selectAll(long idUsuario) {

        Open();

        ArrayList<RefeicoesModel> listaRefeicoes = new ArrayList<>();

        Cursor c = db.query(
                RefeicoesModel.TABELA_NOME,
                colunas,
                RefeicoesModel.COLUNA_ID_USUARIO + " = ?",
                new String[]{String.valueOf(idUsuario)},
                null,
                null,
                null
        );

        c.moveToFirst();
        while (!c.isAfterLast()) {
            RefeicoesModel refeicoesModel = new RefeicoesModel();
            refeicoesModel.setId(c.getInt(0));
            refeicoesModel.setCustoRefeicao(c.getFloat(1));
            refeicoesModel.setRefeicoesDia(c.getFloat(2));
            refeicoesModel.setTotal(c.getFloat(3));
            refeicoesModel.setIdUsuario(c.getInt(4));
            refeicoesModel.setIdViagem(c.getInt(5));
            listaRefeicoes.add(refeicoesModel);
            c.moveToNext();
        }

        Close();

        return listaRefeicoes;
    }

    public ArrayList<RefeicoesModel> selectByViagemId(long idViagem) {

        Open();

        ArrayList<RefeicoesModel> listaRefeicoes = new ArrayList<>();

        Cursor c = db.query(
                RefeicoesModel.TABELA_NOME,
                colunas,
                RefeicoesModel.COLUNA_ID_VIAGEM + " = ?",
                new String[]{String.valueOf(idViagem)},
                null,
                null,
                null
        );

        if (c.moveToFirst()) {
            while (!c.isAfterLast()) {
                RefeicoesModel refeicoesModel = new RefeicoesModel();
                refeicoesModel.setId(c.getInt(0));
                refeicoesModel.setCustoRefeicao(c.getFloat(1));
                refeicoesModel.setRefeicoesDia(c.getFloat(2));
                refeicoesModel.setTotal(c.getFloat(3));
                refeicoesModel.setIdUsuario(c.getInt(4));
                refeicoesModel.setIdViagem(c.getInt(5));
                listaRefeicoes.add(refeicoesModel);
                c.moveToNext();
            }
        }

        c.close();
        Close();

        return listaRefeicoes;
    }
}
