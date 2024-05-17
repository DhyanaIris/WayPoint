package com.example.waypoint;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class DiversosActivity extends AppCompatActivity {

    private LinearLayout containerLayout;
    private Button btnNovoCampo, btnAddViagem, btnPularEtapa;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diversos);

        containerLayout = findViewById(R.id.containerLayout);

        btnNovoCampo = findViewById(R.id.btnNovoCampo);
        btnNovoCampo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                novoCampo();
            }
        });

        btnAddViagem = findViewById(R.id.btnAddViagem);
        btnAddViagem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(DiversosActivity.this, RelatorioActivity.class);
                startActivity(it);
            }
        });

        btnPularEtapa = findViewById(R.id.btnPularEtapa);
        btnPularEtapa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(DiversosActivity.this, RelatorioActivity.class);
                startActivity(it);
            }
        });


    }

    private void novoCampo() {

        TextView lugarVisitadoLabel = new TextView(this);
        LinearLayout.LayoutParams labelParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        labelParams.setMargins(0, 120, 0, 0); // Setting top margin
        lugarVisitadoLabel.setLayoutParams(labelParams);
        lugarVisitadoLabel.setText("Lugar Visitado");
        lugarVisitadoLabel.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18); // Set font size
        lugarVisitadoLabel.setTypeface(null, Typeface.BOLD); // Set bold
        containerLayout.addView(lugarVisitadoLabel);

        EditText lugarVisitadoEditText = new EditText(this);
        lugarVisitadoEditText.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT));
        lugarVisitadoEditText.setHint("Informe o nome do lugar");
        containerLayout.addView(lugarVisitadoEditText);

        TextView custoLabel = new TextView(this);
        custoLabel.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT));
        custoLabel.setText("Custo");
        custoLabel.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18);
        custoLabel.setTypeface(null, Typeface.BOLD);
        containerLayout.addView(custoLabel);

        EditText custoEditText = new EditText(this);
        custoEditText.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT));
        custoEditText.setHint("Informe o custo da atividade");
        containerLayout.addView(custoEditText);
    }
}