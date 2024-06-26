package com.example.waypoint.database.model;

public class GasolinaModel {

    public static String TABELA_NOME = "tb_gasolina";

    public static String
            COLUNA_ID = "_id",
            COLUNA_KM_TOTAL = "km_total",
            COLUNA_MEDIA_KM_LITRO = "media_km_litro",
            COLUNA_CUSTO_LITRO = "custo_litro",
            COLUNA_TOTAL_VEICULOS = "total_veiculos",
            COLUNA_TOTAL = "total",
            COLUNA_ID_USUARIO = "id_usuario",
            COLUNA_ID_VIAGEM = "id_viagem";


    public static String CREATE_TABLE =
            "CREATE TABLE " + TABELA_NOME +
                    " ( "
                    + COLUNA_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + COLUNA_KM_TOTAL + " FLOAT NOT NULL, "
                    + COLUNA_MEDIA_KM_LITRO + " FLOAT NOT NULL, "
                    + COLUNA_CUSTO_LITRO + " FLOAT NOT NULL, "
                    + COLUNA_TOTAL_VEICULOS + " FLOAT NOT NULL, "
                    + COLUNA_TOTAL + " FLOAT NOT NULL, "
                    + COLUNA_ID_USUARIO+" INTEGER, "
                    + COLUNA_ID_VIAGEM+" INTEGER, "
                    + " FOREIGN KEY (" + COLUNA_ID_USUARIO + ") REFERENCES " + UsuarioModel.TABELA_NOME + "(" + UsuarioModel.COLUNA_ID + "), "
                    + " FOREIGN KEY (" + COLUNA_ID_VIAGEM + ") REFERENCES " + ViagemModel.TABELA_NOME + "(" + ViagemModel.COLUNA_ID + ")"
                    + " )";

    public static String DROP_TABLE =
            "DROP TABLE IF EXISTS "+TABELA_NOME;

    private long id;
    private float kmTotal;
    private float mediaKmLitro;
    private float custoLitro;
    private float totalVeiculos;
    private float total;
    private long idUsuario;
    private long idViagem;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public float getKmTotal() {
        return kmTotal;
    }

    public void setKmTotal(float kmTotal) {
        this.kmTotal = kmTotal;
    }

    public float getMediaKmLitro() {
        return mediaKmLitro;
    }

    public void setMediaKmLitro(float mediaKmLitro) {
        this.mediaKmLitro = mediaKmLitro;
    }

    public float getCustoLitro() {
        return custoLitro;
    }

    public void setCustoLitro(float custoLitro) {
        this.custoLitro = custoLitro;
    }

    public float getTotalVeiculos() {
        return totalVeiculos;
    }

    public void setTotalVeiculos(float totalVeiculos) {
        this.totalVeiculos = totalVeiculos;
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