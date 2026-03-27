package models;

import enums.CategoriaEnum;
import enums.PrioridadeEnum;
import enums.StatusEnum;

import java.time.LocalDate;

public class SolicitaçãoModel {

    private String protocolo;
    private String categoria;
    private String descricao;
    private String localização;
    private PrioridadeEnum prioridade;
    private StatusEnum status;
    private LocalDate prazo;

    public SolicitaçãoModel(String protocolo, String categoria, String descricao, String localização, PrioridadeEnum prioridade, StatusEnum status, LocalDate prazo) {
        this.protocolo = protocolo;
        this.categoria = categoria;
        this.descricao = descricao;
        this.localização = localização;
        this.prioridade = prioridade;
        this.status = status;
        this.prazo = prazo;
    }

    public String getProtocolo() {
        return protocolo;
    }

    public String getCategoria() {
        return categoria;
    }

    public String getDescricao() {
        return descricao;
    }

    public String getLocalização() {
        return localização;
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

    public void setLocalização(String localização) {
        this.localização = localização;
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
