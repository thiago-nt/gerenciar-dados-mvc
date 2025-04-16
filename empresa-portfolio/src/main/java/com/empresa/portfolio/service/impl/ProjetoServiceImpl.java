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
    public List<Projeto> listAll() {
        return projetoRepository.findAll();
    }

    @Override
    public Projeto findById(Long id) {
        return projetoRepository.findById(id).orElse(null);
    }

    @Override
    public void saveOrUpdate(Projeto projeto) {
        projetoRepository.save(projeto);
    }

    @Override
    public void delete(Long id) {
        projetoRepository.deleteById(id);
    }
}