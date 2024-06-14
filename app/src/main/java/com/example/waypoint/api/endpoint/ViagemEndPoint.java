package com.example.waypoint.api.endpoint;

import com.example.waypoint.api.response.Resposta;
import com.example.waypoint.api.response.Viagem;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ViagemEndPoint {

    @POST("api/cadastro/viagem")
    Call<Resposta> postViagem(@Body Viagem viagem);

    @GET("api/listar/viagem")
    Call<ArrayList<Viagem>> getViagem(@Query("idConta") String idConta);

    @GET("api/listar/viagem/conta")
    Call<ArrayList<Viagem>> getViagemConta(@Query("idConta") String idConta);

}
