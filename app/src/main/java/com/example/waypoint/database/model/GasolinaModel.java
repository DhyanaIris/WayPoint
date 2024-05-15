package com.example.waypoint.database.model;

import static com.example.waypoint.database.model.DadosGeraisModel.COLUNA_DESTINO;

public class GasolinaModel {

    public static String TABELA_NOME = "tb_gasolina";

    public static String
            COLUNA_ID = "_id",
            COLUNA_KM_TOTAL = "km_total",
            COLUNA_MEDIA_KM_LITRO = "media_km_litro",
            COLUNA_CUSTO_LITRO = "custo_litro",
            COLUNA_TOTAL_VEICULOS = "total_veiculos",
            COLUNA_TOTAL = "total",
            COLUNA_ID_USUARIO = "id_usuario";


    public static String CREATE_TABLE =
            "CREATE TABLE " + TABELA_NOME +
                    " ( "
                    + COLUNA_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + COLUNA_KM_TOTAL + " FLOAT NOT NULL, "
                    + COLUNA_MEDIA_KM_LITRO + " FLOAT NOT NULL, "
                    + COLUNA_CUSTO_LITRO + " FLOAT NOT NULL, "
                    + COLUNA_TOTAL_VEICULOS + " FLOAT NOT NULL, "
                    + COLUNA_TOTAL + " TOTAL, "
                    + " FOREIGN KEY (" + COLUNA_ID_USUARIO + ") REFERENCES " + UsuarioModel.TABELA_NOME + "(" + UsuarioModel.COLUNA_ID + ")"
                    + " )";

    public static String DROP_TABLE =
            "DROP TABLE IF EXISTS "+TABELA_NOME;

    private long id;
    private int kmTotal;
    private float mediaKmLitro;
    private float custoLitro;
    private int totalVeiculos;
    private float total;
    private long idUsuario;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getKmTotal() {
        return kmTotal;
    }

    public void setKmTotal(int kmTotal) {
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

    public int getTotalVeiculos() {
        return totalVeiculos;
    }

    public void setTotalVeiculos(int totalVeiculos) {
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





}