package com.example.waypoint.api.response;

import java.io.Serializable;

public class Entretenimento implements Serializable {

    private int viagemid;
    private String entretenimento;
    private double valor;
    private int idConta;

    public int getViagemid() {
        return viagemid;
    }

    public void setViagemid(int viagemid) {
        this.viagemid = viagemid;
    }

    public String getEntretenimento() {
        return entretenimento;
    }

    public void setEntretenimento(String entretenimento) {
        this.entretenimento = entretenimento;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public int getIdConta() {
        return idConta;
    }

    public void setIdConta(int idConta) {
        this.idConta = idConta;
    }
}
