package com.example.waypoint;

import static androidx.core.content.ContextCompat.startActivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.waypoint.database.dao.UsuarioDAO;

public class MainActivity extends AppCompatActivity {

    private EditText txtUsuario, txtSenha;
    private Button btnLogin;
    private TextView btnCadastro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtUsuario = findViewById(R.id.txtUsuario);
        txtSenha = findViewById(R.id.txtSenha);

        btnCadastro = findViewById(R.id.btnCadastro);
        btnCadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(MainActivity.this, CadastroActivity.class);
                startActivity(it);
            }
        });

        btnLogin = findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });
    };

    private void login() {
        String nomeUsuario = txtUsuario.getText().toString();
        String senha = txtSenha.getText().toString();

        UsuarioDAO usuarioDAO = new UsuarioDAO(MainActivity.this);

        if (usuarioDAO.validarCredenciais(nomeUsuario, senha)) {
            long idUsuario = usuarioDAO.getIdUsuario(nomeUsuario);
            MyApplication.getInstance().setIdUsuarioLogado(idUsuario);

            Intent it = new Intent(MainActivity.this, ListaViagensActivity.class);
            startActivity(it);
        } else {
            Toast.makeText(MainActivity.this, "Credenciais inválidas", Toast.LENGTH_SHORT).show();
        }
    }

}
