package com.example.waypoint;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class RefeicoesActivity extends AppCompatActivity {

    private Button btnAddViagem, btnPularEtapa;

    private EditText txtCustoRefeicao, txtRefeicoesDia, txtTotal;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_refeicoes);

        txtCustoRefeicao = findViewById(R.id.txtCustoRefeicao);
        txtRefeicoesDia = findViewById(R.id.txtRefeicoesDia);
        txtTotal = findViewById(R.id.txtTotal);

        btnAddViagem = findViewById(R.id.btnAddViagem);
        btnAddViagem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(RefeicoesActivity.this, HospedagemActivity.class);
                startActivity(it);
            }
        });

        btnPularEtapa = findViewById(R.id.btnPularEtapa);
        btnPularEtapa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(RefeicoesActivity.this, HospedagemActivity.class);
                startActivity(it);
            }
        });
    }
}
