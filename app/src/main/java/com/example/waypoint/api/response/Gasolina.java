package com.example.waypoint.api.response;

import java.io.Serializable;

public class Gasolina implements Serializable {

    private int viagemid;
    private int totalEstimadoKM;
    private double mediaKMLitro;
    private double custoMedioLitro;
    private int totalVeiculos;
    private int idConta;

    public int getViagemid() {
        return viagemid;
    }

    public void setViagemid(int viagemid) {
        this.viagemid = viagemid;
    }

    public int getTotalEstimadoKM() {
        return totalEstimadoKM;
    }

    public void setTotalEstimadoKM(int totalEstimadoKM) {
        this.totalEstimadoKM = totalEstimadoKM;
    }

    public double getMediaKMLitro() {
        return mediaKMLitro;
    }

    public void setMediaKMLitro(double mediaKMLitro) {
        this.mediaKMLitro = mediaKMLitro;
    }

    public double getCustoMedioLitro() {
        return custoMedioLitro;
    }

    public void setCustoMedioLitro(double custoMedioLitro) {
        this.custoMedioLitro = custoMedioLitro;
    }

    public int getTotalVeiculos() {
        return totalVeiculos;
    }

    public void setTotalVeiculos(int totalVeiculos) {
        this.totalVeiculos = totalVeiculos;
    }

    public int getIdConta() {
        return idConta;
    }

    public void setIdConta(int idConta) {
        this.idConta = idConta;
    }
}
