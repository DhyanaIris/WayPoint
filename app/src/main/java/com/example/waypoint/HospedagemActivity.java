package com.example.waypoint;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class HospedagemActivity extends AppCompatActivity {

    private Button btnAddViagem;
    private Button btnPularEtapa;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hospedagem);

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