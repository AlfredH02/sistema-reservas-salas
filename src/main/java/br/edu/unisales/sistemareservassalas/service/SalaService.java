package br.edu.unisales.sistemareservassalas.service;

import br.edu.unisales.sistemareservassalas.model.Sala;
import br.edu.unisales.sistemareservassalas.model.Reserva;
import br.edu.unisales.sistemareservassalas.repository.ReservaRepository;
import br.edu.unisales.sistemareservassalas.repository.SalaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Service
public class SalaService {

    private final SalaRepository salaRepository;
    private final ReservaRepository reservaRepository;

    @Autowired
    public SalaService(SalaRepository salaRepository, ReservaRepository reservaRepository) {
        this.salaRepository = salaRepository;
        this.reservaRepository = reservaRepository;
    }

    public Sala criarSala(Sala sala) {
        return salaRepository.save(sala);
    }

    public Optional<Sala> buscarSalaPorId(String id) {
        return salaRepository.findById(id);
    }

    public Optional<Sala> buscarSalaPorNome(String nome) {
        return salaRepository.findByNome(nome);
    }

    public Sala atualizarSala(String id, Sala salaAtualizada) {
        Sala salaExistente = salaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Sala não encontrada com o ID: " + id));

        salaExistente.setNome(salaAtualizada.getNome());
        salaExistente.setCapacidade(salaAtualizada.getCapacidade());
        salaExistente.setRecursosDisponiveis(salaAtualizada.getRecursosDisponiveis());
        salaExistente.setStatus(salaAtualizada.getStatus());

        return salaRepository.save(salaExistente);
    }

    public void desativarSala(String id) {
        Sala sala = salaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Sala não encontrada com o ID: " + id));
        sala.setStatus("I");  // 'I' indica inativo
        salaRepository.save(sala);
    }

    public List<Sala> buscarTodasAsSalas() {
        return salaRepository.findAll();
    }

    public boolean isSalaDisponivel(String salaId, LocalDate data, LocalTime horarioInicio, LocalTime horarioFim) {
        List<Reserva> reservas = reservaRepository.findBySalaIdAndData(salaId, data);

        return reservas.stream().noneMatch(reserva ->
                (horarioInicio.isBefore(reserva.getHorarioFim()) && horarioFim.isAfter(reserva.getHorarioInicio())));
    }
}