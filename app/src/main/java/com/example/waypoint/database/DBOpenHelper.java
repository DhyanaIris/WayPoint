package com.example.waypoint.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.waypoint.database.model.DadosGeraisModel;
import com.example.waypoint.database.model.UsuarioModel;

public class DBOpenHelper extends SQLiteOpenHelper {

    private static final String BANCO_NOME = "waypoint.db";
    private static final int VERSAO_BANCO = 1;

    public DBOpenHelper(Context context) {
        super(context, BANCO_NOME, null, VERSAO_BANCO);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(UsuarioModel.CREATE_TABLE);
        db.execSQL(DadosGeraisModel.CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
//        db.execSQL(ProdutoModel.DROP_TABLE);
//        db.execSQL(ProdutoModel.CREATE_TABLE);
//        db.execSQL(DadosGeraisModel.CREATE_TABLE);
    }
}