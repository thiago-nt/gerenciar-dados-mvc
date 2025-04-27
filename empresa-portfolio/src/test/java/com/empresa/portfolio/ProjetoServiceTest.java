package com.empresa.portfolio;

import com.empresa.portfolio.common.enums.StatusProjeto;
import com.empresa.portfolio.model.Projeto;
import com.empresa.portfolio.repository.ProjetoRepository;
import com.empresa.portfolio.service.impl.ProjetoServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProjetoServiceTest {

	@InjectMocks
	private ProjetoServiceImpl projetoService;

	@Mock
	private ProjetoRepository projetoRepository;

	@Test
	void naoDevePermitirExcluirProjetoComStatusRestrito() {
		Projeto projeto = new Projeto();
		projeto.setId(1L);
		projeto.setStatus(StatusProjeto.INICIADO);

		when(projetoRepository.findById(1L)).thenReturn(Optional.of(projeto));

		IllegalStateException ex = assertThrows(IllegalStateException.class, () -> {
			projetoService.excluir(1L);
		});

		assertEquals("Não é possível excluir um projeto com status: INICIADO", ex.getMessage());
		verify(projetoRepository, never()).deleteById(any());
	}

	@Test
	void devePermitirExcluirProjetoComStatusPermitido() {
		Projeto projeto = new Projeto();
		projeto.setId(2L);
		projeto.setStatus(StatusProjeto.PLANEJADO);

		when(projetoRepository.findById(2L)).thenReturn(Optional.of(projeto));
		projetoService.excluir(2L);
		verify(projetoRepository).deleteById(2L);
	}
}