package com.empresa.portfolio.service;

import com.empresa.portfolio.model.Projeto;
import java.util.List;

public interface ProjetoService {
    List<Projeto> listAll();
    Projeto findById(Long id);
    void saveOrUpdate(Projeto projeto);
    void delete(Long id);
}