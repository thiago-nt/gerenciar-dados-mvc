package com.empresa.portfolio.common.enums;

import lombok.Getter;

@Getter
public enum StatusProjeto {
    EM_ANALISE("Em Análise"),
    ANALISE_REALIZADA("Análise Realizada"),
    ANALISE_APROVADA("Análise Aprovada"),
    INICIADO("Iniciado"),
    PLANEJADO("Planejado"),
    EM_ANDAMENTO("Em Andamento"),
    ENCERRADO("Encerrado"),
    CANCELADO("Cancelado");

    private final String label;

    public boolean naoPodeSerExcluido() {
        return this == INICIADO || this == EM_ANDAMENTO || this == ENCERRADO;
    }

    StatusProjeto(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
