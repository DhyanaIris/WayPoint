package com.example.waypoint.database.model;

public class DadosGeraisModel {

    public static String TABELA_NOME = "tb_dados_gerais";

    public static String
            COLUNA_ID = "_id",
            COLUNA_NOME = "nome",
            COLUNA_VIAJANTES = "viajantes",
            COLUNA_DURACAO = "duracao",
            COLUNA_DESTINO = "destino",
            COLUNA_ID_USUARIO = "id_usuario",
            COLUNA_ID_VIAGEM = "id_viagem";


    public static String CREATE_TABLE =
            "CREATE TABLE "+TABELA_NOME+
                    " ( "
                    + COLUNA_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + COLUNA_NOME+" TEXT NOT NULL, "
                    + COLUNA_VIAJANTES+" FLOAT NOT NULL, "
                    + COLUNA_DURACAO+" FLOAT NOT NULL, "
                    + COLUNA_DESTINO+" TEXT NOT NULL, "
                    + COLUNA_ID_USUARIO+" INTEGER, "
                    + COLUNA_ID_VIAGEM+" INTEGER, "
                    + " FOREIGN KEY (" + COLUNA_ID_USUARIO + ") REFERENCES " + UsuarioModel.TABELA_NOME + "(" + UsuarioModel.COLUNA_ID + "), "
                    + " FOREIGN KEY (" + COLUNA_ID_VIAGEM + ") REFERENCES " + ViagemModel.TABELA_NOME + "(" + ViagemModel.COLUNA_ID + ")"
                    + " )";


    public static String DROP_TABLE =
            "DROP TABLE IF EXISTS "+TABELA_NOME;

    private long id;
    private String nomeViagem;
    private float viajantes;
    private float duracao;
    private String destino;
    private long idUsuario;
    private long idViagem;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNomeViagem() {
        return nomeViagem;
    }

    public void setNomeViagem(String nomeViagem) {
        this.nomeViagem = nomeViagem;
    }

    public float getViajantes() {
        return viajantes;
    }

    public void setViajantes(float viajantes) {
        this.viajantes = viajantes;
    }

    public float getDuracao() {
        return duracao;
    }

    public void setDuracao(float duracao) {
        this.duracao = duracao;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
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