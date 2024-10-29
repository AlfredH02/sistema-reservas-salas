package br.edu.unisales.sistemareservassalas.service;

import br.edu.unisales.sistemareservassalas.model.Sala;
import br.edu.unisales.sistemareservassalas.repository.SalaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class SalaServiceTest {

    @InjectMocks
    private SalaService salaService;

    @Mock
    private SalaRepository salaRepository;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void criarSala_DeveRetornarSalaCriada() {
        Sala sala = new Sala("Sala A", 10, List.of("Projetor"), "A");
        when(salaRepository.save(sala)).thenReturn(sala);

        Sala salaCriada = salaService.criarSala(sala);

        assertNotNull(salaCriada);
        assertEquals("Sala A", salaCriada.getNome());
        verify(salaRepository, times(1)).save(sala);
    }

    @Test
    public void buscarSalaPorId_DeveRetornarSala() {
        Sala sala = new Sala("Sala B", 15, List.of("Wi-Fi"), "A");
        when(salaRepository.findById("123")).thenReturn(Optional.of(sala));

        Optional<Sala> salaBuscada = salaService.buscarSalaPorId("123");

        assertTrue(salaBuscada.isPresent());
        assertEquals("Sala B", salaBuscada.get().getNome());
    }

    @Test
    public void atualizarSala_DeveAtualizarInformacoesDaSala() {
        Sala salaExistente = new Sala("Sala C", 20, List.of("Lousa"), "A");
        when(salaRepository.findById("123")).thenReturn(Optional.of(salaExistente));

        Sala salaAtualizada = new Sala("Sala C", 25, List.of("Lousa", "Wi-Fi"), "A");
        salaService.atualizarSala("123", salaAtualizada);

        verify(salaRepository, times(1)).save(salaExistente);
        assertEquals(25, salaExistente.getCapacidade());
    }
}