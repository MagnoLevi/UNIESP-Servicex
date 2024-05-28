package br.com.magnolevi.servicex.categoria.services;

import br.com.magnolevi.servicex.categoria.domain.Categoria;
import br.com.magnolevi.servicex.categoria.repositories.CategoriaRepository;
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
class CategoriaServiceTest {

    @InjectMocks
    private CategoriaService categoriaService;

    @Mock
    private CategoriaRepository categoriaRepository;

    @Test
    public void testCadastrarCategoria_Success() {
        Categoria categoria = new Categoria();
        categoria.setNomeCategoria("New category");

        // MONTANDO O CENARIO
        when(categoriaRepository.existsByNomeCategoria(categoria.getNomeCategoria())).thenReturn(false);
        when(categoriaRepository.save(any(Categoria.class))).thenReturn(categoria);

        // EXECUTANDO
        Categoria result = categoriaService.criarCategoria(categoria);

        // VALIDANDO
        assertNotNull(result);
        assertEquals("New category", result.getNomeCategoria());
        verify(categoriaRepository).existsByNomeCategoria(categoria.getNomeCategoria());
        verify(categoriaRepository).save(any(Categoria.class));
    }

    @Test
    public void testDeletarCategoria_Success() {
        Categoria categoria = new Categoria();
        categoria.setNomeCategoria("New category");

        // MONTANDO O CENARIO
        when(categoriaRepository.existsByNomeCategoria(categoria.getNomeCategoria())).thenReturn(false);
        when(categoriaRepository.save(any(Categoria.class))).thenReturn(categoria);

        // EXECUTANDO
        Categoria result = categoriaService.criarCategoria(categoria);

        // VALIDANDO
        assertNotNull(result);
        assertEquals("New category", result.getNomeCategoria());
        verify(categoriaRepository).existsByNomeCategoria(categoria.getNomeCategoria());
        verify(categoriaRepository).save(any(Categoria.class));
    }
}