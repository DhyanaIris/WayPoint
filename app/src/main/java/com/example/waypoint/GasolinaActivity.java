package com.example.waypoint;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class GasolinaActivity extends AppCompatActivity {

    private Button btnAddViagem, btnPularEtapa;
    private EditText txtKmTotal, txtMediaKmLitro, txtCustoMedioLitro, txtTotalVeiculos, txtTotal;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gasolina);

        txtKmTotal = findViewById(R.id.txtKmTotal);
        txtMediaKmLitro = findViewById(R.id.txtMediaKmLitro);
        txtCustoMedioLitro = findViewById(R.id.txtCustoMedioLitro);
        txtTotalVeiculos = findViewById(R.id.txtTotalVeiculos);
        txtTotal = findViewById(R.id.txtTotal);


        btnAddViagem = findViewById(R.id.btnAddViagem);
        btnAddViagem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(GasolinaActivity.this, TarifaAereaActivity.class);
                startActivity(it);
            }
        });

        btnPularEtapa = findViewById(R.id.btnPularEtapa);
        btnPularEtapa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(GasolinaActivity.this, TarifaAereaActivity.class);
                startActivity(it);
            }
        });
    }

}
