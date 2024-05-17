package com.example.waypoint;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class TarifaAereaActivity extends AppCompatActivity {

    private Button btnPularEtapa, btnAddViagem;
    private EditText txtCustoPessoa, txtAluguelVeiculo, txtTotal;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tarifa_aerea);

        txtCustoPessoa = findViewById(R.id.txtCustoPessoa);
        txtAluguelVeiculo = findViewById(R.id.txtAluguelVeiculo);
        txtTotal = findViewById(R.id.txtTotal);

        btnAddViagem = findViewById(R.id.btnAddViagem);
        btnAddViagem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(TarifaAereaActivity.this, RefeicoesActivity.class);
                startActivity(it);
            }
        });

        btnPularEtapa = findViewById(R.id.btnPularEtapa);
        btnPularEtapa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(TarifaAereaActivity.this, RefeicoesActivity.class);
                startActivity(it);
            }
        });
    }
}
