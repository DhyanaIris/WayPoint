package com.example.waypoint.database.model;

public class RefeicoesModel {
    public static String TABELA_NOME = "tb_refeicoes";

    public static String
            COLUNA_ID = "_id",
            COLUNA_CUSTO_REFEICAO= "custo_refeicao",
            COLUNA_REFEICOES_DIA = "refeicoes_dia",
            COLUNA_TOTAL = "total",
            COLUNA_ID_USUARIO = "id_usuario",
            COLUNA_ID_VIAGEM = "id_viagem";


    public static String CREATE_TABLE =
            "CREATE TABLE " + TABELA_NOME +
                    " ( "
                    + COLUNA_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + COLUNA_CUSTO_REFEICAO + " FLOAT NOT NULL, "
                    + COLUNA_REFEICOES_DIA + " FLOAT NOT NULL, "
                    + COLUNA_TOTAL + " FLOAT NOT NULL, "
                    + COLUNA_ID_USUARIO+" INTEGER, "
                    + COLUNA_ID_VIAGEM+" INTEGER, "
                    + " FOREIGN KEY (" + COLUNA_ID_USUARIO + ") REFERENCES " + UsuarioModel.TABELA_NOME + "(" + UsuarioModel.COLUNA_ID + "), "
                    + " FOREIGN KEY (" + COLUNA_ID_VIAGEM + ") REFERENCES " + ViagemModel.TABELA_NOME + "(" + ViagemModel.COLUNA_ID + ")"
                    + " )";
    public static String DROP_TABLE =
            "DROP TABLE IF EXISTS "+TABELA_NOME;

    private long id;
    private float custoRefeicao;
    private float refeicoesDia;
    private float total;
    private long idUsuario;
    private long idViagem;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public float getCustoRefeicao() {
        return custoRefeicao;
    }

    public void setCustoRefeicao(float custoRefeicao) {
        this.custoRefeicao = custoRefeicao;
    }

    public float getRefeicoesDia() {
        return refeicoesDia;
    }

    public void setRefeicoesDia(float refeicoesDia) {
        this.refeicoesDia = refeicoesDia;
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

    public long getIdViagem() {
        return idViagem;
    }

    public void setIdViagem(long idViagem) {
        this.idViagem = idViagem;
    }
}
