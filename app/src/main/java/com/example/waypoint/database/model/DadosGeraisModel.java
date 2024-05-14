package com.example.waypoint.database.model;

public class DadosGeraisModel {

    public static String TABELA_NOME = "tb_dados_gerais";

    public static String
            COLUNA_ID = "_id",
            COLUNA_NOME = "nome",
            COLUNA_VIAJANTES = "viajantes",
            COLUNA_DURACAO = "duracao",
            COLUNA_DESTINO = "destino",
            COLUNA_ID_USUARIO = "id_usuario";


    public static String CREATE_TABLE =
            "CREATE TABLE "+TABELA_NOME+
                    " ( "
                    + COLUNA_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + COLUNA_NOME+" TEXT NOT NULL, "
                    + COLUNA_VIAJANTES+" INTEGER NOT NULL, "
                    + COLUNA_DURACAO+" TEXT NOT NULL, "
                    + COLUNA_DESTINO+" TEXT NOT NULL, "
                    + COLUNA_ID_USUARIO+" INTEGER, "
                    + " FOREIGN KEY (" + COLUNA_ID_USUARIO + ") REFERENCES " + UsuarioModel.TABELA_NOME + "(" + UsuarioModel.COLUNA_ID + ")"
                    + " )";


    public static String DROP_TABLE =
            "DROP TABLE IF EXISTS "+TABELA_NOME;

    private long id;
    private String nomeViagem;
    private int viajantes;
    private String duracao;
    private String destino;
    private long idUsuario;

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

    public int getViajantes() {
        return viajantes;
    }

    public void setViajantes(int viajantes) {
        this.viajantes = viajantes;
    }

    public String getDuracao() {
        return duracao;
    }

    public void setDuracao(String duracao) {
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
}