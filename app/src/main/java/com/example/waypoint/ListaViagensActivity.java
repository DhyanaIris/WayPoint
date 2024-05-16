package com.example.waypoint;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.waypoint.database.dao.ViagemDAO;
import com.example.waypoint.database.model.ViagemModel;

public class ListaViagensActivity extends AppCompatActivity {

    private Button btnNovaViagem;
    private ViagemDAO viagemDAO;
    private long idUsuarioLogado;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_viagens);

        btnNovaViagem = findViewById(R.id.btnNovaViagem);
        viagemDAO = new ViagemDAO(this);

        idUsuarioLogado = MyApplication.getInstance().getIdUsuarioLogado();

        btnNovaViagem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ViagemModel viagem = new ViagemModel();
                viagem.setIdUsuario(idUsuarioLogado);

                long viagemId = viagemDAO.insertViagem(viagem);

                MyApplication.getInstance().setIdViagemAtual(viagemId);

                Intent it = new Intent(ListaViagensActivity.this, DadosGeraisActivity.class);
                startActivity(it);
            }
        });
    }
}