package com.example.waypoint.database.dao;

import android.content.ContentValues;
import android.content.Context;

import com.example.waypoint.database.DBOpenHelper;
import com.example.waypoint.database.model.ViagemModel;

public class ViagemDAO extends AbstrataDAO {

    public ViagemDAO(Context context) {
        db_helper = new DBOpenHelper(context);
    }

    public long insertViagem(ViagemModel viagemModel) {
        long rowId = -1;

        try {
            Open();

            ContentValues values = new ContentValues();
            values.put(ViagemModel.COLUNA_ID_USUARIO, viagemModel.getIdUsuario());

            rowId = db.insert(ViagemModel.TABELA_NOME, null, values);
        } finally {
            Close();
        }

        return rowId;
    }
}
