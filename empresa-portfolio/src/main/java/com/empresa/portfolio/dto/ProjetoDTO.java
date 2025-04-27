package com.empresa.portfolio.dto;
import com.empresa.portfolio.common.enums.RiscoProjeto;
import com.empresa.portfolio.common.enums.StatusProjeto;
import com.empresa.portfolio.model.Pessoa;
import com.empresa.portfolio.model.Projeto;
import lombok.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProjetoDTO {

    private Long id;
    private String nome;
    private String dataInicio;
    private String dataPrevisaoFim;
    private String dataFim;
    private String descricao;
    private StatusProjeto status;
    private Double orcamento;
    private RiscoProjeto risco;
    private Long gerenteId;
    private Pessoa gerente;

    public ProjetoDTO(Projeto projeto) {
        DateTimeFormatter fmt = DateTimeFormatter.ISO_LOCAL_DATE;
        this.id               = projeto.getId();
        this.nome             = projeto.getNome();
        this.dataInicio       = formatar(projeto.getDataInicio(), fmt);
        this.dataPrevisaoFim  = formatar(projeto.getDataPrevisaoFim(), fmt);
        this.dataFim          = formatar(projeto.getDataFim(), fmt);
        this.descricao        = projeto.getDescricao();
        this.status           = projeto.getStatus();
        this.orcamento        = projeto.getOrcamento();
        this.risco            = projeto.getRisco();
        this.gerenteId        = projeto.getGerente() != null ? projeto.getGerente().getId() : null;
        this.gerente          = projeto.getGerente();
    }

    private String formatar(LocalDate d, DateTimeFormatter fmt) {
        return d == null ? "" : d.format(fmt);
    }

    public Projeto toEntity(Pessoa gerente) {
        DateTimeFormatter fmt = DateTimeFormatter.ISO_LOCAL_DATE;
        return Projeto.builder()
                .id(id)
                .nome(nome)
                .dataInicio(parse(dataInicio, fmt))
                .dataPrevisaoFim(parse(dataPrevisaoFim, fmt))
                .dataFim(parse(dataFim, fmt))
                .descricao(descricao)
                .status(status)
                .orcamento(orcamento)
                .risco(risco)
                .gerente(gerente)
                .build();
    }

    private LocalDate parse(String s, DateTimeFormatter fmt) {
        return (s == null || s.isBlank()) ? null : LocalDate.parse(s, fmt);
    }
}