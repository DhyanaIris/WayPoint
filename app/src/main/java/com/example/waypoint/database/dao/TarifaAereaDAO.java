package com.example.waypoint.database.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.example.waypoint.database.DBOpenHelper;
import com.example.waypoint.database.model.TarifaAereaModel;

import java.util.ArrayList;

public class TarifaAereaDAO extends AbstrataDAO {

    private final String[] colunas = {
            TarifaAereaModel.COLUNA_ID,
            TarifaAereaModel.COLUNA_CUSTO_PESSOA,
            TarifaAereaModel.COLUNA_ALUGUEL_VEICULO,
            TarifaAereaModel.COLUNA_TOTAL,
            TarifaAereaModel.COLUNA_ID_USUARIO,
            TarifaAereaModel.COLUNA_ID_VIAGEM,
    };

    public TarifaAereaDAO(Context context) { db_helper = new DBOpenHelper(context);
    }

    public long Insert(TarifaAereaModel tarifaAereaModel) {

        long rowId = -1;

        try {
            Open();

            ContentValues values = new ContentValues();
            values.put(TarifaAereaModel.COLUNA_CUSTO_PESSOA, tarifaAereaModel.getCustoPessoa());
            values.put(TarifaAereaModel.COLUNA_ALUGUEL_VEICULO, tarifaAereaModel.getAluguelVeiculo());
            values.put(TarifaAereaModel.COLUNA_TOTAL, tarifaAereaModel.getTotal());
            values.put(TarifaAereaModel.COLUNA_ID_USUARIO, tarifaAereaModel.getIdUsuario());
            values.put(TarifaAereaModel.COLUNA_ID_VIAGEM, tarifaAereaModel.getIdViagem());

            rowId = db.insert(TarifaAereaModel.TABELA_NOME, null, values);
        }
        finally {
            Close();
        }

        return rowId;
    }

    public long Update(TarifaAereaModel tarifaAereaModel) {

        long rowId = -1;

        try {
            Open();

            ContentValues values = new ContentValues();
            values.put(TarifaAereaModel.COLUNA_CUSTO_PESSOA, tarifaAereaModel.getCustoPessoa());
            values.put(TarifaAereaModel.COLUNA_ALUGUEL_VEICULO, tarifaAereaModel.getAluguelVeiculo());
            values.put(TarifaAereaModel.COLUNA_TOTAL, tarifaAereaModel.getTotal());

            rowId  = db.update(
                    TarifaAereaModel.TABELA_NOME,
                    values,
                    TarifaAereaModel.COLUNA_ID + " = ?",
                    new String[]{String.valueOf(tarifaAereaModel.getId())}
            );
        }
        finally {
            Close();
        }

        return rowId;
    }


    public long Delete(TarifaAereaModel tarifaAereaModel) {

        long rowId = -1;

        try {
            Open();

            rowId = db.delete(
                    TarifaAereaModel.TABELA_NOME,
                    TarifaAereaModel.COLUNA_ID + " = ?",
                    new String[]{String.valueOf(tarifaAereaModel.getId())}
            );
        }
        finally {
            Close();
        }

        return rowId;
    }

    public boolean DeleteById(long idViagem) {
        try {
            Open();
            int rowsAffected = db.delete(
                    TarifaAereaModel.TABELA_NOME,
                    TarifaAereaModel.COLUNA_ID_VIAGEM + " = ?",
                    new String[]{String.valueOf(idViagem)}
            );
            return rowsAffected > 0;
        } finally {
            Close();
        }
    }

    public ArrayList<TarifaAereaModel> selectAll(long idUsuario) {

        Open();

        ArrayList<TarifaAereaModel> listaTarifaAerea = new ArrayList<>();

        Cursor c = db.query(
                TarifaAereaModel.TABELA_NOME,
                colunas,
                TarifaAereaModel.COLUNA_ID_USUARIO + " = ?",
                new String[]{String.valueOf(idUsuario)},
                null,
                null,
                null
        );

        c.moveToFirst();
        while (!c.isAfterLast()) {
            TarifaAereaModel tarifaAereaModel = new TarifaAereaModel();
            tarifaAereaModel.setId(c.getInt(0));
            tarifaAereaModel.setCustoPessoa(c.getFloat(1));
            tarifaAereaModel.setAluguelVeiculo(c.getFloat(2));
            tarifaAereaModel.setTotal(c.getFloat(3));
            tarifaAereaModel.setIdUsuario(c.getInt(4));
            tarifaAereaModel.setIdViagem(c.getInt(5));
            listaTarifaAerea.add(tarifaAereaModel);
            c.moveToNext();
        }

        Close();

        return listaTarifaAerea;
    }

    public ArrayList<TarifaAereaModel> selectByViagemId(long idViagem) {

        Open();

        ArrayList<TarifaAereaModel> listaTarifaAerea = new ArrayList<>();

        Cursor c = db.query(
                TarifaAereaModel.TABELA_NOME,
                colunas,
                TarifaAereaModel.COLUNA_ID_VIAGEM + " = ?",
                new String[]{String.valueOf(idViagem)},
                null,
                null,
                null
        );

        if (c.moveToFirst()) {
            while (!c.isAfterLast()) {
                TarifaAereaModel tarifaAereaModel = new TarifaAereaModel();
                tarifaAereaModel.setId(c.getInt(0));
                tarifaAereaModel.setCustoPessoa(c.getFloat(1));
                tarifaAereaModel.setAluguelVeiculo(c.getFloat(2));
                tarifaAereaModel.setTotal(c.getFloat(3));
                tarifaAereaModel.setIdUsuario(c.getInt(4));
                tarifaAereaModel.setIdViagem(c.getInt(5));
                listaTarifaAerea.add(tarifaAereaModel);
                c.moveToNext();
            }
        }

        c.close();
        Close();

        return listaTarifaAerea;
    }
}
