package com.example.waypoint;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.waypoint.api.API;
import com.example.waypoint.api.response.Aereo;
import com.example.waypoint.api.response.Entretenimento;
import com.example.waypoint.api.response.Gasolina;
import com.example.waypoint.api.response.Hospedagem;
import com.example.waypoint.api.response.Refeicao;
import com.example.waypoint.api.response.Resposta;
import com.example.waypoint.api.response.Viagem;
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
import java.util.Collection;
import java.util.List;
import java.util.Locale;

import cn.pedant.SweetAlert.SweetAlertDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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

    float total, custoPessoa;

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

        btnPostNuvem = findViewById(R.id.btnPostNuvem);
        btnPostNuvem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                long idViagem = getIntent().getLongExtra("idViagem", -1);
                if (idViagem != -1) {
                    enviarViagem(idViagem);
                }
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

        float viajantes = 0, totalGasolina = 0, totalTarifa = 0, totalRefeicao = 0, totalHospedagem = 0, totalDiversos = 0;

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

    private void enviarViagem(long idViagem) {
        ArrayList<DadosGeraisModel> listaDadosGerais = dadosGeraisDAO.selectByViagemId(idViagem);
        ArrayList<GasolinaModel> listaGasolina = gasolinaDAO.selectByViagemId(idViagem);
        ArrayList<TarifaAereaModel> listaTarifaAerea = tarifaAereaDAO.selectByViagemId(idViagem);
        ArrayList<RefeicoesModel> listaRefeicoes = refeicoesDAO.selectByViagemId(idViagem);
        ArrayList<HospedagemModel> listaHospedagem = hospedagemDAO.selectByViagemId(idViagem);
        ArrayList<DiversosModel> listaDiversos = diversosDAO.selectByViagemId(idViagem);

        Viagem viagem = new Viagem();

        if (!listaDadosGerais.isEmpty()) {
            DadosGeraisModel dadosGeraisModel = listaDadosGerais.get(0);
            viagem.setTotalViajante((int) dadosGeraisModel.getViajantes());
            viagem.setDuracaoViagem((int) dadosGeraisModel.getDuracao());
            viagem.setCustoTotalViagem(total);
            viagem.setCustoPorPessoa(custoPessoa);
            viagem.setLocal(dadosGeraisModel.getDestino());
            viagem.setIdConta(94737);
        }

        Gasolina gasolina = new Gasolina();
        if (!listaGasolina.isEmpty()) {
            GasolinaModel gasolinaModel = listaGasolina.get(0);
            gasolina.setViagemid((int) idViagem);
            gasolina.setTotalEstimadoKM((int) gasolinaModel.getKmTotal());
            gasolina.setMediaKMLitro(gasolinaModel.getMediaKmLitro());
            gasolina.setCustoMedioLitro(gasolinaModel.getCustoLitro());
            gasolina.setTotalVeiculos((int) gasolinaModel.getTotalVeiculos());
            gasolina.setIdConta(94737);
            viagem.setGasolina(gasolina);
        }

        Aereo aereo = new Aereo();
        if (!listaTarifaAerea.isEmpty()) {
            TarifaAereaModel tarifaAereaModel = listaTarifaAerea.get(0);
            aereo.setViagemid((int) idViagem);
            aereo.setCustoPessoa(tarifaAereaModel.getCustoPessoa());
            aereo.setCustoAluguelVeiculo(tarifaAereaModel.getAluguelVeiculo());
            aereo.setIdConta(94737);
            viagem.setAereo(aereo);
        }

        Refeicao refeicao = new Refeicao();
        if (!listaRefeicoes.isEmpty()) {
            RefeicoesModel refeicoesModel = listaRefeicoes.get(0);
            refeicao.setViagemid((int) idViagem);
            refeicao.setCustoRefeicao(refeicoesModel.getCustoRefeicao());
            refeicao.setRefeicoesDia((int) refeicoesModel.getRefeicoesDia());
            refeicao.setIdConta(94737);
            viagem.setRefeicao(refeicao);
        }

        Hospedagem hospedagem = new Hospedagem();
        if (!listaHospedagem.isEmpty()) {
            HospedagemModel hospedagemModel = listaHospedagem.get(0);
            hospedagem.setViagemid((int) idViagem);
            hospedagem.setCustoMedioNoite(hospedagemModel.getCustoMedio());
            hospedagem.setTotalNoite((int) hospedagemModel.getTotalNoites());
            hospedagem.setTotalQuartos((int) hospedagemModel.getTotalQuartos());
            hospedagem.setIdConta(94737);
            viagem.setHospedagem(hospedagem);
        }

        List<Entretenimento> entretenimentoList = new ArrayList<>();
        if (!listaDiversos.isEmpty()) {
            for (DiversosModel diversosModel : listaDiversos) {
                Entretenimento entretenimento = new Entretenimento();
                entretenimento.setViagemid((int) idViagem);
                entretenimento.setEntretenimento(diversosModel.getNomeLocal());
                entretenimento.setValor(diversosModel.getCusto());
                entretenimento.setIdConta(94737);
                entretenimentoList.add(entretenimento);
            }
        }
        viagem.setEntretenimentoList(entretenimentoList);

        Log.d("ViagemObject", viagem.toString());

//        API.postViagem(viagem, new Callback<Resposta>() {
//            @Override
//            public void onResponse(Call<Resposta> call, Response<Resposta> response) {
//                if (response.isSuccessful()) {
//                    Toast.makeText(ResumoActivity.this, "Dados enviados com sucesso!", Toast.LENGTH_SHORT).show();
//                } else {
//                    Toast.makeText(ResumoActivity.this, "Falha ao enviar dados!", Toast.LENGTH_SHORT).show();
//                }
//            }
//
//            @Override
//            public void onFailure(Call<Resposta> call, Throwable t) {
//                Toast.makeText(ResumoActivity.this, "Erro: " + t.getMessage(), Toast.LENGTH_SHORT).show();
//            }
//        });


//        SweetAlertDialog pDialog = new SweetAlertDialog(ResumoActivity.this, SweetAlertDialog.PROGRESS_TYPE);
//        pDialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
//        pDialog.setTitleText("Aguarde");
//        pDialog.setContentText("Enviando dados ao servidor ...");
//        pDialog.setCancelable(false);
//        pDialog.show();

//        Log.d("ViagemObject", new Callback<Resposta>() {
//        API.postViagem(viagem, new Callback<Resposta>() {
//            @Override
//            public void onResponse(Call<Resposta> call, Response<Resposta> response) {
//                pDialog.cancel();
//                if (response != null && response.isSuccessful()) {
//                    Resposta resposta = response.body();
//
//                    AlertDialog.Builder alert = new AlertDialog.Builder(ResumoActivity.this);
//                    alert.setTitle("Sucesso");
//                    alert.setMessage("Dados enviados com sucesso!");
//                    alert.create().show();
//                }
//                else {
//                    AlertDialog.Builder alert = new AlertDialog.Builder(ResumoActivity.this);
//                    alert.setTitle("ERROR");
//                    alert.setMessage("Erro ao enviar dados para o servidor!");
//                    alert.create().show();
//                }
//            }
//
//            @Override
//            public void onFailure(Call<Resposta> call, Throwable t) {
//                pDialog.cancel();
//                AlertDialog.Builder alert = new AlertDialog.Builder(ResumoActivity.this);
//                alert.setTitle("ERROR");
//                alert.setMessage(t.getMessage());
//                alert.create().show();
//            }
//        });
    }
}