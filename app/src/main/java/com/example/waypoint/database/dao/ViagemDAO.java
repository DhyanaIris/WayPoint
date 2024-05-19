package com.example.waypoint.database.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.example.waypoint.database.DBOpenHelper;
import com.example.waypoint.database.model.ViagemModel;

import java.util.ArrayList;

public class ViagemDAO extends AbstrataDAO {

    private final String[] colunas = {
            ViagemModel.COLUNA_ID,
            ViagemModel.COLUNA_TOTAL,
            ViagemModel.COLUNA_ID_USUARIO
    };

    public ViagemDAO(Context context) {
        db_helper = new DBOpenHelper(context);
    }

    public long Insert(ViagemModel viagemModel) {
        long rowId = -1;

        try {
            Open();

            ContentValues values = new ContentValues();
            values.put(ViagemModel.COLUNA_TOTAL, viagemModel.getTotal());
            values.put(ViagemModel.COLUNA_ID_USUARIO, viagemModel.getIdUsuario());

            rowId = db.insert(ViagemModel.TABELA_NOME, null, values);
        } finally {
            Close();
        }

        return rowId;
    }

    public long Update(ViagemModel viagemModel) {
        long rowId = -1;

        try {
            Open();

            ContentValues values = new ContentValues();
            values.put(ViagemModel.COLUNA_TOTAL, viagemModel.getTotal());

            rowId = db.update(
                    ViagemModel.TABELA_NOME,
                    values,
                    ViagemModel.COLUNA_ID + " = ?",
                    new String[]{String.valueOf(viagemModel.getId())}
            );
        } finally {
            Close();
        }

        return rowId;
    }

    public boolean Delete(long idViagem) {
        boolean isDeleted = false;

        try {
            Open();
            int rowsAffected = db.delete(ViagemModel.TABELA_NOME, ViagemModel.COLUNA_ID + " = ?", new String[]{String.valueOf(idViagem)});
            isDeleted = rowsAffected > 0;
        } finally {
            Close();
        }

        return isDeleted;
    }

    public ArrayList<ViagemModel> selectAll(long idUsuario) {
        ArrayList<ViagemModel> listaViagens = new ArrayList<>();

        try {
            Open();
            Cursor cursor = db.query(
                    ViagemModel.TABELA_NOME,
                    null, // Select all columns
                    ViagemModel.COLUNA_ID_USUARIO + " = ?",
                    new String[]{String.valueOf(idUsuario)},
                    null,
                    null,
                    null
            );

            if (cursor != null && cursor.moveToFirst()) {
                do {
                    ViagemModel viagem = new ViagemModel();
                    viagem.setId(cursor.getLong(cursor.getColumnIndexOrThrow(ViagemModel.COLUNA_ID)));
                    viagem.setTotal(cursor.getFloat(cursor.getColumnIndexOrThrow(ViagemModel.COLUNA_TOTAL)));
                    viagem.setIdUsuario(cursor.getLong(cursor.getColumnIndexOrThrow(ViagemModel.COLUNA_ID_USUARIO)));
                    listaViagens.add(viagem);
                } while (cursor.moveToNext());

                cursor.close();
            }
        } finally {
            Close();
        }

        return listaViagens;
    }

}
