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
import com.example.waypoint.database.dao.DiversosDAO;
import com.example.waypoint.database.dao.GasolinaDAO;
import com.example.waypoint.database.dao.HospedagemDAO;
import com.example.waypoint.database.dao.RefeicoesDAO;
import com.example.waypoint.database.dao.TarifaAereaDAO;
import com.example.waypoint.database.dao.ViagemDAO;
import com.example.waypoint.database.model.DadosGeraisModel;
import com.example.waypoint.database.model.DiversosModel;
import com.example.waypoint.database.model.GasolinaModel;
import com.example.waypoint.database.model.HospedagemModel;
import com.example.waypoint.database.model.RefeicoesModel;
import com.example.waypoint.database.model.TarifaAereaModel;
import com.example.waypoint.database.model.ViagemModel;

import java.util.ArrayList;
import java.util.Locale;

public class ResumoActivity extends AppCompatActivity {

    private ViagemDAO viagemDAO;
    private DadosGeraisDAO dadosGeraisDAO;
    private GasolinaDAO gasolinaDAO;
    private TarifaAereaDAO tarifaAereaDAO;
    private RefeicoesDAO refeicoesDAO;
    private HospedagemDAO hospedagemDAO;
    private DiversosDAO diversosDAO;
    private EditText txtTotalViajantes, txtDuracaoViagem, txtCustoTotal, txtCustoPessoa, txtDetinacao;
    private Button btnProxEtapa, btnPostNuvem, btnExcluir;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resumo);

        txtTotalViajantes = findViewById(R.id.txtTotalViajantes);
        txtDuracaoViagem = findViewById(R.id.txtDuracaoViagem);
        txtCustoTotal = findViewById(R.id.txtCustoTotal);
        txtCustoPessoa = findViewById(R.id.txtCustoPessoa);
        txtDetinacao = findViewById(R.id.txtDetinacao);

        viagemDAO = new ViagemDAO(ResumoActivity.this);
        dadosGeraisDAO = new DadosGeraisDAO(ResumoActivity.this);
        gasolinaDAO = new GasolinaDAO(ResumoActivity.this);
        tarifaAereaDAO = new TarifaAereaDAO(ResumoActivity.this);
        refeicoesDAO = new RefeicoesDAO(ResumoActivity.this);
        hospedagemDAO = new HospedagemDAO(ResumoActivity.this);
        diversosDAO = new DiversosDAO(ResumoActivity.this);

        long idViagem = getIntent().getLongExtra("idViagem", -1);
        if (idViagem != -1) {
            MyApplication.getInstance().setIdViagemAtual(idViagem);
            preencherDados(idViagem);
        }

        btnExcluir = findViewById(R.id.btnExcluir);
        btnExcluir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                long idViagem = getIntent().getLongExtra("idViagem", -1);
                if (idViagem != -1) {
                    boolean success = excluirViagem(idViagem);
                        Intent intent = new Intent(ResumoActivity.this, ListaViagensActivity.class);
                        startActivity(intent);
                        finish();
                }
            }
        });

        btnProxEtapa = findViewById(R.id.btnProxEtapa);
        btnProxEtapa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(ResumoActivity.this, ListaViagensActivity.class);
                startActivity(it);
            }
        });
    }

    private void preencherDados(long idViagem) {
        ArrayList<DadosGeraisModel> listaDadosGerais = dadosGeraisDAO.selectByViagemId(idViagem);
        ArrayList<GasolinaModel> listaGasolina = gasolinaDAO.selectByViagemId(idViagem);
        ArrayList<TarifaAereaModel> listaTarifaAerea = tarifaAereaDAO.selectByViagemId(idViagem);
        ArrayList<RefeicoesModel> listaRefeicoes = refeicoesDAO.selectByViagemId(idViagem);
        ArrayList<HospedagemModel> listaHospedagem = hospedagemDAO.selectByViagemId(idViagem);
        ArrayList<DiversosModel> listaDiversos = diversosDAO.selectByViagemId(idViagem);

        float total, custoPessoa, viajantes = 0, totalGasolina = 0, totalTarifa = 0, totalRefeicao = 0, totalHospedagem = 0, totalDiversos = 0;

        if (!listaDadosGerais.isEmpty()) {
            DadosGeraisModel dadosGeraisModel = listaDadosGerais.get(0);

            viajantes = dadosGeraisModel.getViajantes();
            txtTotalViajantes.setText(String.valueOf(viajantes));
            txtDuracaoViagem.setText(String.valueOf(dadosGeraisModel.getDuracao()));
            txtDetinacao.setText(dadosGeraisModel.getDestino());
        }
        if (!listaGasolina.isEmpty()) {
            GasolinaModel gasolinaModel = listaGasolina.get(0);
            totalGasolina = Float.parseFloat(String.valueOf(gasolinaModel.getTotal()));
        }
        if (!listaTarifaAerea.isEmpty()) {
            TarifaAereaModel tarifaAereaModel = listaTarifaAerea.get(0);
            totalTarifa = Float.parseFloat(String.valueOf(tarifaAereaModel.getTotal()));
        }
        if (!listaRefeicoes.isEmpty()) {
            RefeicoesModel refeicoesModel = listaRefeicoes.get(0);
            totalRefeicao = Float.parseFloat(String.valueOf(refeicoesModel.getTotal()));
        }
        if (!listaHospedagem.isEmpty()) {
            HospedagemModel hospedagemModel = listaHospedagem.get(0);
            totalHospedagem = Float.parseFloat(String.valueOf(hospedagemModel.getTotal()));
        }
        if (!listaDiversos.isEmpty()) {
            for (DiversosModel diversosModel : listaDiversos) {
                totalDiversos += diversosModel.getCusto();
            }
        }
        
        total = totalGasolina + totalTarifa + totalRefeicao + totalHospedagem + totalDiversos;
        custoPessoa = total / viajantes;
        String totalFormatado = String.format(Locale.getDefault(), "%.2f", total);
        String custoPessoaFormatado = String.format(Locale.getDefault(), "%.2f", custoPessoa);
        txtCustoTotal.setText(totalFormatado);
        txtCustoPessoa.setText(custoPessoaFormatado);

        ViagemModel viagemModel = new ViagemModel();
        viagemModel.setId(idViagem);
        viagemModel.setTotal(total);
        viagemDAO.Update(viagemModel);
        
    }

    private boolean excluirViagem(long idViagem) {
        boolean success = viagemDAO.Delete(idViagem);
        success &= dadosGeraisDAO.DeleteById(idViagem);
        success &= gasolinaDAO.DeleteById(idViagem);
        success &= tarifaAereaDAO.DeleteById(idViagem);
        success &= refeicoesDAO.DeleteById(idViagem);
        success &= hospedagemDAO.DeleteById(idViagem);
        success &= diversosDAO.DeleteById(idViagem);

        return success;
    }
}