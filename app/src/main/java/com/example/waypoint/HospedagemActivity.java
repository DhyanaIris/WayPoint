package com.example.waypoint;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.waypoint.database.dao.HospedagemDAO;
import com.example.waypoint.database.model.HospedagemModel;

import java.util.Locale;

public class HospedagemActivity extends AppCompatActivity {

    private HospedagemDAO hospedagemDAO;
    private Button btnAddViagem, btnPularEtapa;
    private EditText txtCustoMedio, txtTotalNoites, txtTotalQuartos, txtTotal;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hospedagem);

        txtCustoMedio = findViewById(R.id.txtCustoMedio);
        txtTotalNoites = findViewById(R.id.txtTotalNoites);
        txtTotalQuartos = findViewById(R.id.txtTotalQuartos);
        txtTotal = findViewById(R.id.txtTotal);

        TextWatcher watcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}

            @Override
            public void afterTextChanged(Editable s) {
                calcularHospedagem();
            }
        };

        txtCustoMedio.addTextChangedListener(watcher);
        txtTotalNoites.addTextChangedListener(watcher);
        txtTotalQuartos.addTextChangedListener(watcher);


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
                Intent it = new Intent(HospedagemActivity.this, DiversosActivity.class);
                startActivity(it);
            }
        });

    }

    private void salvar() {
        hospedagemDAO = new HospedagemDAO(HospedagemActivity.this);
        String custoMedio = String.valueOf(txtCustoMedio.getText()).trim();
        String totalNoites = String.valueOf(txtTotalNoites.getText()).trim();
        String totalQuartos = String.valueOf(txtTotalQuartos.getText()).trim();

        if (custoMedio.isEmpty() || totalNoites.isEmpty() || totalQuartos.isEmpty()) {
            Toast.makeText(HospedagemActivity.this, "Por favor, preencha todos os campos", Toast.LENGTH_SHORT).show();
        }

        float totalFloat = calcularHospedagem();

        HospedagemModel hospedagemModel = new HospedagemModel();
        hospedagemModel.setCustoMedio(Float.parseFloat(custoMedio));
        hospedagemModel.setTotalNoites(Float.parseFloat(totalNoites));
        hospedagemModel.setTotalQuartos(Float.parseFloat(totalQuartos));
        hospedagemModel.setTotal(totalFloat);
        hospedagemModel.setIdUsuario(MyApplication.getInstance().getIdUsuarioLogado());
        hospedagemModel.setIdViagem(MyApplication.getInstance().getIdViagemAtual());

        long rowId = hospedagemDAO.Insert(hospedagemModel);

        if (rowId != -1) {
            Intent it = new Intent(HospedagemActivity.this, DiversosActivity.class);
            startActivity(it);
            finish();
            Toast.makeText(HospedagemActivity.this, "Dados salvos com sucesso", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(HospedagemActivity.this, "Erro ao salvar os dados", Toast.LENGTH_SHORT).show();
        }
    }

    private float calcularHospedagem() {
        try {
            String custoNoite = String.valueOf(txtCustoMedio.getText()).trim();
            String totalNoites = String.valueOf(txtTotalNoites.getText()).trim();
            String totalQuartos = String.valueOf(txtTotalQuartos.getText()).trim();


            if (custoNoite.isEmpty() || totalNoites.isEmpty() || totalQuartos.isEmpty()) {
                txtTotal.setText("0");
                return 0;
            }

            float custoMedioFloat = Float.parseFloat(custoNoite);
            float totalNoitesFloat = Float.parseFloat(totalNoites);
            float totalQuartosFloat = Float.parseFloat(totalQuartos);

            float total = (custoMedioFloat * totalNoitesFloat) * totalQuartosFloat;
            String textoFormatado = String.format(Locale.getDefault(), "%.2f", total);
            txtTotal.setText(textoFormatado);
            return total;
        } catch (NumberFormatException e) {
            txtTotal.setText("Valor Inválido");
            return 0;
        }
    }
}