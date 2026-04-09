package com.observaAcao.configuracaoDB;

import com.observaAcao.models.SolicitacaoModel;
import com.observaAcao.models.HistoricoModel;

import java.util.List;

public class SolicitacaoCompletaDTO {

    private SolicitacaoModel solicitacao;
    private List<HistoricoModel> historico;

    public SolicitacaoCompletaDTO(SolicitacaoModel solicitacao,
                                  List<HistoricoModel> historico) {
        this.solicitacao = solicitacao;
        this.historico = historico;
    }

    public SolicitacaoModel getSolicitacao() {
        return solicitacao;
    }

    public List<HistoricoModel> getHistorico() {
        return historico;
    }
}