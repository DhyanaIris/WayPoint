package com.example.waypoint.database.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.example.waypoint.database.DBOpenHelper;
import com.example.waypoint.database.model.UsuarioModel;

import java.util.ArrayList;

public class UsuarioDAO extends AbstrataDAO {

    private final String[] colunas = {
            UsuarioModel.COLUNA_ID,
            UsuarioModel.COLUNA_NOME_USUARIO,
            UsuarioModel.COLUNA_SENHA,
    };

    public UsuarioDAO(Context context) {
        db_helper = new DBOpenHelper(context);
    }

    public long Insert(UsuarioModel usuarioModel) {

        long rowId = -1;

        try {
            Open();

            ContentValues values = new ContentValues();
            values.put(UsuarioModel.COLUNA_NOME_USUARIO, usuarioModel.getNomeUsuario());
            values.put(UsuarioModel.COLUNA_SENHA, usuarioModel.getSenha());

            rowId = db.insert(UsuarioModel.TABELA_NOME, null, values);
        }
        finally {
            Close();
        }

        return rowId;
    }

//    public long Update(UsuarioModel usuarioModel) {
//
//        long rowId = -1;
//
//        try {
//            Open();
//
//            ContentValues values = new ContentValues();
//            values.put(UsuarioModel.COLUNA_DESCRICAO_PRODUTO, produtoModel.getDescricaoProduto());
//
//            rowId = db.update
//                    (
//                            ProdutoModel.TABELA_NOME,
//                            values,
//                            ProdutoModel.COLUNA_PRODUTO+" =?",
//                            new String[]{produtoModel.getProduto()}
//                    );
//        }
//        finally {
//            Close();
//        }
//
//        return rowId;
//    }


    public long Delete(UsuarioModel usuarioModel) {

        long rowId = -1;

        try {
            Open();

            rowId = db.delete
                    (
                            UsuarioModel.TABELA_NOME,
                            UsuarioModel.COLUNA_NOME_USUARIO+" =?",
                            new String[]{usuarioModel.getNomeUsuario()}
                    );
        }
        finally {
            Close();
        }

        return rowId;
    }

    public ArrayList<UsuarioModel> selectAll() {

        Open();

        ArrayList<UsuarioModel> listaUsuario = new ArrayList<UsuarioModel>();

        Cursor c = db.query
                (
                        UsuarioModel.TABELA_NOME,
                        colunas,
                        null,
                        null,
                        null,
                        null,
                        null
                );
        c.moveToFirst();
        while (!c.isAfterLast()) {
            UsuarioModel usuarioModel = new UsuarioModel();
            usuarioModel.setId(c.getInt(0));
            usuarioModel.setNomeUsuario(c.getString(1));
            usuarioModel.setSenha(c.getString(2));
            listaUsuario.add(usuarioModel);
            c.moveToNext();
        }

        Close();

        return listaUsuario;
    }

    public boolean verificarUsuarioExistente(String nomeUsuario) {
        Open();

        Cursor c = db.query(
                UsuarioModel.TABELA_NOME,
                new String[]{UsuarioModel.COLUNA_NOME_USUARIO},
                UsuarioModel.COLUNA_NOME_USUARIO + " = ?",
                new String[]{nomeUsuario},
                null,
                null,
                null
        );

        boolean usuarioExiste = c.getCount() > 0;

        c.close();
        Close();

        return usuarioExiste;
    }

    public boolean validarCredenciais(String nomeUsuario, String senha) {
        Open();

        Cursor c = db.query(
                UsuarioModel.TABELA_NOME,
                new String[]{UsuarioModel.COLUNA_NOME_USUARIO},
                UsuarioModel.COLUNA_NOME_USUARIO + " = ? AND " + UsuarioModel.COLUNA_SENHA + " = ?",
                new String[]{nomeUsuario, senha},
                null,
                null,
                null
        );

        boolean credenciaisValidas = c.getCount() > 0;

        c.close();
        Close();

        return credenciaisValidas;
    }

    public long getIdUsuario(String nomeUsuario) {
        Open();

        long idUsuario = -1;

        Cursor c = db.query(
                UsuarioModel.TABELA_NOME,
                new String[]{UsuarioModel.COLUNA_ID},
                UsuarioModel.COLUNA_NOME_USUARIO + " = ?",
                new String[]{nomeUsuario},
                null,
                null,
                null
        );

        int columnIndex = c.getColumnIndex(UsuarioModel.COLUNA_ID);

        if (c.moveToFirst()) {
            idUsuario = c.getLong(columnIndex);
        }

        c.close();
        Close();

        return idUsuario;
    }
}