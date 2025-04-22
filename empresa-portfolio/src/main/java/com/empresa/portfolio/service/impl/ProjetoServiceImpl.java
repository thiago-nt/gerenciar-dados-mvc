package com.empresa.portfolio.service.impl;

import com.empresa.portfolio.model.Projeto;
import com.empresa.portfolio.repository.ProjetoRepository;
import com.empresa.portfolio.service.ProjetoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ProjetoServiceImpl implements ProjetoService {

    @Autowired
    private ProjetoRepository projetoRepository;

    @Override
    public Projeto buscarPorId(Long id) {
        return projetoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Projeto não encontrado"));
    }

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
}