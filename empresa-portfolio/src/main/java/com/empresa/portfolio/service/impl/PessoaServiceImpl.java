package com.empresa.portfolio.service.impl;

import com.empresa.portfolio.dto.PessoaDTO;
import com.empresa.portfolio.mapper.PessoaMapper;
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
    public List<PessoaDTO> buscarGerentes() {
        return pessoaRepository.findByGerenteTrue()
                .stream()
                .map(PessoaMapper::toDTO)
                .toList();
    }

    @Override
    public PessoaDTO buscarPorId(Long id) {
        Pessoa pessoa = pessoaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Pessoa não encontrada: " + id));
        return PessoaMapper.toDTO(pessoa);
    }
}