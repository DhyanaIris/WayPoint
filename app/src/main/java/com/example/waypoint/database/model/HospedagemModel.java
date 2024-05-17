package com.example.waypoint.database.model;

public class HospedagemModel {

    public static String TABELA_NOME = "tb_hospedagem";

    public static String
            COLUNA_ID = "_id",
            COLUNA_CUSTO_MEDIO = "custo_medio",
            COLUNA_TOTAL_NOITES = "total_noites",
            COLUNA_TOTAL_QUARTOS= "total_quartos",
            COLUNA_TOTAL = "total",
            COLUNA_ID_USUARIO = "id_usuario";


    public static String CREATE_TABLE =
            "CREATE TABLE " + TABELA_NOME +
                    " ( "
                    + COLUNA_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + COLUNA_CUSTO_MEDIO + " FLOAT NOT NULL, "
                    + COLUNA_TOTAL_NOITES + " FLOAT NOT NULL, "
                    + COLUNA_TOTAL_QUARTOS + " FLOAT NOT NULL, "
                    + COLUNA_TOTAL + " FLOAT NOT NULL, "
                    + COLUNA_ID_USUARIO+" INTEGER, "
                    + " FOREIGN KEY (" + COLUNA_ID_USUARIO + ") REFERENCES " + UsuarioModel.TABELA_NOME + "(" + UsuarioModel.COLUNA_ID + ")"
                    + " )";

    public static String DROP_TABLE =
            "DROP TABLE IF EXISTS "+TABELA_NOME;

    private long id;
    private float custoMedio;
    private float totalNoites;
    private float totalQuartos;
    private float total;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public float getCustoMedio() {
        return custoMedio;
    }

    public void setCustoMedio(float custoMedio) {
        this.custoMedio = custoMedio;
    }

    public float getTotalNoites() {
        return totalNoites;
    }

    public void setTotalNoites(float totalNoites) {
        this.totalNoites = totalNoites;
    }

    public float getTotalQuartos() {
        return totalQuartos;
    }

    public void setTotalQuartos(float totalQuartos) {
        this.totalQuartos = totalQuartos;
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

    private long idUsuario;
}
