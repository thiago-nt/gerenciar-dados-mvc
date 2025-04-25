package com.empresa.portfolio.service.impl;

import com.empresa.portfolio.model.Projeto;
import com.empresa.portfolio.repository.ProjetoRepository;
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

    @Override
    public List<Projeto> listarTodos() {
        return projetoRepository.findAll();
    }

    @Override
    public void salvar(Projeto projeto) {
        projetoRepository.save(projeto);
    }

    @Override
    public void excluir(Long id) {
        projetoRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Projeto buscarPorId(Long id) {
        return projetoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Projeto não encontrado: " + id));
    }

    @Override
    public void excluirProjetoComValidacao(Long id) {
        Projeto projeto = buscarPorId(id);
        if (projeto.getStatus().naoPodeSerExcluido()) {
            throw new IllegalStateException("Não é possível excluir um projeto com status: " + projeto.getStatus());
        }
        excluir(id);
    }
}