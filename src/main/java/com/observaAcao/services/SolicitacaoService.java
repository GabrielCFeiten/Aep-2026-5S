package com.observaAcao.services;

import com.observaAcao.repositories.SolicitacaoRepository;
import com.observaAcao.models.SolicitacaoModel;

import java.time.LocalDate;
import java.util.List;

public class SolicitacaoService {

    private SolicitacaoRepository banco = new SolicitacaoRepository();

    public void criar(String categoria, String descricao, String localizacao) {
        if (descricao == null || descricao.length() < 10) {
            throw new RuntimeException("Descrição inválida");
        }

        SolicitacaoModel solicitacao = new SolicitacaoModel(categoria,
                descricao, localizacao);

        banco.salvar(solicitacao);
    }

}
