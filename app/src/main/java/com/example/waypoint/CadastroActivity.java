package com.example.waypoint;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.waypoint.database.dao.UsuarioDAO;
import com.example.waypoint.database.model.UsuarioModel;

public class CadastroActivity extends AppCompatActivity {

    private UsuarioDAO usuarioDAO;
    private EditText textUsuario, textNovaSenha, textConfirmaSenha;
    private Button btnCadastrar;
    private TextView btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        textUsuario = findViewById(R.id.textUsuario);
        textNovaSenha = findViewById(R.id.textNovaSenha);
        textConfirmaSenha = findViewById(R.id.textConfirmaSenha);
        btnCadastrar = findViewById(R.id.btnCadastrar);
        btnLogin = findViewById(R.id.btnLogin);

        btnCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cadastrar();
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(CadastroActivity.this, MainActivity.class);
                startActivity(it);
            }
        });
    }

    private void cadastrar() {
        usuarioDAO = new UsuarioDAO(CadastroActivity.this);
        String nomeUsuario = String.valueOf(textUsuario.getText()).trim();
        String senha = String.valueOf(textNovaSenha.getText()).trim();
        String confirmarSenha = String.valueOf(textConfirmaSenha.getText()).trim();

        if(usuarioDAO.verificarUsuarioExistente(nomeUsuario)) {
            Toast.makeText(CadastroActivity.this, "Este nome de usuário já está em uso", Toast.LENGTH_SHORT).show();
        }
        if (TextUtils.isEmpty(senha)) {
            Toast.makeText(CadastroActivity.this, "Por favor, insira uma senha", Toast.LENGTH_SHORT).show();
        } else {
            UsuarioModel usuarioModel = new UsuarioModel();
            usuarioModel.setNomeUsuario(nomeUsuario);
            if (senha.equals(confirmarSenha)) {
                usuarioModel.setSenha(confirmarSenha);
                usuarioDAO.Insert(usuarioModel);

                Intent it = new Intent(CadastroActivity.this, MainActivity.class);
                startActivity(it);
                finish();
            } else {
                Toast.makeText(CadastroActivity.this, "As senhas não coincidem", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
