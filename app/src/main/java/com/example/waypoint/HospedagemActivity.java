package com.example.waypoint;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class HospedagemActivity extends AppCompatActivity {

    private Button btnAddViagem, btnPularEtapa;

    private EditText txtCustoNoite, txtTotalNoites, txtTotalQuartos, txtTotal;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hospedagem);

        txtCustoNoite = findViewById(R.id.txtCustoNoite);
        txtTotalNoites = findViewById(R.id.txtTotalNoites);
        txtTotalQuartos = findViewById(R.id.txtTotalQuartos);
        txtTotal = findViewById(R.id.txtTotal);


        btnAddViagem = findViewById(R.id.btnAddViagem);
        btnAddViagem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(HospedagemActivity.this, DiversosActivity.class);
                startActivity(it);
            }
        });

        btnPularEtapa = findViewById(R.id.btnPularEtapa);
        btnPularEtapa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(HospedagemActivity.this, DiversosActivity.class);
                startActivity(it);
            }
        });
    }
}