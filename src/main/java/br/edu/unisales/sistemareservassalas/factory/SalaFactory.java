package br.edu.unisales.sistemareservassalas.factory;

import br.edu.unisales.sistemareservassalas.model.Sala;
import java.util.List;

public class SalaFactory {


    public static Sala criarSala(String nome, Integer capacidade, List<String> recursosDisponiveis, String status) {
        return new Sala(nome, capacidade, recursosDisponiveis, status);
    }
}