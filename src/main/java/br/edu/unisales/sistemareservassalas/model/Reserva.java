package br.edu.unisales.sistemareservassalas.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.time.LocalTime;

@Setter
@Getter
@Document(collection = "reservas")
public class Reserva {

    @Id
    private String id;
    private String salaId;
    private LocalDate data;
    private LocalTime horarioInicio;
    private LocalTime horarioFim;
    private String usuarioId;

    public Reserva() {}

    public Reserva(String salaId, LocalDate data, LocalTime horarioInicio, LocalTime horarioFim, String usuarioId) {
        this.salaId = salaId;
        this.data = data;
        this.horarioInicio = horarioInicio;
        this.horarioFim = horarioFim;
        this.usuarioId = usuarioId;
    }

}
