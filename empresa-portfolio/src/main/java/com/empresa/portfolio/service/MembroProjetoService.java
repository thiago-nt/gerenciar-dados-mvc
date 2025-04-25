package com.empresa.portfolio.service;

import com.empresa.portfolio.model.MembroProjeto;

public interface MembroProjetoService {
    MembroProjeto associarPessoaAoProjeto(Long idProjeto, Long idPessoa, String papel);
}
