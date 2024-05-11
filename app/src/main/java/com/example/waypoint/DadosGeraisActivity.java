package com.example.waypoint;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class DadosGeraisActivity extends AppCompatActivity {

    private Button btnAddControle;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dados_gerais);

        btnAddControle = findViewById(R.id.btnAddControle);
        btnAddControle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(DadosGeraisActivity.this, GasolinaActivity.class);
                startActivity(it);
            }
        });

    }
}
