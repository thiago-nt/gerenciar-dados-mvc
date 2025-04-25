package com.empresa.portfolio.enumeration;

import lombok.Getter;

@Getter
public enum RiscoProjeto {
    BAIXO("Baixo"), MEDIO("Médio"), ALTO("Alto");

    private final String label;

    RiscoProjeto(String label) { this.label = label; }

    @Override public String toString() { return label; }
}
