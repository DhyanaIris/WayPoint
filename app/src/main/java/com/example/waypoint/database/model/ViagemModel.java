package com.example.waypoint.database.model;

public class ViagemModel {

    public static final String TABELA_NOME = "tb_viagem";

    public static final String
            COLUNA_ID = "_id",
            COLUNA_TOTAL = "total",
            COLUNA_ID_USUARIO = "id_usuario";


    public static final String CREATE_TABLE =
            "CREATE TABLE " + TABELA_NOME + " ("
                    + COLUNA_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + COLUNA_TOTAL + " FLOAT, "
                    + COLUNA_ID_USUARIO + " INTEGER, "
                    + " FOREIGN KEY (" + COLUNA_ID_USUARIO + ") REFERENCES " + UsuarioModel.TABELA_NOME + "(" + UsuarioModel.COLUNA_ID + ")"
                    + " )";

    public static final String DROP_TABLE =
            "DROP TABLE IF EXISTS " + TABELA_NOME;

    private long id;
    private float total;
    private long idUsuario;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(long idUsuario) {
        this.idUsuario = idUsuario;
    }
}
