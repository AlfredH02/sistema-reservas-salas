package br.edu.unisales.sistemareservassalas.repository;

import br.edu.unisales.sistemareservassalas.model.Reserva;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.time.LocalDate;
import java.util.List;

public interface ReservaRepository extends MongoRepository<Reserva, String> {
    List<Reserva> findByData(LocalDate data);
    List<Reserva> findBySalaIdAndData(String salaId, LocalDate data);
}
