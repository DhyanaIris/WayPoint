package com.example.waypoint.database.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.example.waypoint.database.DBOpenHelper;
import com.example.waypoint.database.model.DiversosModel;

import java.util.ArrayList;

public class DiversosDAO extends AbstrataDAO {

    private final String[] colunas = {
            DiversosModel.COLUNA_ID,
            DiversosModel.COLUNA_NOME_LOCAL,
            DiversosModel.COLUNA_CUSTO,
            DiversosModel.COLUNA_ID_USUARIO,
            DiversosModel.COLUNA_ID_VIAGEM,
    };

    public DiversosDAO(Context context) {
        db_helper = new DBOpenHelper(context);
    }

    public long Insert(DiversosModel diversosModel) {

        long rowId = -1;

        try {
            Open();

            ContentValues values = new ContentValues();
            values.put(DiversosModel.COLUNA_NOME_LOCAL, diversosModel.getNomeLocal());
            values.put(DiversosModel.COLUNA_CUSTO, diversosModel.getCusto());
            values.put(DiversosModel.COLUNA_ID_USUARIO, diversosModel.getIdUsuario());
            values.put(DiversosModel.COLUNA_ID_VIAGEM, diversosModel.getIdViagem());

            rowId = db.insert(DiversosModel.TABELA_NOME, null, values);
        }
        finally {
            Close();
        }

        return rowId;
    }

    public long Update(DiversosModel diversosModel) {

        long rowId = -1;

        try {
            Open();

            ContentValues values = new ContentValues();
            values.put(DiversosModel.COLUNA_NOME_LOCAL, diversosModel.getNomeLocal());
            values.put(DiversosModel.COLUNA_CUSTO, diversosModel.getCusto());

            rowId  = db.update(
                    DiversosModel.TABELA_NOME,
                    values,
                    DiversosModel.COLUNA_ID + " = ?",
                    new String[]{String.valueOf(diversosModel.getId())}
            );
        }
        finally {
            Close();
        }

        return rowId;
    }


    public long Delete(DiversosModel diversosModel) {

        long rowId = -1;

        try {
            Open();

            rowId = db.delete(
                    DiversosModel.TABELA_NOME,
                    DiversosModel.COLUNA_ID + " = ?",
                    new String[]{String.valueOf(diversosModel.getId())}
            );
        }
        finally {
            Close();
        }

        return rowId;
    }

    public ArrayList<DiversosModel> selectAll(long idUsuario) {

        Open();

        ArrayList<DiversosModel> listaDiversos = new ArrayList<>();

        Cursor c = db.query(
                DiversosModel.TABELA_NOME,
                colunas,
                DiversosModel.COLUNA_ID_USUARIO + " = ?",
                new String[]{String.valueOf(idUsuario)},
                null,
                null,
                null
        );

        c.moveToFirst();
        while (!c.isAfterLast()) {
            DiversosModel diversosModel = new DiversosModel();
            diversosModel.setId(c.getInt(0));
            diversosModel.setNomeLocal(c.getString(1));
            diversosModel.setCusto(c.getFloat(2));
            diversosModel.setIdUsuario(c.getInt(3));
            diversosModel.setIdViagem(c.getInt(4));
            listaDiversos.add(diversosModel);
            c.moveToNext();
        }

        Close();

        return listaDiversos;
    }
}
