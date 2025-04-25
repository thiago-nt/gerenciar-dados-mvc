package com.empresa.portfolio.controller;

import com.empresa.portfolio.enumeration.RiscoProjeto;
import com.empresa.portfolio.enumeration.StatusProjeto;
import com.empresa.portfolio.model.Pessoa;
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
        List<Projeto> projetos = projetoService.listarTodos();
        model.addAttribute("projetos", projetos);
        return "projeto/listar";
    }

    @GetMapping("/novo")
    public String novoProjeto(Model model) {
        List<Pessoa> gerentes = pessoaService.buscarGerentes();

        model.addAttribute("projeto", new Projeto());
        model.addAttribute("gerentes", gerentes);
        model.addAttribute("status", StatusProjeto.values());
        model.addAttribute("riscos", RiscoProjeto.values());
        return "projeto/form";
    }

    @PostMapping("/salvar")
    public String salvarProjeto(@Valid @ModelAttribute Projeto projeto, BindingResult result, Model model) {
        if (projeto.getGerente() != null && projeto.getGerente().getId() != null) {
            Pessoa gerentePersistido = pessoaService.buscarPorId(projeto.getGerente().getId());
            projeto.setGerente(gerentePersistido);
        }
        projetoService.salvar(projeto);
        return "redirect:/projetos";
    }

    @GetMapping("/editar/{id}")
    public String editarProjeto(@PathVariable Long id, Model model) {
        Projeto projeto = projetoService.buscarPorId(id);
        List<Pessoa> gerentes = pessoaService.buscarGerentes();

        model.addAttribute("projeto", projeto);
        model.addAttribute("gerentes", gerentes);
        model.addAttribute("status", StatusProjeto.values());
        model.addAttribute("riscos", RiscoProjeto.values());
        return "projeto/form";
    }

    @GetMapping("/excluir/{id}")
    public String excluirProjeto(@PathVariable Long id, RedirectAttributes redirect) {
        try {
            projetoService.excluirProjetoComValidacao(id);
            redirect.addFlashAttribute("sucesso", "Projeto excluído com sucesso!");
        } catch (IllegalStateException e) {
            redirect.addFlashAttribute("erro", e.getMessage());
        }
        return "redirect:/projetos";
    }
}
