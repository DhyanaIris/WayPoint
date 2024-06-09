package com.example.waypoint.api;

import com.example.waypoint.api.endpoint.ViagemEndPoint;
import com.example.waypoint.api.response.Resposta;
import com.example.waypoint.api.response.Viagem;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class API {
    private static final String URL_ROOT = "http://api.genialsaude.com.br/";

    private static Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(URL_ROOT)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    public static void postViagem(Viagem viagem, Callback<Resposta> callback) {
        ViagemEndPoint endpoint = retrofit.create(ViagemEndPoint.class);
        Call<Resposta> call = endpoint.postViagem(viagem);
        call.enqueue(callback);
    }
}
