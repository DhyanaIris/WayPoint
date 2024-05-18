package com.example.waypoint;

import android.app.Application;

public class MyApplication extends Application {
    private static MyApplication instance;
    private long idUsuarioLogado, idViagemAtual;
    private  float totalViajantes, duracaoViagem;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }

    public static MyApplication getInstance() {
        return instance;
    }

    public long getIdUsuarioLogado() {
        return idUsuarioLogado;
    }

    public void setIdUsuarioLogado(long idUsuarioLogado) {
        this.idUsuarioLogado = idUsuarioLogado;
    }

    public long getIdViagemAtual() {
        return idViagemAtual;
    }

    public void setIdViagemAtual(long idViagemAtual) {
        this.idViagemAtual = idViagemAtual;
    }

    public float getTotalViajantes() {
        return totalViajantes;
    }

    public void setTotalViajantes(float totalViajantes) {
        this.totalViajantes = totalViajantes;
    }

    public float getDuracaoViagem() {
        return duracaoViagem;
    }

    public void setDuracaoViagem(float duracaoViagem) {
        this.duracaoViagem = duracaoViagem;
    }
}