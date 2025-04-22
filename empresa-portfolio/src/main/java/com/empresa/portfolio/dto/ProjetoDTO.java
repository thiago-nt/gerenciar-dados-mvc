package com.empresa.portfolio.dto;
import com.empresa.portfolio.model.Projeto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
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
    private String status;
    private Double orcamento;
    private String risco;
    private Long gerenteId;

    public ProjetoDTO(Projeto projeto) {
        this.id = projeto.getId();
        this.nome = projeto.getNome();
        this.dataInicio = formatar(projeto.getDataInicio());
        this.dataPrevisaoFim = formatar(projeto.getDataPrevisaoFim());
        this.dataFim = formatar(projeto.getDataFim());
        this.descricao = projeto.getDescricao();
        this.status = projeto.getStatus();
        this.orcamento = projeto.getOrcamento();
        this.risco = projeto.getRisco();
        this.gerenteId = projeto.getGerente() != null ? projeto.getGerente().getId() : null;
    }

    private String formatar(LocalDate data) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return data != null ? data.format(formatter) : null;
    }
}