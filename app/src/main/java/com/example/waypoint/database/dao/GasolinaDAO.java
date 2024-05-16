package com.example.waypoint.database.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.example.waypoint.database.DBOpenHelper;
import com.example.waypoint.database.model.DadosGeraisModel;
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
            GasolinaModel.COLUNA_ID_USUARIO
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
            values.put(gasolinaModel.COLUNA_TOTAL, gasolinaModel.getTotal());
            values.put(GasolinaModel.COLUNA_ID_USUARIO, gasolinaModel.getIdUsuario());

            rowId = db.insert(DadosGeraisModel.TABELA_NOME, null, values);
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
            values.put(gasolinaModel.COLUNA_TOTAL, gasolinaModel.getTotal());
            values.put(GasolinaModel.COLUNA_ID_USUARIO, gasolinaModel.getIdUsuario());

            rowId  = db.update(
                    GasolinaModel.TABELA_NOME,
                    values,
                    DadosGeraisModel.COLUNA_ID + " = ?",
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
                    DadosGeraisModel.COLUNA_ID + " = ?",
                    new String[]{String.valueOf(gasolinaModel.getId())}
            );
        }
        finally {
            Close();
        }

        return rowId;
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
            gasolinaModel.setKmTotal(Float.parseFloat(c.getString(1)));
            gasolinaModel.setMediaKmLitro(c.getFloat(2));
            gasolinaModel.setCustoLitro(Float.parseFloat(c.getString(3)));
            gasolinaModel.setTotalVeiculos(Float.parseFloat(c.getString(4)));
            gasolinaModel.setIdUsuario(c.getInt(5));
            listaGasolina.add(gasolinaModel);
            c.moveToNext();
        }

        Close();

        return listaGasolina;
    }
}
