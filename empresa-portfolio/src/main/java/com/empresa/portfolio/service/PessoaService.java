package com.empresa.portfolio.service;

import com.empresa.portfolio.dto.PessoaDTO;
import java.util.List;

public interface PessoaService {

    List<PessoaDTO> buscarGerentes();

    PessoaDTO buscarPorId(Long id);

}