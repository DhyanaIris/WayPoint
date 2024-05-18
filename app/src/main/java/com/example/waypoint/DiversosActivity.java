package com.example.waypoint;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.waypoint.database.dao.DiversosDAO;
import com.example.waypoint.database.model.DiversosModel;

import java.util.ArrayList;
import java.util.List;

public class DiversosActivity extends AppCompatActivity {

    private DiversosDAO diversosDAO;
    private LinearLayout containerLayout;
    private Button btnNovoCampo, btnAddViagem, btnPularEtapa;
    private EditText txtNomeLocal, txtCusto, txtTotal;

    private List<EditText> localVisitadoEditTexts;
    private List<EditText> custoEditTexts;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diversos);

        txtNomeLocal = findViewById(R.id.txtNomeLocal);
        txtCusto = findViewById(R.id.txtCusto);
        txtTotal = findViewById(R.id.txtTotal);

        containerLayout = findViewById(R.id.containerLayout);

        localVisitadoEditTexts = new ArrayList<>();
        custoEditTexts = new ArrayList<>();

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
                salvar();
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
        TextWatcher watcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}

            @Override
            public void afterTextChanged(Editable s) {
                calcularTotal();
            }
        };
        txtNomeLocal.addTextChangedListener(watcher);
        txtCusto.addTextChangedListener(watcher);

    }

    private void novoCampo() {

        TextView localVisitadoLabel = new TextView(this);
        LinearLayout.LayoutParams labelParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        labelParams.setMargins(0, 120, 0, 0);
        localVisitadoLabel.setLayoutParams(labelParams);
        localVisitadoLabel.setText("Local Visitado");
        localVisitadoLabel.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18);
        localVisitadoLabel.setTypeface(null, Typeface.BOLD);
        containerLayout.addView(localVisitadoLabel);

        EditText localVisitadoEditText = new EditText(this);
        localVisitadoEditText.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT));
        localVisitadoEditText.setHint("Informe o nome do local");
        containerLayout.addView(localVisitadoEditText);
        localVisitadoEditTexts.add(localVisitadoEditText);

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
        custoEditTexts.add(custoEditText);

        TextWatcher watcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}

            @Override
            public void afterTextChanged(Editable s) {
                calcularTotal();
            }
        };
        localVisitadoEditText.addTextChangedListener(watcher);
        custoEditText.addTextChangedListener(watcher);
    }

    private void salvar() {
        diversosDAO = new DiversosDAO(DiversosActivity.this);

        if (txtNomeLocal.getText().toString().trim().isEmpty() || txtCusto.getText().toString().trim().isEmpty()) {
            Toast.makeText(DiversosActivity.this, "Por favor, preencha todos os campos", Toast.LENGTH_SHORT).show();
            return;
        }

        String nomeLocal = String.valueOf(txtNomeLocal.getText()).trim();
        String custo = String.valueOf(txtCusto.getText()).trim();

        DiversosModel diversosModel = new DiversosModel();
        diversosModel.setNomeLocal(nomeLocal);
        diversosModel.setCusto(Float.parseFloat(custo));
        diversosModel.setIdUsuario(MyApplication.getInstance().getIdUsuarioLogado());
        diversosModel.setIdViagem(MyApplication.getInstance().getIdViagemAtual());

        long rowId = diversosDAO.Insert(diversosModel);

        if (rowId != -1) {
            for (int i = 0; i < localVisitadoEditTexts.size(); i++) {
                String dynamicNomeLocal = localVisitadoEditTexts.get(i).getText().toString().trim();
                String dynamicCusto = custoEditTexts.get(i).getText().toString().trim();
                if (dynamicNomeLocal.isEmpty() || dynamicCusto.isEmpty()) {
                    Toast.makeText(DiversosActivity.this, "Por favor, preencha todos os campos", Toast.LENGTH_SHORT).show();
                    return;
                }

                DiversosModel dynamicDiversosModel = new DiversosModel();
                dynamicDiversosModel.setNomeLocal(dynamicNomeLocal);
                dynamicDiversosModel.setCusto(Float.parseFloat(dynamicCusto));
                dynamicDiversosModel.setIdUsuario(MyApplication.getInstance().getIdUsuarioLogado());
                dynamicDiversosModel.setIdViagem(MyApplication.getInstance().getIdViagemAtual());

                long dynamicRowId = diversosDAO.Insert(dynamicDiversosModel);
                if (dynamicRowId == -1) {
                    Toast.makeText(DiversosActivity.this, "Erro ao salvar os dados dinâmicos", Toast.LENGTH_SHORT).show();
                    return;
                }
            }

            Intent it = new Intent(DiversosActivity.this, RelatorioActivity.class);
            startActivity(it);
            finish();
            Toast.makeText(DiversosActivity.this, "Dados salvos com sucesso", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(DiversosActivity.this, "Erro ao salvar os dados", Toast.LENGTH_SHORT).show();
        }
    }

    private void calcularTotal() {
        try {
            float total = 0.0f;

            String custoStr = txtCusto.getText().toString().trim();
            if (!custoStr.isEmpty()) {
                total += Float.parseFloat(custoStr);
            }

            for (EditText custoEditText : custoEditTexts) {
                String dynamicCustoStr = custoEditText.getText().toString().trim();
                if (!dynamicCustoStr.isEmpty()) {
                    total += Float.parseFloat(dynamicCustoStr);
                }
            }

            txtTotal.setText(String.valueOf(total));
        } catch (NumberFormatException e) {
            txtTotal.setText("Valor Inválido");
        }
    }
}


