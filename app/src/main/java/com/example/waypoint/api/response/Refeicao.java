package com.example.waypoint.api.response;

import java.io.Serializable;

public class Refeicao implements Serializable {

    private int viagemid;
    private double custoRefeicao;
    private int refeicoesDia;
    private int idConta;

    public int getViagemid() {
        return viagemid;
    }

    public void setViagemid(int viagemid) {
        this.viagemid = viagemid;
    }

    public double getCustoRefeicao() {
        return custoRefeicao;
    }

    public void setCustoRefeicao(double custoRefeicao) {
        this.custoRefeicao = custoRefeicao;
    }

    public int getRefeicoesDia() {
        return refeicoesDia;
    }

    public void setRefeicoesDia(int refeicoesDia) {
        this.refeicoesDia = refeicoesDia;
    }

    public int getIdConta() {
        return idConta;
    }

    public void setIdConta(int idConta) {
        this.idConta = idConta;
    }
}
