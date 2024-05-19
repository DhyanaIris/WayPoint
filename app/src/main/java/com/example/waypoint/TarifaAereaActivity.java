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

import com.example.waypoint.database.dao.TarifaAereaDAO;
import com.example.waypoint.database.model.TarifaAereaModel;

import java.util.Locale;

public class TarifaAereaActivity extends AppCompatActivity {

    private TarifaAereaDAO tarifaAereaDAO;
    private EditText txtCustoPessoa, txtAluguelVeiculo, txtTotal;
    private Button btnPularEtapa, btnAddViagem;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tarifa_aerea);

        txtCustoPessoa = findViewById(R.id.txtCustoPessoa);
        txtAluguelVeiculo = findViewById(R.id.txtAluguelVeiculo);
        txtTotal = findViewById(R.id.txtTotal);

        TextWatcher watcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}

            @Override
            public void afterTextChanged(Editable s) {
                calcularTarifaAerea();
            }
        };
        txtCustoPessoa.addTextChangedListener(watcher);
        txtAluguelVeiculo.addTextChangedListener(watcher);

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
                Intent it = new Intent(TarifaAereaActivity.this, RefeicoesActivity.class);
                startActivity(it);
            }
        });
    }

    private void salvar() {
        tarifaAereaDAO = new TarifaAereaDAO(TarifaAereaActivity.this);
        String custoPessoa = String.valueOf(txtCustoPessoa.getText()).trim();
        String aluguelVeiculo = String.valueOf(txtAluguelVeiculo.getText()).trim();

        if (custoPessoa.isEmpty() || aluguelVeiculo.isEmpty()) {
            Toast.makeText(TarifaAereaActivity.this, "Por favor, preencha todos os campos", Toast.LENGTH_SHORT).show();
        }

        float totalFloat = calcularTarifaAerea();

        TarifaAereaModel tarifaAereaModel = new TarifaAereaModel();
        tarifaAereaModel.setCustoPessoa(Float.parseFloat(custoPessoa));
        tarifaAereaModel.setAluguelVeiculo(Float.parseFloat(aluguelVeiculo));
        tarifaAereaModel.setTotal(totalFloat);
        tarifaAereaModel.setIdUsuario(MyApplication.getInstance().getIdUsuarioLogado());
        tarifaAereaModel.setIdViagem(MyApplication.getInstance().getIdViagemAtual());

        long rowId = tarifaAereaDAO.Insert(tarifaAereaModel);

        if (rowId != -1) {
            Intent it = new Intent(TarifaAereaActivity.this, RefeicoesActivity.class);
            startActivity(it);
            finish();
            Toast.makeText(TarifaAereaActivity.this, "Dados salvos com sucesso", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(TarifaAereaActivity.this, "Erro ao salvar os dados", Toast.LENGTH_SHORT).show();
        }
    }

    private float calcularTarifaAerea() {
        try {
            String custoPessoa = String.valueOf(txtCustoPessoa.getText()).trim();
            String aluguelVeiculo = String.valueOf(txtAluguelVeiculo.getText()).trim();

            if (custoPessoa.isEmpty() || aluguelVeiculo.isEmpty()) {
                txtTotal.setText("0");
                return 0;
            }

            float custoPessoaFloat = Float.parseFloat(custoPessoa);
            float aluguelVeiculoFloat = Float.parseFloat(aluguelVeiculo);
            float viajantes = MyApplication.getInstance().getTotalViajantes();

            float total = (custoPessoaFloat * viajantes) + aluguelVeiculoFloat;
            String textoFormatado = String.format(Locale.getDefault(), "%.2f", total);
            txtTotal.setText(textoFormatado);
            return total;
        } catch (NumberFormatException e) {
            txtTotal.setText("Valor Inválido");
            return 0;
        }
    }

}
