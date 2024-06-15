package com.example.waypoint.api.response;

import java.io.Serializable;

public class Aereo implements Serializable {

    private int viagemId;
    private double custoPessoa;
    private double custoAluguelVeiculo;
    private int idConta;

    public int getViagemId() {
        return viagemId;
    }

    public void setViagemId(int viagemId) {
        this.viagemId = viagemId;
    }

    public double getCustoPessoa() {
        return custoPessoa;
    }

    public void setCustoPessoa(double custoPessoa) {
        this.custoPessoa = custoPessoa;
    }

    public double getCustoAluguelVeiculo() {
        return custoAluguelVeiculo;
    }

    public void setCustoAluguelVeiculo(double custoAluguelVeiculo) {
        this.custoAluguelVeiculo = custoAluguelVeiculo;
    }

    public int getIdConta() {
        return idConta;
    }

    public void setIdConta(int idConta) {
        this.idConta = idConta;
    }
}
