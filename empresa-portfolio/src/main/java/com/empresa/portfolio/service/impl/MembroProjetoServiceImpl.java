package com.empresa.portfolio.service.impl;

import com.empresa.portfolio.model.MembroProjeto;
import com.empresa.portfolio.model.Pessoa;
import com.empresa.portfolio.model.Projeto;
import com.empresa.portfolio.repository.MembroProjetoRepository;
import com.empresa.portfolio.repository.PessoaRepository;
import com.empresa.portfolio.repository.ProjetoRepository;
import com.empresa.portfolio.service.MembroProjetoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MembroProjetoServiceImpl implements MembroProjetoService {

    @Autowired
    private PessoaRepository pessoaRepository;

    @Autowired
    private ProjetoRepository projetoRepository;

    @Autowired
    private MembroProjetoRepository membroProjetoRepository;

    @Override
    public MembroProjeto associarPessoaAoProjeto(Long idProjeto, Long idPessoa, String papel) {
        Pessoa pessoa = pessoaRepository.findById(idPessoa)
                .orElseThrow(() -> new RuntimeException("Pessoa não encontrada"));

        Projeto projeto = projetoRepository.findById(idProjeto)
                .orElseThrow(() -> new RuntimeException("Projeto não encontrado"));

        if (!pessoa.isFuncionario()) {
            throw new IllegalArgumentException("Somente pessoas com atribuição 'Funcionario' podem ser membros de projeto.");
        }

        MembroProjeto membro = new MembroProjeto();
        membro.setProjeto(projeto);
        membro.setPessoa(pessoa);
        membro.setPapel(papel);
        return membroProjetoRepository.save(membro);
    }
}
