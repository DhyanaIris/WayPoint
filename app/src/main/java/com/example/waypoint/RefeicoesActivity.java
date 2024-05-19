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

import com.example.waypoint.database.dao.RefeicoesDAO;
import com.example.waypoint.database.model.RefeicoesModel;

import java.util.Locale;

public class RefeicoesActivity extends AppCompatActivity {

    private RefeicoesDAO refeicoesDAO;
    private Button btnAddViagem, btnPularEtapa;
    private EditText txtCustoRefeicao, txtRefeicoesDia, txtTotal;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_refeicoes);

        txtCustoRefeicao = findViewById(R.id.txtCustoRefeicao);
        txtRefeicoesDia = findViewById(R.id.txtRefeicoesDia);
        txtTotal = findViewById(R.id.txtTotal);

        TextWatcher watcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}

            @Override
            public void afterTextChanged(Editable s) {
                calcularRefeicoes();
            }
        };
        txtCustoRefeicao.addTextChangedListener(watcher);
        txtRefeicoesDia.addTextChangedListener(watcher);

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
                Intent it = new Intent(RefeicoesActivity.this, HospedagemActivity.class);
                startActivity(it);
            }
        });

        OnBackPressedCallback onBackPressedCallback = new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                Intent intent = new Intent(RefeicoesActivity.this, ListaViagensActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                finish();
            }
        };
        getOnBackPressedDispatcher().addCallback(this,onBackPressedCallback);
    }

    private void salvar() {
        refeicoesDAO = new RefeicoesDAO(RefeicoesActivity.this);
        String custoRefeicao = String.valueOf(txtCustoRefeicao.getText()).trim();
        String refeicoesDia = String.valueOf(txtRefeicoesDia.getText()).trim();

        if (custoRefeicao.isEmpty() || refeicoesDia.isEmpty()) {
            Toast.makeText(RefeicoesActivity.this, "Por favor, preencha todos os campos", Toast.LENGTH_SHORT).show();
        }

        float totalFloat = calcularRefeicoes();

        RefeicoesModel refeicoesModel = new RefeicoesModel();
        refeicoesModel.setCustoRefeicao(Float.parseFloat(custoRefeicao));
        refeicoesModel.setRefeicoesDia(Float.parseFloat(refeicoesDia));
        refeicoesModel.setTotal(totalFloat);
        refeicoesModel.setIdUsuario(MyApplication.getInstance().getIdUsuarioLogado());
        refeicoesModel.setIdViagem(MyApplication.getInstance().getIdViagemAtual());

        long rowId = refeicoesDAO.Insert(refeicoesModel);

        if (rowId != -1) {
            Intent it = new Intent(RefeicoesActivity.this, HospedagemActivity.class);
            startActivity(it);
            finish();
            Toast.makeText(RefeicoesActivity.this, "Dados salvos com sucesso", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(RefeicoesActivity.this, "Erro ao salvar os dados", Toast.LENGTH_SHORT).show();
        }
    }

    private float calcularRefeicoes() {
        try {
            String custoRefeicao = String.valueOf(txtCustoRefeicao.getText()).trim();
            String refeicoesDia = String.valueOf(txtRefeicoesDia.getText()).trim();

            if (custoRefeicao.isEmpty() || refeicoesDia.isEmpty()) {
                txtTotal.setText("0");
                return 0;
            }

            float custoRefeicaoFloat = Float.parseFloat(custoRefeicao);
            float refeicoesDiaFloat = Float.parseFloat(refeicoesDia);
            float viajantes = MyApplication.getInstance().getTotalViajantes();
            float duracao = MyApplication.getInstance().getDuracaoViagem();

            float total = ((refeicoesDiaFloat * viajantes) * custoRefeicaoFloat) * duracao;
            String textoFormatado = String.format(Locale.getDefault(), "%.2f", total);
            txtTotal.setText(textoFormatado);
            return total;
        } catch (NumberFormatException e) {
            txtTotal.setText("Valor Inválido");
            return 0;
        }
    }
}
