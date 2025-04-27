package com.empresa.portfolio.mapper;

import com.empresa.portfolio.dto.PessoaDTO;
import com.empresa.portfolio.model.Pessoa;

public class PessoaMapper {

    public static PessoaDTO toDTO(Pessoa pessoa) {
        if (pessoa == null) {
            return null;
        }
        return new PessoaDTO(
                pessoa.getId(),
                pessoa.getNome(),
                pessoa.getDataNascimento(),
                pessoa.getCpf(),
                pessoa.isFuncionario(),
                pessoa.isGerente()
        );
    }

    public static Pessoa toEntity(PessoaDTO pessoaDTO) {
        if (pessoaDTO == null) {
            return null;
        }
        Pessoa pessoa = new Pessoa();
        pessoa.setId(pessoaDTO.getId());
        pessoa.setNome(pessoaDTO.getNome());
        pessoa.setDataNascimento(pessoaDTO.getDataNascimento());
        pessoa.setCpf(pessoaDTO.getCpf());
        pessoa.setFuncionario(pessoaDTO.isFuncionario());
        pessoa.setGerente(pessoaDTO.isGerente());
        return pessoa;
    }
}