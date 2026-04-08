package com.observaAcao.models;

import com.observaAcao.enums.CategoriaEnum;
import com.observaAcao.enums.PrioridadeEnum;
import com.observaAcao.enums.StatusEnum;

import java.time.LocalDate;

public class SolicitacaoModel {

    private Integer protocolo;
    private CategoriaEnum categoria; // <-- continua sendo "categoria"
    private String descricao;
    private String bairro;
    private String endereco;
    private PrioridadeEnum prioridade;
    private StatusEnum status;
    private LocalDate prazo;
    private Integer usuarioId;

    public SolicitacaoModel(CategoriaEnum categoria,
                            String descricao,
                            String bairro, String endereco) {

        this.categoria = categoria;
        this.descricao = descricao;
        this.bairro = bairro;
        this.endereco = endereco;

        definirPrioridadeEPrazo();

    }

    private void definirPrioridadeEPrazo() {
        this.prioridade = this.categoria.getPrioridade();
        this.prazo = calcularPrazo(this.prioridade);
    }

    private LocalDate calcularPrazo(PrioridadeEnum prioridade) {
        return switch (prioridade) {
            case ALTA -> LocalDate.now().plusDays(1);
            case MEDIA -> LocalDate.now().plusDays(3);
            case BAIXA -> LocalDate.now().plusDays(7);
        };
    }

    public Integer getProtocolo() {
        return protocolo;
    }

    public void setProtocolo(Integer protocolo) {
        this.protocolo = protocolo;
    }

    public CategoriaEnum getCategoria() {
        return categoria;
    }

    public void setCategoria(CategoriaEnum categoria) {
        this.categoria = categoria;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public PrioridadeEnum getPrioridade() {
        return prioridade;
    }

    public void setPrioridade(PrioridadeEnum prioridade) {
        this.prioridade = prioridade;
    }

    public StatusEnum getStatus() {
        return status;
    }

    public void setStatus(StatusEnum status) {
        this.status = status;
    }

    public LocalDate getPrazo() {
        return prazo;
    }

    public void setPrazo(LocalDate prazo) {
        this.prazo = prazo;
    }

    public Integer getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Integer usuarioId) {
        this.usuarioId = usuarioId;
    }

}