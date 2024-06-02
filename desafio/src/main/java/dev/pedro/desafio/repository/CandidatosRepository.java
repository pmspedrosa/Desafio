package dev.pedro.desafio.repository;

import dev.pedro.desafio.model.Candidato;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CandidatosRepository extends CrudRepository<Candidato, Long> {
    // Só são utilizados os metodos extendidos do CrudRepositor,
    // no entanto, é possivel adicionar metodos personalizados como os exemplos abaixo

    List<Candidato> findByNome(String nome);
    List<Candidato> findByIdade(int idade);
    List<Candidato> findByMorada(String morada);
}
