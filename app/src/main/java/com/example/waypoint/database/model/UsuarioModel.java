package com.example.waypoint.database.model;

public class UsuarioModel {

    public static String TABELA_NOME = "tb_usuario";

    public static String
            COLUNA_ID = "_id",
            COLUNA_NOME_USUARIO = "usuario_nome",
            COLUNA_SENHA = "senha";

    public static String CREATE_TABLE =
            "CREATE TABLE "+TABELA_NOME+
                    " ( "
                    + COLUNA_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + COLUNA_NOME_USUARIO+" TEXT NOT NULL, "
                    + COLUNA_SENHA+" TEXT NOT NULL "
                    +" );";

    public static String DROP_TABLE =
            "DROP TABLE IF EXISTS "+TABELA_NOME;

    private long id;
    private String nomeUsuario;
    private String senha;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNomeUsuario() {
        return nomeUsuario;
    }

    public void setNomeUsuario(String nomeUsuario) {
        this.nomeUsuario = nomeUsuario;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}