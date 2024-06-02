package dev.pedro.desafio.repository;

import dev.pedro.desafio.model.Profissoes;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfissoesRepository extends CrudRepository<Profissoes, Long> {
    // Só são utilizados os metodos extendidos do CrudRepositor,
    // no entanto, é possivel adicionar metodos personalizados como os exemplos abaixo
    Profissoes findByDescricao(String descricao);

}
