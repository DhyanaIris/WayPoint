package com.example.waypoint;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.InputType;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.example.waypoint.database.dao.DadosGeraisDAO;
import com.example.waypoint.database.dao.ViagemDAO;
import com.example.waypoint.database.model.DadosGeraisModel;
import com.example.waypoint.database.model.ViagemModel;

import java.util.ArrayList;
import java.util.Locale;

public class ListaViagensActivity extends AppCompatActivity {

    private LinearLayout containerLayout;
    private ViagemDAO viagemDAO;
    private DadosGeraisDAO dadosGeraisDAO;
    private Button btnNovaViagem;
    private long idUsuarioLogado, idViagemAtual;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_viagens);

        viagemDAO = new ViagemDAO(ListaViagensActivity.this);
        dadosGeraisDAO = new DadosGeraisDAO(ListaViagensActivity.this);
        idUsuarioLogado = MyApplication.getInstance().getIdUsuarioLogado();

        ArrayList<ViagemModel> listaViagens = viagemDAO.selectAll(idUsuarioLogado);
        criarLayoutsViagens(listaViagens);

        btnNovaViagem = findViewById(R.id.btnNovaViagem);
        btnNovaViagem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ViagemModel viagem = new ViagemModel();
                viagem.setIdUsuario(idUsuarioLogado);

                long viagemId = viagemDAO.Insert(viagem);

                MyApplication.getInstance().setIdViagemAtual(viagemId);

                Intent it = new Intent(ListaViagensActivity.this, DadosGeraisActivity.class);
                startActivity(it);
            }
        });

        OnBackPressedCallback onBackPressedCallback = new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                Intent intent = new Intent(ListaViagensActivity.this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                finish();
            }
        };
        getOnBackPressedDispatcher().addCallback(this,onBackPressedCallback);
    }

    private void criarLayoutsViagens(ArrayList<ViagemModel> listaViagens) {
        long idUsuarioLogado = MyApplication.getInstance().getIdUsuarioLogado(); // Obtendo id do usuário logado
        ArrayList<DadosGeraisModel> listaDadosGerais = dadosGeraisDAO.selectAll(idUsuarioLogado);

        containerLayout = findViewById(R.id.containerLayout);

        for (ViagemModel viagemModel : listaViagens) {
            DadosGeraisModel dadosGeraisModel = null;
            for (DadosGeraisModel dados : listaDadosGerais) {
                if (dados.getIdViagem() == viagemModel.getId()) {
                    dadosGeraisModel = dados;
                    break;
                }
            }

            String nomeViagem = (dadosGeraisModel != null) ? dadosGeraisModel.getNomeViagem() : "Desconhecido";

            float totalViagem = viagemModel.getTotal();

            LinearLayout outerLayout = new LinearLayout(this);
            LinearLayout.LayoutParams outerParams = new LinearLayout.LayoutParams(
                    dpToPx(this, 350),
                    ViewGroup.LayoutParams.WRAP_CONTENT
            );
            outerParams.gravity = Gravity.CENTER;
            outerParams.topMargin = dpToPx(this, 20);
            outerLayout.setLayoutParams(outerParams);
            outerLayout.setPadding(dpToPx(this, 20), dpToPx(this, 20), dpToPx(this, 20), dpToPx(this, 20));
            outerLayout.setBackground(ContextCompat.getDrawable(this, R.drawable.rounded));
            outerLayout.setOrientation(LinearLayout.HORIZONTAL);

            LinearLayout innerLayout = new LinearLayout(this);
            LinearLayout.LayoutParams innerParams = new LinearLayout.LayoutParams(
                    dpToPx(this, 230),
                    ViewGroup.LayoutParams.WRAP_CONTENT
            );
            innerParams.gravity = Gravity.CENTER;
            innerLayout.setLayoutParams(innerParams);
            innerLayout.setOrientation(LinearLayout.VERTICAL);

            TextView textView = new TextView(this);
            LinearLayout.LayoutParams textViewParams = new LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
            );
            textView.setLayoutParams(textViewParams);
            textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 26);
            textView.setTypeface(null, Typeface.BOLD);
            textView.setText(nomeViagem);

            EditText editText = new EditText(this);
            LinearLayout.LayoutParams editTextParams = new LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    dpToPx(this, 50)
            );
            editText.setLayoutParams(editTextParams);
            editText.setHintTextColor(ContextCompat.getColor(this, R.color.hintGray));
            String textoTotal = String.format(Locale.getDefault(), "Total: R$ %.2f", totalViagem);
            editText.setText((textoTotal));
            editText.setFocusable(false);
            editText.setClickable(false);
            editText.setInputType(InputType.TYPE_NULL);

            innerLayout.addView(textView);
            innerLayout.addView(editText);

            outerLayout.addView(innerLayout);

            ImageView logoImageView = new ImageView(this);
            LinearLayout.LayoutParams logoParams = new LinearLayout.LayoutParams(
                    dpToPx(this, 75),
                    dpToPx(this, 75)
            );
            logoImageView.setLayoutParams(logoParams);
            logoImageView.setBackground(ContextCompat.getDrawable(this, R.drawable.logo_viagem));
            outerLayout.addView(logoImageView);

            containerLayout.addView(outerLayout);

            outerLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Open RelatorioActivity and pass idViagem
                    Intent intent = new Intent(ListaViagensActivity.this, ResumoActivity.class);
                    intent.putExtra("idViagem", viagemModel.getId());
                    startActivity(intent);
                }
            });
        }
    }

    private int dpToPx(Context context, int dp) {
        float density = context.getResources().getDisplayMetrics().density;
        return Math.round(dp * density);
    }
}

