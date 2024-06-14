package com.example.waypoint.api.response;

import java.io.Serializable;
import java.util.List;

public class Viagem implements Serializable {

    private int totalViajante;
    private int duracaoViagem;
    private double custoTotalViagem;
    private double custoPorPessoa;
    private String local;
    private int idConta;

    private Gasolina gasolina;
    private Aereo aereo;
    private Refeicao refeicao;
    private Hospedagem hospedagem;
    private List<Entretenimento> entretenimentoList;

    public int getTotalViajante() {
        return totalViajante;
    }

    public void setTotalViajante(int totalViajante) {
        this.totalViajante = totalViajante;
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

    public List<Entretenimento> getEntretenimentoList() {
        return entretenimentoList;
    }

    public void setEntretenimentoList(List<Entretenimento> entretenimentoList) {
        this.entretenimentoList = entretenimentoList;
    }

    @Override
    public String toString() {
        String gasolinaStr = gasolina != null ?
                "Gasolina{" +
                        "viagemid=" + gasolina.getViagemid() +
                        ", totalEstimadoKM=" + gasolina.getTotalEstimadoKM() +
                        ", mediaKMLitro=" + gasolina.getMediaKMLitro() +
                        ", custoMedioLitro=" + gasolina.getCustoMedioLitro() +
                        ", totalVeiculos=" + gasolina.getTotalVeiculos() +
                        ", idConta=" + gasolina.getIdConta() +
                        '}' :
                "null";

        String aereoStr = aereo != null ?
                "Aereo{" +
                        "viagemid=" + aereo.getViagemid() +
                        ", custoPessoa=" + aereo.getCustoPessoa() +
                        ", custoAluguelVeiculo=" + aereo.getCustoAluguelVeiculo() +
                        ", idConta=" + aereo.getIdConta() +
                        '}' :
                "null";

        String refeicaoStr = refeicao != null ?
                "Refeicao{" +
                        "viagemid=" + refeicao.getViagemid() +
                        ", custoRefeicao=" + refeicao.getCustoRefeicao() +
                        ", refeicoesDia=" + refeicao.getRefeicoesDia() +
                        ", idConta=" + refeicao.getIdConta() +
                        '}' :
                "null";

        String hospedagemStr = hospedagem != null ?
                "Hospedagem{" +
                        "viagemid=" + hospedagem.getViagemid() +
                        ", custoMedioNoite=" + hospedagem.getCustoMedioNoite() +
                        ", totalNoite=" + hospedagem.getTotalNoite() +
                        ", totalQuartos=" + hospedagem.getTotalQuartos() +
                        ", idConta=" + hospedagem.getIdConta() +
                        '}' :
                "null";

        StringBuilder entretenimentoListStr = new StringBuilder();
        if (entretenimentoList != null) {
            entretenimentoListStr.append("EntretenimentoList{");
            for (Entretenimento entretenimento : entretenimentoList) {
                entretenimentoListStr.append(entretenimento.toString()).append(", ");
            }
            if (entretenimentoListStr.length() > 0) {
                entretenimentoListStr.delete(entretenimentoListStr.length() - 2, entretenimentoListStr.length());
            }
            entretenimentoListStr.append('}');
        } else {
            entretenimentoListStr.append("null");
        }

        return "Viagem{" +
                "totalViajante=" + totalViajante +
                ", duracaoViagem=" + duracaoViagem +
                ", custoTotalViagem=" + custoTotalViagem +
                ", custoPorPessoa=" + custoPorPessoa +
                ", local='" + local + '\'' +
                ", idConta=" + idConta +
                ", gasolina=" + gasolinaStr +
                ", aereo=" + aereoStr +
                ", refeicao=" + refeicaoStr +
                ", hospedagem=" + hospedagemStr +
                ", entretenimentoList=" + entretenimentoListStr +
                '}';
    }
}
