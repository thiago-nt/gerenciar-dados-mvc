package com.empresa.portfolio.service;

import com.empresa.portfolio.model.Pessoa;
import java.util.List;

public interface PessoaService {

    List<Pessoa> buscarGerentes();

    Pessoa buscarPorId(Long id);

}