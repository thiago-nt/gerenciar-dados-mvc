package com.empresa.portfolio.repository;

import com.empresa.portfolio.model.MembroProjeto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MembroProjetoRepository extends JpaRepository<MembroProjeto, Long> {
}
