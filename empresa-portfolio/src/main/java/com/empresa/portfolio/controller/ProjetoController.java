package com.empresa.portfolio.controller;

import com.empresa.portfolio.common.constants.Messages;
import com.empresa.portfolio.common.constants.Redirects;
import com.empresa.portfolio.common.constants.Templates;
import com.empresa.portfolio.common.enums.RiscoProjeto;
import com.empresa.portfolio.common.enums.StatusProjeto;
import com.empresa.portfolio.dto.PessoaDTO;
import com.empresa.portfolio.dto.ProjetoDTO;
import com.empresa.portfolio.model.Projeto;
import com.empresa.portfolio.service.PessoaService;
import com.empresa.portfolio.service.ProjetoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import java.util.List;

@Controller
@RequestMapping("/projetos")
@RequiredArgsConstructor
public class ProjetoController {

    private final ProjetoService projetoService;
    private final PessoaService pessoaService;

    @GetMapping
    public String listar(Model model) {
        List<ProjetoDTO> projetos = projetoService.listarTodos();
        model.addAttribute("projetos", projetos);
        return Templates.PROJETO_LISTAR;
    }

    @GetMapping("/novo")
    public String novoProjeto(Model model) {
        List<PessoaDTO> gerentes = pessoaService.buscarGerentes();
        model.addAttribute("projeto", new Projeto());
        model.addAttribute("gerentes", gerentes);
        model.addAttribute("status", StatusProjeto.values());
        model.addAttribute("riscos", RiscoProjeto.values());
        return Templates.PROJETO_FORM;
    }

    @PostMapping("/salvar")
    public String salvarProjeto(@Valid @ModelAttribute ProjetoDTO projeto, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("gerentes", pessoaService.buscarGerentes());
            model.addAttribute("status", StatusProjeto.values());
            model.addAttribute("riscos", RiscoProjeto.values());
            return Templates.PROJETO_FORM;
        }
        projetoService.salvar(projeto);
        return Redirects.PROJETOS;
    }

    @GetMapping("/editar/{id}")
    public String editarProjeto(@PathVariable Long id, Model model) {
        ProjetoDTO projeto = projetoService.buscarPorId(id);
        List<PessoaDTO> gerentes = pessoaService.buscarGerentes();
        model.addAttribute("projeto", projeto);
        model.addAttribute("gerentes", gerentes);
        model.addAttribute("status", StatusProjeto.values());
        model.addAttribute("riscos", RiscoProjeto.values());
        return Templates.PROJETO_FORM;
    }

    @GetMapping("/excluir/{id}")
    public String excluirProjeto(@PathVariable Long id, RedirectAttributes redirect) {
        try {
            projetoService.excluir(id);
            redirect.addFlashAttribute("sucesso",  Messages.SUCESSO_EXCLUSAO);
        } catch (IllegalStateException e) {
            redirect.addFlashAttribute("erro", e.getMessage());
        }
        return Redirects.PROJETOS;
    }
}
