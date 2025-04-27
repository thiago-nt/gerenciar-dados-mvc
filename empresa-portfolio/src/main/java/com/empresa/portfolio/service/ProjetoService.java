package com.empresa.portfolio.service;

import com.empresa.portfolio.dto.ProjetoDTO;
import com.empresa.portfolio.model.Pessoa;
import com.empresa.portfolio.model.Projeto;
import java.util.List;

public interface ProjetoService {

    List<ProjetoDTO> listarTodos();
    ProjetoDTO buscarPorId(Long id);
    void excluir(Long id);
    void salvar(ProjetoDTO projeto);
}