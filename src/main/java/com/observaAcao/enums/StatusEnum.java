package com.observaAcao.enums;

public enum StatusEnum {
    ABERTO,
    TRIAGEM,
    EM_EXECUCAO,
    RESOLVIDO,
    ENCERRADO;

    private StatusEnum status;

    public void setStatus(StatusEnum status) {
        this.status = status;
    }

    public StatusEnum getStatus() {
        return status;
    }
}
