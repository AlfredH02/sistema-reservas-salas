package br.edu.unisales.sistemareservassalas.controller;

import br.edu.unisales.sistemareservassalas.model.Sala;
import br.edu.unisales.sistemareservassalas.service.SalaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/salas")
public class SalaController {

    private final SalaService salaService;

    @Autowired
    public SalaController(SalaService salaService) {
        this.salaService = salaService;
    }

    @PostMapping
    public Sala criarSala(@RequestBody Sala sala) {
        return salaService.criarSala(sala);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Sala> buscarSalaPorId(@PathVariable String id) {
        Optional<Sala> sala = salaService.buscarSalaPorId(id);
        return sala.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/buscarPorNome/{nome}")
    public ResponseEntity<Sala> buscarSalaPorNome(@PathVariable String nome) {
        Optional<Sala> sala = salaService.buscarSalaPorNome(nome);
        return sala.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public Sala atualizarSala(@PathVariable String id, @RequestBody Sala salaAtualizada) {
        return salaService.atualizarSala(id, salaAtualizada);
    }

    @DeleteMapping("/{id}")
    public void desativarSala(@PathVariable String id) {
        salaService.desativarSala(id);
    }

    @GetMapping
    public List<Sala> buscarTodasAsSalas() {
        return salaService.buscarTodasAsSalas();
    }
}
