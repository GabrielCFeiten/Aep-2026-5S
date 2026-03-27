package com.observaAcao.services;

import com.observaAcao.repositories.SolicitacaoRepository;
import com.observaAcao.models.SolicitacaoModel;

import java.time.LocalDate;
import java.util.List;

public class SolicitacaoService {

    private SolicitacaoRepository db = new SolicitacaoRepository();

    public void criar(String protocolo, String categoria, String descricao, String localizacao, enums.PrioridadeEnum prioridade, enums.StatusEnum status, LocalDate prazo) {
        if (descricao == null || descricao.length() < 10) {
            throw new RuntimeException("Descrição inválida");
        }

        db.salvar(new SolicitacaoModel(protocolo, categoria, descricao, localizacao, prioridade, status, prazo));
    }

    public List<SolicitacaoModel> listar() {
        return db.listar();
    }
}
