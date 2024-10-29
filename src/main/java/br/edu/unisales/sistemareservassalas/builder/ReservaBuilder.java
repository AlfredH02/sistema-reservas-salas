package br.edu.unisales.sistemareservassalas.builder;

import br.edu.unisales.sistemareservassalas.model.Reserva;
import java.time.LocalDate;
import java.time.LocalTime;

public class ReservaBuilder {

    private String salaId;
    private LocalDate data;
    private LocalTime horarioInicio;
    private LocalTime horarioFim;
    private String usuarioId;

    public ReservaBuilder setSalaId(String salaId) {
        this.salaId = salaId;
        return this;
    }

    public ReservaBuilder setData(LocalDate data) {
        this.data = data;
        return this;
    }

    public ReservaBuilder setHorarioInicio(LocalTime horarioInicio) {
        this.horarioInicio = horarioInicio;
        return this;
    }

    public ReservaBuilder setHorarioFim(LocalTime horarioFim) {
        this.horarioFim = horarioFim;
        return this;
    }

    public ReservaBuilder setUsuarioId(String usuarioId) {
        this.usuarioId = usuarioId;
        return this;
    }

    public Reserva build() {
        return new Reserva(salaId, data, horarioInicio, horarioFim, usuarioId);
    }
}
