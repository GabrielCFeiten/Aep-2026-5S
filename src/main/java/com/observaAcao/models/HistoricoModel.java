package com.observaAcao.models;

import com.observaAcao.enums.StatusEnum;

import java.time.LocalDateTime;

public class HistoricoModel {

    private Integer id;
    private Integer protocolo;
    private StatusEnum status;
    private LocalDateTime data;
    private Integer responsavelId;
    private String justificativa;

    public HistoricoModel(Integer protocolo, StatusEnum status,
                          Integer responsavelId, String justificativa) {
        this.protocolo = protocolo;
        this.status = status;
        this.data = LocalDateTime.now();
        this.responsavelId = responsavelId;
        this.justificativa = justificativa;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getProtocolo() {
        return protocolo;
    }

    public void setProtocolo(Integer protocolo) {
        this.protocolo = protocolo;
    }

    public LocalDateTime getData() {
        return data;
    }

    public void setData(LocalDateTime data) {
        this.data = data;
    }

    public StatusEnum getStatus() {
        return status;
    }

    public void setStatus(StatusEnum status) {
        this.status = status;
    }

    public Integer getResponsavelId() {
        return responsavelId;
    }

    public void setResponsavelId(Integer responsavelId) {
        this.responsavelId = responsavelId;
    }

    public String getJustificativa() {
        return justificativa;
    }

    public void setJustificativa(String justificativa) {
        this.justificativa = justificativa;
    }
}