package com.empresa.portfolio.controller;

import com.empresa.portfolio.model.Pessoa;
import com.empresa.portfolio.model.Projeto;
import com.empresa.portfolio.service.PessoaService;
import com.empresa.portfolio.service.ProjetoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/projetos")
public class ProjetoController {

    @Autowired
    private ProjetoService projetoService;

    @Autowired
    private PessoaService pessoaService;

    @GetMapping
    public String listProjetos(Model model) {
        List<Projeto> listaProjetos = projetoService.listAll();
        model.addAttribute("listaProjetos", listaProjetos);
        return "projeto-list";
    }

    @GetMapping("/new")
    public String showNewForm(Model model) {
        List<Pessoa> gerentes = pessoaService.listGerentes();
        model.addAttribute("gerentes", gerentes);
        model.addAttribute("projeto", new Projeto());
        return "projeto-form";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") Long id, Model model) {
        Projeto projeto = projetoService.findById(id);
        List<Pessoa> gerentes = pessoaService.listGerentes();
        model.addAttribute("gerentes", gerentes);
        model.addAttribute("projeto", projeto);
        return "projeto-form";
    }

    @PostMapping("/save")
    public String saveProjeto(@ModelAttribute Projeto projeto) {
        projetoService.saveOrUpdate(projeto);
        return "redirect:/projetos";
    }

    @GetMapping("/delete/{id}")
    public String deleteProjeto(@PathVariable("id") Long id) {
        Projeto projeto = projetoService.findById(id);
        if (!projeto.getStatus().equalsIgnoreCase("iniciado") &&
                !projeto.getStatus().equalsIgnoreCase("em andamento") &&
                !projeto.getStatus().equalsIgnoreCase("encerrado")) {
            projetoService.delete(id);
        }
        return "redirect:/projetos";
    }
}