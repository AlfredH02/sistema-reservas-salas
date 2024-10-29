package br.edu.unisales.sistemareservassalas.service;

import br.edu.unisales.sistemareservassalas.model.Reserva;
import br.edu.unisales.sistemareservassalas.repository.ReservaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ReservaServiceTest {

    @InjectMocks
    private ReservaService reservaService;

    @Mock
    private ReservaRepository reservaRepository;

    @Mock
    private SalaService salaService;

    private Reserva reserva;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        reserva = new Reserva("salaId", LocalDate.now(), LocalTime.of(14, 0), LocalTime.of(16, 0), "usuarioId");
    }

    @Test
    public void criarReserva_DeveRetornarReservaCriada() {
        when(salaService.isSalaDisponivel(anyString(), any(), any(), any())).thenReturn(true);
        when(reservaRepository.save(any(Reserva.class))).thenReturn(reserva);

        Reserva reservaCriada = reservaService.criarReserva(reserva);

        assertNotNull(reservaCriada);
        assertEquals("salaId", reservaCriada.getSalaId()); // Atualizar para refletir o id correto
        verify(reservaRepository, times(1)).save(reserva);
    }

    @Test
    public void criarReserva_DeveLancarExcecao_QuandoSalaNaoDisponivel() {
        when(salaService.isSalaDisponivel(anyString(), any(), any(), any())).thenReturn(false);

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            reservaService.criarReserva(reserva);
        });

        assertEquals("Sala não disponível para o horário solicitado.", exception.getMessage());
    }

    @Test
    public void cancelarReserva_DeveRemoverReserva() {
        when(reservaRepository.existsById("1")).thenReturn(true);

        boolean resultado = reservaService.cancelarReserva("1");

        assertTrue(resultado);
        verify(reservaRepository, times(1)).deleteById("1");
    }

    @Test
    public void buscarReservasPorFiltros_DeveFiltrarReservasPorData() {
        List<Reserva> reservas = List.of(reserva);
        when(reservaRepository.findAll()).thenReturn(reservas);

        List<Reserva> reservasFiltradas = reservaService.buscarReservasPorFiltros(reserva.getData(), null, null);

        assertFalse(reservasFiltradas.isEmpty());
        assertEquals(1, reservasFiltradas.size());
    }
}