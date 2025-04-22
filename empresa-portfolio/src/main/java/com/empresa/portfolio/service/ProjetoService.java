package com.empresa.portfolio.service;

import com.empresa.portfolio.model.Projeto;
import java.util.List;

public interface ProjetoService {

    List<Projeto> listarTodos();
    Projeto buscarPorId(Long id);
    void salvar(Projeto projeto);
    void excluir(Long id);
}