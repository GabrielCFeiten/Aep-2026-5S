package com.observaAcao.configuracaoDB;

import com.observaAcao.models.HistoricoModel;
import com.observaAcao.models.SolicitacaoModel;

public class SolicitacaoResumoDTO {

    private SolicitacaoModel solicitacao;
    private HistoricoModel ultimoHistorico;

    public SolicitacaoResumoDTO(SolicitacaoModel s, HistoricoModel h) {
        this.solicitacao = s;
        this.ultimoHistorico = h;
    }

    public SolicitacaoModel getSolicitacao() {
        return solicitacao;
    }

    public HistoricoModel getUltimoHistorico() {
        return ultimoHistorico;
    }
}