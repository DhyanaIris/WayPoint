package com.example.waypoint;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.waypoint.database.dao.DadosGeraisDAO;
import com.example.waypoint.database.model.DadosGeraisModel;

public class DadosGeraisActivity extends AppCompatActivity {

    private DadosGeraisDAO dadosGeraisDAO;
    private EditText txtNomeViagem, txtViajantes, txtDuracao, txtDestino;
    private Button btnAddControle;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dados_gerais);

        txtNomeViagem = findViewById(R.id.txtNomeViagem);
        txtViajantes = findViewById(R.id.txtViajantes);
        txtDuracao = findViewById(R.id.txtDuracao);
        txtDestino = findViewById(R.id.txtDestino);

        btnAddControle = findViewById(R.id.btnAddControle);
        btnAddControle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                salvar();
            }
        });

    }

    private void salvar() {
        dadosGeraisDAO = new DadosGeraisDAO(DadosGeraisActivity.this);
        String nomeViagem = String.valueOf(txtNomeViagem.getText()).trim();
        String viajantes = String.valueOf(txtViajantes.getText()).trim();
        String duracao = String.valueOf(txtDuracao.getText()).trim();
        String destino = String.valueOf(txtDestino.getText()).trim();

        if (nomeViagem.isEmpty() || viajantes.isEmpty() || duracao.isEmpty() || destino.isEmpty()) {
            Toast.makeText(DadosGeraisActivity.this, "Por favor, preencha todos os campos", Toast.LENGTH_SHORT).show();
        }
        DadosGeraisModel dadosGeraisModel = new DadosGeraisModel();
        dadosGeraisModel.setNomeViagem(nomeViagem);
        dadosGeraisModel.setViajantes(Float.parseFloat(viajantes));
        dadosGeraisModel.setDuracao(Float.parseFloat(duracao));
        dadosGeraisModel.setDestino(destino);
        dadosGeraisModel.setIdUsuario(MyApplication.getInstance().getIdUsuarioLogado());
        dadosGeraisModel.setIdViagem(MyApplication.getInstance().getIdViagemAtual());

        long rowId = dadosGeraisDAO.Insert(dadosGeraisModel);

        if (rowId != -1) {
            // Dados salvos com sucesso
            Intent it = new Intent(DadosGeraisActivity.this, GasolinaActivity.class);
            startActivity(it);
            finish();
            Toast.makeText(DadosGeraisActivity.this, "Dados salvos com sucesso", Toast.LENGTH_SHORT).show();
        } else {
            // Ocorreu um erro ao salvar os dados
            Toast.makeText(DadosGeraisActivity.this, "Erro ao salvar os dados", Toast.LENGTH_SHORT).show();
        }

    }
}
