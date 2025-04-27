package com.empresa.portfolio.service.impl;

import com.empresa.portfolio.dto.PessoaDTO;
import com.empresa.portfolio.dto.ProjetoDTO;
import com.empresa.portfolio.mapper.PessoaMapper;
import com.empresa.portfolio.mapper.ProjetoMapper;
import com.empresa.portfolio.model.Pessoa;
import com.empresa.portfolio.model.Projeto;
import com.empresa.portfolio.repository.ProjetoRepository;
import com.empresa.portfolio.service.PessoaService;
import com.empresa.portfolio.service.ProjetoService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class ProjetoServiceImpl implements ProjetoService {

    @Autowired
    private ProjetoRepository projetoRepository;

    @Autowired
    private PessoaService pessoaService;

    @Override
    public List<ProjetoDTO> listarTodos() {
        return projetoRepository.findAll()
                .stream()
                .map(ProjetoMapper::toDTO)
                .toList();
    }

    @Override
    public void excluir(Long id) {
        validarStatusProjeto(id);
        projetoRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public ProjetoDTO buscarPorId(Long id) {
        Projeto projeto = projetoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Projeto não encontrado: " + id));
        return ProjetoMapper.toDTO(projeto);
    }

    @Override
    public void salvar(ProjetoDTO projetoDTO) {
        Pessoa pessoa = validarGerente(projetoDTO);
        Projeto projeto = ProjetoMapper.toEntity(projetoDTO, pessoa);
        projetoRepository.save(projeto);
    }

    private Pessoa validarGerente(ProjetoDTO projetoDTO) {
        PessoaDTO pessoaDTO = null;
        if (projetoDTO.getGerente() != null && projetoDTO.getGerente().getId() != null) {
            pessoaDTO = pessoaService.buscarPorId(projetoDTO.getGerente().getId());
        }
        return PessoaMapper.toEntity(pessoaDTO);
    }

    private void validarStatusProjeto(Long id) {
        ProjetoDTO projetoDTO = buscarPorId(id);
        if (projetoDTO.getStatus().naoPodeSerExcluido()) {
            throw new IllegalStateException("Não é possível excluir um projeto com status: " + projetoDTO.getStatus());
        }
    }
}