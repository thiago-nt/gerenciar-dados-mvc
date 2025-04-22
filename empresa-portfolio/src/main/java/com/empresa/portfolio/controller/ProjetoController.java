package com.empresa.portfolio.controller;

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

    private final List<String> statusRestritosParaExclusao = List.of("Iniciado", "Em Andamento", "Encerrado");
    private final List<String> statusDisponiveis = List.of(
            "Em Análise", "Análise Realizada", "Análise Aprovada", "Iniciado",
            "Planejado", "Em Andamento", "Encerrado", "Cancelado"
    );
    private final List<String> riscoDisponiveis = List.of("Baixo", "Médio", "Alto");

    // LISTAR PROJETOS
    @GetMapping
    public String listar(Model model) {
        model.addAttribute("projetos", projetoService.listarTodos());
        return "projeto/listar";
    }

    // FORMULÁRIO DE NOVO PROJETO
    @GetMapping("/novo")
    public String novoProjeto(Model model) {
        model.addAttribute("projeto", new Projeto());
        model.addAttribute("gerentes", pessoaService.buscarGerentes());
        model.addAttribute("status", statusDisponiveis);
        model.addAttribute("riscos", riscoDisponiveis);
        return "projeto/form";
    }

    // SALVAR NOVO OU EDITADO
    @PostMapping("/salvar")
    public String salvarProjeto(@Valid @ModelAttribute Projeto projeto, BindingResult result, Model model) {
        List<String> statusValidos = List.of("Em Análise", "Análise Realizada", "Análise Aprovada", "Iniciado", "Planejado", "Em Andamento", "Encerrado", "Cancelado");
        List<String> riscoValidos = List.of("Baixo", "Médio", "Alto");

        model.addAttribute("gerentes", pessoaService.buscarGerentes());
        model.addAttribute("status", statusValidos);
        model.addAttribute("riscos", riscoValidos);

        if (!statusValidos.contains(projeto.getStatus())) {
            result.rejectValue("status", "status.invalido", "Status inválido.");
        }
        if (projeto.getRisco() != null && !riscoValidos.contains(projeto.getRisco())) {
            result.rejectValue("risco", "risco.invalido", "Classificação de risco inválida.");
        }
        if (projeto.getId() != null) {
            Projeto projetoExistente = projetoService.buscarPorId(projeto.getId());
            if (projetoExistente != null && List.of("Iniciado", "Em Andamento", "Encerrado").contains(projetoExistente.getStatus())) {
                result.reject("projeto.naoexcluivel", "Projetos com status 'Iniciado', 'Em Andamento' ou 'Encerrado' não podem ser alterados.");
            }
        }
        if (result.hasErrors()) {
            return "projeto/form";
        }
        projetoService.salvar(projeto);
        return "redirect:/projetos";
    }

    // EDITAR
    @GetMapping("/editar/{id}")
    public String editarProjeto(@PathVariable Long id, Model model) {
        Projeto projeto = projetoService.buscarPorId(id);
        model.addAttribute("projeto", projeto);
        model.addAttribute("gerentes", pessoaService.buscarGerentes());
        model.addAttribute("status", statusDisponiveis);
        model.addAttribute("riscos", riscoDisponiveis);
        return "projeto/form";
    }

    // EXCLUIR
    @GetMapping("/excluir/{id}")
    public String excluirProjeto(@PathVariable Long id, RedirectAttributes redirect) {
        Projeto projeto = projetoService.buscarPorId(id);
        if (statusRestritosParaExclusao.contains(projeto.getStatus())) {
            redirect.addFlashAttribute("erro", "Não é possível excluir um projeto com status: " + projeto.getStatus());
        } else {
            projetoService.excluir(id);
        }
        return "redirect:/projetos";
    }
}
