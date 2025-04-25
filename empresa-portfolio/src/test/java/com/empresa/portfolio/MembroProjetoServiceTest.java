package com.empresa.portfolio;

import com.empresa.portfolio.model.MembroProjeto;
import com.empresa.portfolio.model.Pessoa;
import com.empresa.portfolio.model.Projeto;
import com.empresa.portfolio.repository.MembroProjetoRepository;
import com.empresa.portfolio.repository.PessoaRepository;
import com.empresa.portfolio.repository.ProjetoRepository;
import com.empresa.portfolio.service.impl.MembroProjetoServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class MembroProjetoServiceTest {

    @Mock
    private PessoaRepository pessoaRepository;

    @Mock
    private ProjetoRepository projetoRepository;

    @Mock
    private MembroProjetoRepository membroProjetoRepository;

    @InjectMocks
    private MembroProjetoServiceImpl service;

    @Test
    void deveAssociarPessoaComAtribuicaoFuncionario() {
        Long projetoId = 1L;
        Long pessoaId = 2L;
        String papel = "Desenvolvedor Back-End";

        Pessoa pessoa = new Pessoa();
        pessoa.setId(pessoaId);
        pessoa.setNome("João");
        pessoa.setFuncionario(Boolean.TRUE);

        Projeto projeto = new Projeto();
        projeto.setId(projetoId);
        projeto.setNome("Projeto X");

        when(pessoaRepository.findById(pessoaId)).thenReturn(Optional.of(pessoa));
        when(projetoRepository.findById(projetoId)).thenReturn(Optional.of(projeto));
        when(membroProjetoRepository.save(Mockito.any()))
                .thenAnswer(invocation -> invocation.getArgument(0));
        MembroProjeto membro = service.associarPessoaAoProjeto(projetoId, pessoaId, papel);

        assertEquals(pessoaId, membro.getPessoa().getId());
        assertEquals(projetoId, membro.getProjeto().getId());
        assertEquals(papel, membro.getPapel());
    }

    @Test
    void deveLancarExcecaoSePessoaNaoForFuncionario() {
        Long projetoId = 1L;
        Long pessoaId = 3L;

        Pessoa pessoa = new Pessoa();
        pessoa.setId(pessoaId);
        pessoa.setNome("Maria");
        pessoa.setFuncionario(Boolean.FALSE);

        Projeto projeto = new Projeto();
        projeto.setId(projetoId);
        projeto.setNome("Projeto Teste");

        when(pessoaRepository.findById(pessoaId)).thenReturn(Optional.of(pessoa));
        when(projetoRepository.findById(projetoId)).thenReturn(Optional.of(projeto));

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            service.associarPessoaAoProjeto(projetoId, pessoaId, "Tester");
        });
        assertEquals("Somente pessoas com atribuição 'Funcionario' podem ser membros de projeto.", exception.getMessage());
    }
}
