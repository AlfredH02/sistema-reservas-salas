package br.edu.unisales.sistemareservassalas.controller;

import br.edu.unisales.sistemareservassalas.model.Reserva;
import br.edu.unisales.sistemareservassalas.service.ReservaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/reservas")
public class ReservaController {

    private final ReservaService reservaService;

    @Autowired
    public ReservaController(ReservaService reservaService) {
        this.reservaService = reservaService;
    }

    @PostMapping
    public ResponseEntity<Reserva> criarReserva(@RequestBody Reserva reserva) {
        try {
            Reserva novaReserva = reservaService.criarReserva(reserva);
            return ResponseEntity.status(HttpStatus.CREATED).body(novaReserva);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> cancelarReserva(@PathVariable String id) {
        boolean cancelado = reservaService.cancelarReserva(id);
        if (cancelado) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping
    public ResponseEntity<List<Reserva>> buscarReservas(
            @RequestParam(required = false) LocalDate data,
            @RequestParam(required = false) Integer capacidade,
            @RequestParam(required = false) List<String> recursos) {
        List<Reserva> reservas = reservaService.buscarReservasPorFiltros(data, capacidade, recursos);
        return ResponseEntity.ok(reservas);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Reserva> alterarReserva(@PathVariable String id, @RequestBody Reserva reservaAtualizada) {
        try {
            Reserva reservaAlterada = reservaService.alterarReserva(id, reservaAtualizada);
            return ResponseEntity.ok(reservaAlterada);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @GetMapping("/buscarPorData/{data}")
    public ResponseEntity<List<Reserva>> buscarReservasPorData(@PathVariable String data) {
        LocalDate dataReserva = LocalDate.parse(data);
        List<Reserva> reservas = reservaService.buscarReservasPorData(dataReserva);
        return reservas.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(reservas);
    }
}