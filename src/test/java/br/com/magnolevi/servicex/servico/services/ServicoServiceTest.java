package br.com.magnolevi.servicex.servico.services;

import br.com.magnolevi.servicex.categoria.domain.Categoria;
import br.com.magnolevi.servicex.servico.domain.Servico;
import br.com.magnolevi.servicex.servico.repositories.ServicoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ServicoServiceTest {
    @InjectMocks
    private ServicoService servicoService;

    @Mock
    private ServicoRepository servicoRepository;

    @Test
    public void testCadastrarServico_Success() {
        Categoria categoria = new Categoria();
        // categoria.setIdCategoria(1);
        // categoria.setNomeCategoria("New category");

        Servico servico = new Servico();
        servico.setNome("Teste");
        servico.setValor(200.0);
        servico.setCategoria(categoria);

        // MONTANDO O CENARIO
        when(servicoRepository.existsById(servico.getIdServico())).thenReturn(false);
        when(servicoRepository.save(any(Servico.class))).thenReturn(servico);

        // EXECUTANDO
        Servico result = servicoService.criarServico(servico);

        // VALIDANDO
        assertNotNull(result);
        assertEquals(1, result.getIdServico());
        verify(servicoRepository).existsById(servico.getIdServico());
        verify(servicoRepository).save(any(Servico.class));
    }
}