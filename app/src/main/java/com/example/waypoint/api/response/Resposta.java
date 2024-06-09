package com.example.waypoint.api.response;

import java.io.Serializable;

public class Resposta implements Serializable {
    private boolean Sucesso;
    private String Mensagem;
    private String Dados;

    public boolean isSucesso() {
        return Sucesso;
    }

    public void setSucesso(boolean sucesso) {
        Sucesso = sucesso;
    }

    public String getMensagem() {
        return Mensagem;
    }

    public void setMensagem(String mensagem) {
        Mensagem = mensagem;
    }

    public String getDados() {
        return Dados;
    }

    public void setDados(String dados) {
        Dados = dados;
    }
}
