package com.example.waypoint;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.example.waypoint.database.dao.DadosGeraisDAO;
import com.example.waypoint.database.dao.ViagemDAO;
import com.example.waypoint.database.model.DadosGeraisModel;
import com.example.waypoint.database.model.ViagemModel;

import java.util.ArrayList;

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
    }


    private void criarLayoutsViagens(ArrayList<ViagemModel> listaViagens) {
        idViagemAtual = MyApplication.getInstance().getIdViagemAtual();
        ArrayList<DadosGeraisModel> listaDadosGerais = dadosGeraisDAO.selectAll(idUsuarioLogado);

        String destino = null;

        containerLayout = findViewById(R.id.containerLayout);

        for (ViagemModel viagemModel : listaViagens) {
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

            if (!listaDadosGerais.isEmpty()) {
                DadosGeraisModel dadosGeraisModel = listaDadosGerais.get(0);
                destino = String.valueOf(dadosGeraisModel.getNomeViagem());
            }
            TextView textView = new TextView(this);
            LinearLayout.LayoutParams textViewParams = new LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
            );
            textView.setLayoutParams(textViewParams);
            textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 26);
            textView.setTypeface(null, Typeface.BOLD);
            String textoViagem = "Viagem - " + destino;
            textView.setText(textoViagem);

            EditText editText = new EditText(this);
            LinearLayout.LayoutParams editTextParams = new LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    dpToPx(this, 50)
            );
            editText.setLayoutParams(editTextParams);
            editText.setHint("Total: R$");
            editText.setHintTextColor(ContextCompat.getColor(this, R.color.hintGray));
            String totalViagem = String.valueOf(viagemModel.getTotal());
            editText.setText(totalViagem);

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
        }
    }

    private int dpToPx(Context context, int dp) {
        float density = context.getResources().getDisplayMetrics().density;
        return Math.round(dp * density);
    }
}