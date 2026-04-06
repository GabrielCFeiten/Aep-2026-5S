package com.observaAcao.services;

import com.observaAcao.repositories.SolicitacaoRepository;
import com.observaAcao.models.SolicitacaoModel;

public class SolicitacaoService {

    private SolicitacaoRepository banco = new SolicitacaoRepository();

    public void criar(String categoria, String descricao, String localizacao) {

        if (descricao == null || descricao.trim().length() < 10) {
            throw new RuntimeException("Descrição deve ter no mínimo 10 caracteres");
        }

        SolicitacaoModel solicitacao = new SolicitacaoModel(
                categoria,
                descricao,
                localizacao
        );

        banco.salvar(solicitacao);
    }
}