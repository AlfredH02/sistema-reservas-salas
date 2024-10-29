package br.edu.unisales.sistemareservassalas.repository;

import br.edu.unisales.sistemareservassalas.model.Sala;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface SalaRepository extends MongoRepository<Sala, String> {
    Optional<Sala> findByNome(String nome);
}
