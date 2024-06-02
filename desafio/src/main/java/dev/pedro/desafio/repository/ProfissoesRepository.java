package dev.pedro.desafio.repository;

import dev.pedro.desafio.model.Profissoes;
import org.springframework.data.repository.CrudRepository;

public interface ProfissoesRepository extends CrudRepository<Profissoes, Long> {
    // Só são utilizados os metodos extendidos do CrudRepositor,
    // no entanto, é possivel adicionar metodos personalizados como os exemplos abaixo
    Profissoes findByDescricao(String descricao);
}
