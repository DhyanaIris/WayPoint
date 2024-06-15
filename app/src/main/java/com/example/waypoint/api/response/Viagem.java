package com.example.waypoint.api.response;

import java.io.Serializable;
import java.util.List;

public class Viagem implements Serializable {

    private int totalViajantes;
    private int duracaoViagem;
    private double custoTotalViagem;
    private double custoPorPessoa;
    private String local;
    private int idConta;

    private Gasolina gasolina;
    private Aereo aereo;
    private Refeicao refeicao;
    private Hospedagem hospedagem;
    private List<Entretenimento> listaEntretenimento;

    public int getTotalViajantes() {
        return totalViajantes;
    }

    public void setTotalViajantes(int totalViajante) {
        this.totalViajantes = totalViajante;
    }

    public int getDuracaoViagem() {
        return duracaoViagem;
    }

    public void setDuracaoViagem(int duracaoViagem) {
        this.duracaoViagem = duracaoViagem;
    }

    public double getCustoTotalViagem() {
        return custoTotalViagem;
    }

    public void setCustoTotalViagem(double custoTotalViagem) {
        this.custoTotalViagem = custoTotalViagem;
    }

    public double getCustoPorPessoa() {
        return custoPorPessoa;
    }

    public void setCustoPorPessoa(double custoPorPessoa) {
        this.custoPorPessoa = custoPorPessoa;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public int getIdConta() {
        return idConta;
    }

    public void setIdConta(int idConta) {
        this.idConta = idConta;
    }

    public Gasolina getGasolina() {
        return gasolina;
    }

    public void setGasolina(Gasolina gasolina) {
        this.gasolina = gasolina;
    }

    public Aereo getAereo() {
        return aereo;
    }

    public void setAereo(Aereo aereo) {
        this.aereo = aereo;
    }

    public Refeicao getRefeicao() {
        return refeicao;
    }

    public void setRefeicao(Refeicao refeicao) {
        this.refeicao = refeicao;
    }

    public Hospedagem getHospedagem() {
        return hospedagem;
    }

    public void setHospedagem(Hospedagem hospedagem) {
        this.hospedagem = hospedagem;
    }

    public List<Entretenimento> getListaEntretenimento() {
        return listaEntretenimento;
    }

    public void setListaEntretenimento(List<Entretenimento> listaEntretenimento) {
        this.listaEntretenimento = listaEntretenimento;
    }

}
