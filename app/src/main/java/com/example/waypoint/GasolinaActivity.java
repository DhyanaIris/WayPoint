package com.example.waypoint;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.waypoint.database.dao.GasolinaDAO;
import com.example.waypoint.database.model.GasolinaModel;

import java.util.Locale;

public class GasolinaActivity extends AppCompatActivity {

    private GasolinaDAO gasolinaDAO;
    private EditText txtKmTotal, txtMediaKmLitro, txtCustoMedioLitro, txtTotalVeiculos, txtTotal;
    private Button btnAddViagem, btnPularEtapa;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gasolina);

        txtKmTotal = findViewById(R.id.txtKmTotal);
        txtMediaKmLitro = findViewById(R.id.txtMediaKmLitro);
        txtCustoMedioLitro = findViewById(R.id.txtCustoMedioLitro);
        txtTotalVeiculos = findViewById(R.id.txtTotalVeiculos);
        txtTotal = findViewById(R.id.txtTotal);

        TextWatcher watcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}

            @Override
            public void afterTextChanged(Editable s) {
                calcularGasolina();
            }
        };
        txtKmTotal.addTextChangedListener(watcher);
        txtMediaKmLitro.addTextChangedListener(watcher);
        txtCustoMedioLitro.addTextChangedListener(watcher);
        txtTotalVeiculos.addTextChangedListener(watcher);

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
                Intent it = new Intent(GasolinaActivity.this, TarifaAereaActivity.class);
                startActivity(it);
            }
        });

        OnBackPressedCallback onBackPressedCallback = new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                Intent intent = new Intent(GasolinaActivity.this, ListaViagensActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                finish();
            }
        };
        getOnBackPressedDispatcher().addCallback(this,onBackPressedCallback);
    }

    private void salvar() {
        gasolinaDAO = new GasolinaDAO(GasolinaActivity.this);
        String kmTotal = String.valueOf(txtKmTotal.getText()).trim();
        String mediaKm = String.valueOf(txtMediaKmLitro.getText()).trim();
        String custoMedioLitro = String.valueOf(txtCustoMedioLitro.getText()).trim();
        String totalVeiculo = String.valueOf(txtTotalVeiculos.getText()).trim();

        if (kmTotal.isEmpty() || mediaKm.isEmpty() || custoMedioLitro.isEmpty() || totalVeiculo.isEmpty()) {
            Toast.makeText(GasolinaActivity.this, "Por favor, preencha todos os campos", Toast.LENGTH_SHORT).show();
        }

        float totalFloat = calcularGasolina();

        GasolinaModel gasolinaModel = new GasolinaModel();
        gasolinaModel.setKmTotal(Float.parseFloat(kmTotal));
        gasolinaModel.setMediaKmLitro(Float.parseFloat(mediaKm));
        gasolinaModel.setCustoLitro(Float.parseFloat(custoMedioLitro));
        gasolinaModel.setTotalVeiculos(Float.parseFloat(totalVeiculo));
        gasolinaModel.setTotal(totalFloat);
        gasolinaModel.setIdUsuario(MyApplication.getInstance().getIdUsuarioLogado());
        gasolinaModel.setIdViagem(MyApplication.getInstance().getIdViagemAtual());

        long rowId = gasolinaDAO.Insert(gasolinaModel);

        if (rowId != -1) {
            Intent it = new Intent(GasolinaActivity.this, TarifaAereaActivity.class);
            startActivity(it);
            finish();
            Toast.makeText(GasolinaActivity.this, "Dados salvos com sucesso", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(GasolinaActivity.this, "Erro ao salvar os dados", Toast.LENGTH_SHORT).show();
        }
    }

    private float calcularGasolina() {
        try {
            String kmTotal = String.valueOf(txtKmTotal.getText()).trim();
            String mediaKm = String.valueOf(txtMediaKmLitro.getText()).trim();
            String custoMedioLitro = String.valueOf(txtCustoMedioLitro.getText()).trim();
            String totalVeiculo = String.valueOf(txtTotalVeiculos.getText()).trim();

            if (kmTotal.isEmpty() || mediaKm.isEmpty() || custoMedioLitro.isEmpty() || totalVeiculo.isEmpty()) {
                txtTotal.setText("0");
                return 0;
            }

            float kmTotalFloat = Float.parseFloat(kmTotal);
            float mediaKmFloat = Float.parseFloat(mediaKm);
            float custoMedioLitroFloat = Float.parseFloat(custoMedioLitro);
            float totalVeiculoFloat = Float.parseFloat(totalVeiculo);

            float total = ((kmTotalFloat / mediaKmFloat) * custoMedioLitroFloat) / totalVeiculoFloat;
            String textoFormatado = String.format(Locale.getDefault(), "%.2f", total);
            txtTotal.setText(textoFormatado);
            return total;
        } catch (NumberFormatException e) {
            txtTotal.setText("Valor Inválido");
            return 0;
        }
    }
}
