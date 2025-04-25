package com.empresa.portfolio.controller;

import com.empresa.portfolio.dto.AssociacaoDTO;
import com.empresa.portfolio.model.MembroProjeto;
import com.empresa.portfolio.service.MembroProjetoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/membros")
public class MembroProjetoController {

    @Autowired
    private MembroProjetoService service;

    @PostMapping("/associacao")
    public ResponseEntity<MembroProjeto> associar(@RequestBody AssociacaoDTO dto) {
        MembroProjeto associado = service.associarPessoaAoProjeto(dto.getIdProjeto(), dto.getIdPessoa(), dto.getPapel());
        return ResponseEntity.ok(associado);
    }
}
