package com.observaAcao.enums;

public enum CategoriaEnum {
        DENUNCIAR_ESGOTO_CEU_ABERTO(PrioridadeEnum.ALTA),
        DENUNCIAR_LIXO_ACUMULADO(PrioridadeEnum.MEDIA),
        DENUNCIAR_AGUA_PARADA(PrioridadeEnum.ALTA),
        DENUNCIAR_ESTABELECIMENTO_IRREGULAR(PrioridadeEnum.ALTA),
        SOLICITAR_EMISSAO_ALVARA_SANITARIO(PrioridadeEnum.BAIXA),
        SOLICITAR_RENOVACAO_ALVARA(PrioridadeEnum.MEDIA),
        SOLICITAR_VISTORIA_SANITARIA(PrioridadeEnum.MEDIA),
        DENUNCIAR_ALIMENTOS_ESTRAGADOS(PrioridadeEnum.ALTA),
        DENUNCIAR_FALTA_HIGIENE_ESTABELECIMENTO(PrioridadeEnum.ALTA),
        DENUNCIAR_PRAGAS_URBANAS(PrioridadeEnum.MEDIA),
        SOLICITAR_DESINFECCAO_LOCAL(PrioridadeEnum.MEDIA),
        SOLICITAR_COLETA_RESIDUOS_ESPECIAIS(PrioridadeEnum.BAIXA),
        DENUNCIAR_POLUICAO_AGUA(PrioridadeEnum.ALTA),
        DENUNCIAR_MA_ARMAZENAGEM_MEDICAMENTOS(PrioridadeEnum.ALTA);

        private final PrioridadeEnum prioridade;

        CategoriaEnum(PrioridadeEnum prioridade) {
            this.prioridade = prioridade;
        }

        public PrioridadeEnum getPrioridade() {
            return prioridade;
        }
}
