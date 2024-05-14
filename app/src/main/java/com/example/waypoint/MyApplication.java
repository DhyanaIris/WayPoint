package com.example.waypoint;

import android.app.Application;

public class MyApplication extends Application {
    private static MyApplication instance;
    private long idUsuarioLogado;

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
}