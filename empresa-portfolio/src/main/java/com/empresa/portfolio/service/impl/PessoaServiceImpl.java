package com.empresa.portfolio.service.impl;

import com.empresa.portfolio.model.Pessoa;
import com.empresa.portfolio.repository.PessoaRepository;
import com.empresa.portfolio.service.PessoaService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PessoaServiceImpl implements PessoaService {

    @Autowired
    private PessoaRepository pessoaRepository;

    @Override
    public List<Pessoa> buscarGerentes() {
        return pessoaRepository.findByGerenteTrue();
    }

    @Override
    public Pessoa buscarPorId(Long id) {
        return pessoaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Pessoa não encontrada: " + id));
    }
}