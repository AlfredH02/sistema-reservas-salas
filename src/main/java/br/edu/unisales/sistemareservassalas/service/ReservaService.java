package br.edu.unisales.sistemareservassalas.service;

import br.edu.unisales.sistemareservassalas.model.Reserva;
import br.edu.unisales.sistemareservassalas.model.Sala;
import br.edu.unisales.sistemareservassalas.repository.ReservaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class ReservaService {

    private final ReservaRepository reservaRepository;
    private final SalaService salaService;

    @Autowired
    public ReservaService(ReservaRepository reservaRepository, SalaService salaService) {
        this.reservaRepository = reservaRepository;
        this.salaService = salaService;
    }

    public Reserva criarReserva(Reserva reserva) {
        if (!salaService.isSalaDisponivel(reserva.getSalaId(), reserva.getData(), reserva.getHorarioInicio(), reserva.getHorarioFim())) {
            throw new IllegalArgumentException("Sala não disponível para o horário solicitado.");
        }
        return reservaRepository.save(reserva);
    }

    public boolean cancelarReserva(String id) {
        if (reservaRepository.existsById(id)) {
            reservaRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public List<Reserva> buscarReservasPorFiltros(LocalDate data, Integer capacidade, List<String> recursos) {
        List<Reserva> reservas = reservaRepository.findAll();

        if (data != null) {
            reservas = reservas.stream()
                    .filter(reserva -> reserva.getData().equals(data))
                    .toList();
        }

        if (capacidade != null || (recursos != null && !recursos.isEmpty())) {
            Set<String> recursosSet = recursos != null ? new HashSet<>(recursos) : new HashSet<>();

            List<Sala> salasFiltradas = salaService.buscarTodasAsSalas().stream()
                    .filter(sala -> {
                        boolean atendeCapacidade = capacidade == null || sala.getCapacidade() >= capacidade;
                        boolean atendeRecursos = recursosSet.isEmpty() || recursosSet.stream().allMatch(sala.getRecursosDisponiveis()::contains);
                        return atendeCapacidade && atendeRecursos;
                    })
                    .toList();

            List<String> idsSalasFiltradas = salasFiltradas.stream()
                    .map(Sala::getId)
                    .toList();

            reservas = reservas.stream()
                    .filter(reserva -> idsSalasFiltradas.contains(reserva.getSalaId()))
                    .toList();
        }

        return reservas;
    }

    public Reserva alterarReserva(String id, Reserva reservaAtualizada) {
        if (reservaRepository.existsById(id)) {
            reservaAtualizada.setId(id);
            return reservaRepository.save(reservaAtualizada);
        }
        throw new IllegalArgumentException("Reserva não encontrada.");
    }

    public List<Reserva> buscarReservasPorData(LocalDate data) {
        return reservaRepository.findByData(data);
    }
}
