package com.example.waypoint;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class TotalActivity extends AppCompatActivity {

    private Button btnProxEtapa;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_total);

        btnProxEtapa = findViewById(R.id.btnProxEtapa);
        btnProxEtapa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(TotalActivity.this, ListaViagensActivity.class);
                startActivity(it);
            }
        });
    }
}