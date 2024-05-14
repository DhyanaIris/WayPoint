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
    private EditText textNomeViagem, textViajantes, textDuracao, textDestino;
    private Button btnAddControle;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dados_gerais);

        textNomeViagem = findViewById(R.id.txtNomeViagem);
        textViajantes = findViewById(R.id.txtViajantes);
        textDuracao = findViewById(R.id.txtDuracao);
        textDestino = findViewById(R.id.txtDestino);

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
        String nomeViagem = String.valueOf(textNomeViagem.getText()).trim();
        String viajantes = String.valueOf(textViajantes.getText()).trim();
        String duracao = String.valueOf(textDuracao.getText()).trim();
        String destino = String.valueOf(textDestino.getText()).trim();

        if (nomeViagem.isEmpty() || viajantes.isEmpty() || duracao.isEmpty() || destino.isEmpty()) {
            Toast.makeText(DadosGeraisActivity.this, "Por favor, preencha todos os campos", Toast.LENGTH_SHORT).show();
        }
        DadosGeraisModel dadosGeraisModel = new DadosGeraisModel();
        dadosGeraisModel.setNomeViagem(nomeViagem);
        dadosGeraisModel.setViajantes(Integer.parseInt(viajantes));
        dadosGeraisModel.setDuracao(duracao);
        dadosGeraisModel.setDestino(destino);
        dadosGeraisModel.setIdUsuario(MyApplication.getInstance().getIdUsuarioLogado());
//        dadosGeraisDAO.Insert(dadosGeraisModel);

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
