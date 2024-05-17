package com.example.waypoint.database.model;

public class TarifaAereaModel {

    public static String TABELA_NOME = "tb_tarifa_aerea";

    public static String
            COLUNA_ID = "_id",
            COLUNA_CUSTO_PESSOA = "custo_pessoa",
            COLUNA_ALUGUEL_VEICULO = "aluguel_veiculo",
            COLUNA_TOTAL = "total",
            COLUNA_ID_USUARIO = "id_usuario";


    public static String CREATE_TABLE =
            "CREATE TABLE " + TABELA_NOME +
                    " ( "
                    + COLUNA_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + COLUNA_CUSTO_PESSOA + " FLOAT NOT NULL, "
                    + COLUNA_ALUGUEL_VEICULO + " FLOAT NOT NULL, "
                    + COLUNA_TOTAL + " FLOAT NOT NULL, "
                    + COLUNA_ID_USUARIO+" INTEGER, "
                    + " FOREIGN KEY (" + COLUNA_ID_USUARIO + ") REFERENCES " + UsuarioModel.TABELA_NOME + "(" + UsuarioModel.COLUNA_ID + ")"
                    + " )";

    public static String DROP_TABLE =
            "DROP TABLE IF EXISTS "+TABELA_NOME;

    private long id;
    private float custoPessoa;
    private float aluguelVeiculo;
    private float total;
    private long idUsuario;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public float getCustoPessoa() {
        return custoPessoa;
    }

    public void setCustoPessoa(float custoPessoa) {
        this.custoPessoa = custoPessoa;
    }

    public float getAluguelVeiculo() {
        return aluguelVeiculo;
    }

    public void setAluguelVeiculo(float aluguelVeiculo) {
        this.aluguelVeiculo = aluguelVeiculo;
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
