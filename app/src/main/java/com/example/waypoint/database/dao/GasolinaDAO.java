package com.example.waypoint.database.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.example.waypoint.database.DBOpenHelper;
import com.example.waypoint.database.model.GasolinaModel;

import java.util.ArrayList;

public class GasolinaDAO extends AbstrataDAO{
    private final String[] colunas = {
            GasolinaModel.COLUNA_ID,
            GasolinaModel.COLUNA_KM_TOTAL,
            GasolinaModel.COLUNA_MEDIA_KM_LITRO,
            GasolinaModel.COLUNA_CUSTO_LITRO,
            GasolinaModel.COLUNA_TOTAL_VEICULOS,
            GasolinaModel.COLUNA_TOTAL,
            GasolinaModel.COLUNA_ID_USUARIO,
            GasolinaModel.COLUNA_ID_VIAGEM,
    };

    public GasolinaDAO(Context context) { db_helper = new DBOpenHelper(context);
    }

    public long Insert(GasolinaModel gasolinaModel) {

        long rowId = -1;

        try {
            Open();

            ContentValues values = new ContentValues();
            values.put(GasolinaModel.COLUNA_KM_TOTAL, gasolinaModel.getKmTotal());
            values.put(GasolinaModel.COLUNA_MEDIA_KM_LITRO, gasolinaModel.getMediaKmLitro());
            values.put(GasolinaModel.COLUNA_CUSTO_LITRO, gasolinaModel.getCustoLitro());
            values.put(GasolinaModel.COLUNA_TOTAL_VEICULOS, gasolinaModel.getTotalVeiculos());
            values.put(GasolinaModel.COLUNA_TOTAL, gasolinaModel.getTotal());
            values.put(GasolinaModel.COLUNA_ID_USUARIO, gasolinaModel.getIdUsuario());
            values.put(GasolinaModel.COLUNA_ID_VIAGEM, gasolinaModel.getIdViagem());

            rowId = db.insert(GasolinaModel.TABELA_NOME, null, values);
        }
        finally {
            Close();
        }

        return rowId;
    }

    public long Update(GasolinaModel gasolinaModel) {

        long rowId = -1;

        try {
            Open();

            ContentValues values = new ContentValues();
            values.put(GasolinaModel.COLUNA_KM_TOTAL, gasolinaModel.getKmTotal());
            values.put(GasolinaModel.COLUNA_MEDIA_KM_LITRO, gasolinaModel.getMediaKmLitro());
            values.put(GasolinaModel.COLUNA_CUSTO_LITRO, gasolinaModel.getCustoLitro());
            values.put(GasolinaModel.COLUNA_TOTAL_VEICULOS, gasolinaModel.getTotalVeiculos());
            values.put(GasolinaModel.COLUNA_TOTAL, gasolinaModel.getTotal());

            rowId  = db.update(
                    GasolinaModel.TABELA_NOME,
                    values,
                    GasolinaModel.COLUNA_ID + " = ?",
                    new String[]{String.valueOf(gasolinaModel.getId())}
            );
        }
        finally {
            Close();
        }

        return rowId;
    }


    public long Delete(GasolinaModel gasolinaModel) {

        long rowId = -1;

        try {
            Open();

            rowId = db.delete(
                    GasolinaModel.TABELA_NOME,
                    GasolinaModel.COLUNA_ID + " = ?",
                    new String[]{String.valueOf(gasolinaModel.getId())}
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
                    GasolinaModel.TABELA_NOME,
                    GasolinaModel.COLUNA_ID_VIAGEM + " = ?",
                    new String[]{String.valueOf(idViagem)}
            );
            return rowsAffected > 0;
        } finally {
            Close();
        }
    }

    public ArrayList<GasolinaModel> selectAll(long idUsuario) {

        Open();

        ArrayList<GasolinaModel> listaGasolina = new ArrayList<>();

        Cursor c = db.query(
                GasolinaModel.TABELA_NOME,
                colunas,
                GasolinaModel.COLUNA_ID_USUARIO + " = ?",
                new String[]{String.valueOf(idUsuario)},
                null,
                null,
                null
        );

        c.moveToFirst();
        while (!c.isAfterLast()) {
            GasolinaModel gasolinaModel = new GasolinaModel();
            gasolinaModel.setId(c.getInt(0));
            gasolinaModel.setKmTotal(c.getFloat(1));
            gasolinaModel.setMediaKmLitro(c.getFloat(2));
            gasolinaModel.setCustoLitro(c.getFloat(3));
            gasolinaModel.setTotalVeiculos(c.getFloat(4));
            gasolinaModel.setTotal(c.getFloat(5));
            gasolinaModel.setIdUsuario(c.getInt(6));
            gasolinaModel.setIdViagem(c.getInt(7));
            listaGasolina.add(gasolinaModel);
            c.moveToNext();
        }

        Close();

        return listaGasolina;
    }

    public ArrayList<GasolinaModel> selectByViagemId(long idViagem) {

        Open();

        ArrayList<GasolinaModel> listaGasolina = new ArrayList<>();

        Cursor c = db.query(
                GasolinaModel.TABELA_NOME,
                colunas,
                GasolinaModel.COLUNA_ID_VIAGEM + " = ?",
                new String[]{String.valueOf(idViagem)},
                null,
                null,
                null
        );

        if (c.moveToFirst()) {
            while (!c.isAfterLast()) {
                GasolinaModel gasolinaModel = new GasolinaModel();
                gasolinaModel.setId(c.getInt(0));
                gasolinaModel.setKmTotal(c.getFloat(1));
                gasolinaModel.setMediaKmLitro(c.getFloat(2));
                gasolinaModel.setCustoLitro(c.getFloat(3));
                gasolinaModel.setTotalVeiculos(c.getFloat(4));
                gasolinaModel.setTotal(c.getFloat(5));
                gasolinaModel.setIdUsuario(c.getInt(6));
                gasolinaModel.setIdViagem(c.getInt(7));
                listaGasolina.add(gasolinaModel);
                c.moveToNext();
            }
        }

        c.close();
        Close();

        return listaGasolina;
    }
}
