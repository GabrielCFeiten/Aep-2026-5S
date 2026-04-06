package com.observaAcao.models;

import enums.PrioridadeEnum;
import enums.StatusEnum;

import java.time.LocalDate;

public class SolicitacaoModel {

    private static Integer contador = 1;
    private final Integer protocolo;
    private String categoria;
    private String descricao;
    private String localizacao;
    private PrioridadeEnum prioridade;
    private StatusEnum status;
    private LocalDate prazo;

    public SolicitacaoModel(String categoria,
                            String descricao,
                            String localizacao) {
        this.protocolo = contador++;
        this.categoria = categoria;
        this.descricao = descricao;
        this.localizacao = localizacao;
        this.status = StatusEnum.ABERTO;
    }

    public Integer getProtocolo() {
        return protocolo;
    }

    public String getCategoria() {
        return categoria;
    }

    public String getDescricao() {
        return descricao;
    }

    public String getLocalizacao() {
        return localizacao;
    }

    public PrioridadeEnum getPrioridade() {
        return prioridade;
    }

    public StatusEnum getStatus() {
        return status;
    }

    public LocalDate getPrazo() {
        return prazo;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setLocalizacao(String localizacao) {
        this.localizacao = localizacao;
    }

    public void setPrioridade(PrioridadeEnum prioridade) {
        this.prioridade = prioridade;
    }

    public void setStatus(StatusEnum status) {
        this.status = status;
    }

    public void setPrazo(LocalDate prazo) {
        this.prazo = prazo;
    }
}
