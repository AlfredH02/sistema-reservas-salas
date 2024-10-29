package br.edu.unisales.sistemareservassalas.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.List;

@Setter
@Getter
@Document(collection = "salas")
public class Sala {

    @Id
    private String id;
    private String nome;
    private Integer capacidade;
    private List<String> recursosDisponiveis;
    private String status;  // 'A' para ativa e 'I' para inativa

    public Sala() {}

    public Sala(String nome, Integer capacidade, List<String> recursosDisponiveis, String status) {
        this.nome = nome;
        this.capacidade = capacidade;
        this.recursosDisponiveis = recursosDisponiveis;
        this.status = status;
    }

}