package com.empresa.portfolio.mapper;

import com.empresa.portfolio.dto.ProjetoDTO;
import com.empresa.portfolio.model.Pessoa;
import com.empresa.portfolio.model.Projeto;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import static java.time.LocalDate.parse;

public class ProjetoMapper {
    static DateTimeFormatter fmt = DateTimeFormatter.ISO_LOCAL_DATE;

    public static ProjetoDTO toDTO(Projeto projeto) {
        ProjetoDTO dto = new ProjetoDTO();
        dto.setId(projeto.getId());
        dto.setNome(projeto.getNome());
        dto.setDescricao(projeto.getDescricao());
        dto.setStatus(projeto.getStatus());
        dto.setRisco(projeto.getRisco());
        dto.setDataInicio(formatar(projeto.getDataInicio(), fmt));
        dto.setDataFim(formatar(projeto.getDataFim(), fmt));
        dto.setDataPrevisaoFim(formatar(projeto.getDataPrevisaoFim(), fmt));
        dto.setOrcamento(projeto.getOrcamento());
        dto.setGerenteId(projeto.getGerente() != null ? projeto.getGerente().getId() : null);
        dto.setGerente(projeto.getGerente());
        return dto;
    }

    public static Projeto toEntity(ProjetoDTO dto, Pessoa gerente) {
        Projeto projeto = new Projeto();
        projeto.setId(dto.getId());
        projeto.setNome(dto.getNome());
        projeto.setDescricao(dto.getDescricao());
        projeto.setStatus(dto.getStatus());
        projeto.setRisco(dto.getRisco());
        projeto.setDataInicio(parse(dto.getDataInicio(), fmt));
        projeto.setDataFim(parse(dto.getDataFim(), fmt));
        projeto.setDataPrevisaoFim(parse(dto.getDataPrevisaoFim(), fmt));
        projeto.setOrcamento(dto.getOrcamento());
        projeto.setGerente(gerente);
        return projeto;
    }

    private static String formatar(LocalDate d, DateTimeFormatter fmt) {
        return d == null ? "" : d.format(fmt);
    }
}
