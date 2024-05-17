package com.example.waypoint.database.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.example.waypoint.database.DBOpenHelper;
import com.example.waypoint.database.model.GasolinaModel;
import com.example.waypoint.database.model.HospedagemModel;

import java.util.ArrayList;

public class HospedagemDAO extends AbstrataDAO {

    private final String[] colunas = {
            HospedagemModel.COLUNA_ID,
            HospedagemModel.COLUNA_CUSTO_MEDIO,
            HospedagemModel.COLUNA_TOTAL_NOITES,
            HospedagemModel.COLUNA_TOTAL_QUARTOS,
            HospedagemModel.COLUNA_TOTAL,
            HospedagemModel.COLUNA_ID_USUARIO
    };

    public HospedagemDAO(Context context) { db_helper = new DBOpenHelper(context);
    }

    public long Insert(HospedagemModel hospedagemModel) {

        long rowId = -1;

        try {
            Open();

            ContentValues values = new ContentValues();
            values.put(HospedagemModel.COLUNA_CUSTO_MEDIO, hospedagemModel.getCustoMedio());
            values.put(HospedagemModel.COLUNA_TOTAL_NOITES, hospedagemModel.getTotalNoites());
            values.put(HospedagemModel.COLUNA_TOTAL_QUARTOS, hospedagemModel.getTotalQuartos());
            values.put(HospedagemModel.COLUNA_TOTAL, hospedagemModel.getTotal());
            values.put(HospedagemModel.COLUNA_ID_USUARIO, hospedagemModel.getIdUsuario());

            rowId = db.insert(HospedagemModel.TABELA_NOME, null, values);
        }
        finally {
            Close();
        }

        return rowId;
    }

    public long Update(HospedagemModel hospedagemModel) {

        long rowId = -1;

        try {
            Open();

            ContentValues values = new ContentValues();
            values.put(HospedagemModel.COLUNA_CUSTO_MEDIO, hospedagemModel.getCustoMedio());
            values.put(HospedagemModel.COLUNA_TOTAL_NOITES, hospedagemModel.getTotalNoites());
            values.put(HospedagemModel.COLUNA_TOTAL_QUARTOS, hospedagemModel.getTotalQuartos());
            values.put(HospedagemModel.COLUNA_TOTAL, hospedagemModel.getTotal());

            rowId  = db.update(
                    HospedagemModel.TABELA_NOME,
                    values,
                    HospedagemModel.COLUNA_ID + " = ?",
                    new String[]{String.valueOf(hospedagemModel.getId())}
            );
        }
        finally {
            Close();
        }

        return rowId;
    }


    public long Delete(HospedagemModel hospedagemModel) {

        long rowId = -1;

        try {
            Open();

            rowId = db.delete(
                    HospedagemModel.TABELA_NOME,
                    HospedagemModel.COLUNA_ID + " = ?",
                    new String[]{String.valueOf(hospedagemModel.getId())}
            );
        }
        finally {
            Close();
        }

        return rowId;
    }

    public ArrayList<HospedagemModel> selectAll(long idUsuario) {

        Open();

        ArrayList<HospedagemModel> listaHospedagem = new ArrayList<>();

        Cursor c = db.query(
                HospedagemModel.TABELA_NOME,
                colunas,
                HospedagemModel.COLUNA_ID_USUARIO + " = ?",
                new String[]{String.valueOf(idUsuario)},
                null,
                null,
                null
        );

        c.moveToFirst();
        while (!c.isAfterLast()) {
            HospedagemModel hospedagemModel = new HospedagemModel();
            hospedagemModel.setId(c.getInt(0));
            hospedagemModel.setCustoMedio(c.getFloat(1));
            hospedagemModel.setTotalNoites(c.getFloat(2));
            hospedagemModel.setTotalQuartos(c.getFloat(3));
            hospedagemModel.setIdUsuario(c.getInt(4));
            listaHospedagem.add(hospedagemModel);
            c.moveToNext();
        }

        Close();

        return listaHospedagem;
    }
}
